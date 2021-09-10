package simulations

import cn.qe.gatling.example.{DemoServiceGrpc, Ping, Pong}
import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.grpc.action.GrpcCallActionBuilder
import com.github.phisgr.gatling.grpc.protocol.StaticGrpcProtocol
import com.github.phisgr.gatling.javapb._
import io.gatling.core.Predef._
import io.gatling.core.session.Expression
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration._

class PingPongSimulation extends Simulation {

  val grpcConf: StaticGrpcProtocol = grpc(managedChannelBuilder("172.17.162.193", 7777).usePlaintext())
    .shareChannel

  val message: Expression[Ping] =
    Ping.getDefaultInstance
      // dynamic payload!
      .update(_.setData)($("data"))

  def request(name: String): GrpcCallActionBuilder[Ping, Pong] = grpc(name)
    .rpc(DemoServiceGrpc.getPingPongMethod)
    .payload(message)
    .extract(_.getData.some)(_ is $("data"))

  val scn: ScenarioBuilder = scenario("Play gRPC Ping Pong")
    .exec(_.set("data", 10000000))
    .exec(request("Grpc send message"))
    .exec(session =>
                session.set("data",
                  1 + session("data").as[Int]))
//    .forever("tripsCount") {
//      pause(500.millis)
//        .exec(request("Send message"))
//        .exec(session =>
//          session.set("data",
//            1 + session("data").as[Int])
//        )
//    }

  setUp(scn.inject(
//    constantUsersPerSec(100) during (10 seconds))
//    .throttle(reachRps(1000) in (1 seconds), holdFor(120 seconds)
//    rampUsers(100) during (5 seconds),
//    constantUsersPerSec(20) during(15 seconds),
//    heavisideUsers(1000) during (20 seconds)
    rampConcurrentUsers(1) to (1000) during (30.seconds),
    constantConcurrentUsers(1000) during (3.minutes)
  )
  ).protocols(grpcConf)
}
