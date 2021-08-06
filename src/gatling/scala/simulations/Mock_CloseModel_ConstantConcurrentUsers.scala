package simulations

import _root_.scenario.{CommonScenario, MockScenario}
import io.gatling.core.Predef._
import singleObjects._

import scala.concurrent.duration._

class Mock_CloseModel_ConstantConcurrentUsers extends MockScenario {

  /***************** SETUP ************************/
  setUp(
    mockScenario("Monster-Mock CloseModel_ConstantConcurrentUsers","").inject(
        rampConcurrentUsers(1) to (HttpConf.userCount) during (HttpConf.rampDuration.seconds),
        constantConcurrentUsers(HttpConf.userCount) during (HttpConf.testDuration.minutes)),
  )
  .protocols(HttpConf.httpConf)
//      .assertions(
//        global.successfulRequests.percent.gte(97),
//        global.responseTime.max.lte(30000),
//        global.responseTime.mean.lte(6000),
//        global.requestsPerSec.gt(100)
//      )
}
