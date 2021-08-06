package simulations

import io.gatling.core.Predef._
import _root_.scenario.CommonScenario
import singleObjects._

import scala.concurrent.duration._

class CloseModel_ConstantConcurrentUsers extends CommonScenario {

  /***************** SETUP ************************/
  setUp(
      commonScenario("CloseModel_ConstantConcurrentUsers").inject(
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
