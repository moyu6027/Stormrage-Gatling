package simulations

import cn.qe.gatling.dubbo.Predef._
import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ScenarioBuilder
import org.apache.dubbo.config.{ApplicationConfig, ReferenceConfig}
import org.apache.dubbo.config.annotation.Reference

import scala.concurrent.duration._
import org.apache.dubbo.samples.api.GreetingsService
import singleObjects.HttpConf

class DubboSimulation extends Simulation {

  /**
   * dubbo 服务初始化
   */
  val application = new ApplicationConfig()
  application.setName("gatling-dubbo")

  // 引用远程服务
  val reference = new ReferenceConfig[GreetingsService]
  reference.setApplication(application)
  reference.setUrl("dubbo://127.0.0.1:20880/org.apache.dubbo.samples.api.GreetingsService")  //设置服务地址、端口、全限定服务类名
  reference.setInterface(classOf[GreetingsService])

  val greetingsService: GreetingsService = reference.get()

  /**
    * gatling 压测逻辑
    * dubbo 压测插件的 api 只有一个，即 dubbo("com.youzan.xxx.XxxService", f)
    * 第一个参数可以是任意字符串，不过为了方便统计接口级的性能基线，建议设置为全限定接口名
    * 第二个参数的作用下面会讲述，名字也可以自行设置
    */
  val jsonFileFeeder: FileBasedFeederBuilder[Any]#F = jsonFile("bodies/dubbo.json").circular
  val dubboScenario: ScenarioBuilder = scenario("scenario of dubboService")
    .feed(jsonFileFeeder)
    .exec(dubbo("org.apache.dubbo.samples.api.GreetingsService", f))
//    .forever("tripsCount") {
//      feed(jsonFileFeeder)
//        .exec(
//          dubbo("org.apache.dubbo.samples.api.GreetingsService", f)
//
//          //            .threadPoolSize(200)    //4C8G施压机默认使用200线程池，你也可以根据施压机资源情况自行设置，一般不需要设置
//        )
//    }

    setUp(
      dubboScenario.inject(
        rampConcurrentUsers(1) to (1000) during (30.seconds),
        constantConcurrentUsers(1000) during (3.minutes)
//        rampUsers(100) during (5 seconds),
//        constantUsersPerSec(20) during(15 seconds),
//        heavisideUsers(1000) during (20 seconds)
//        .throttle(reachRps(1000) in (1 seconds), holdFor(120 seconds)
      ))

    // 接口调用逻辑，包括入参构造和设置. 注意参数类型需一致，不一致就做相应的转化
    def f(session: Session): Object = {
      //如果方法的入参是复杂对象, 必须在这里 new 对象和 set 字段

      greetingsService.sayHi(session.attributes("kdtId").toString)
    }
}
