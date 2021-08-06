package simulations

import _root_.scenario.CommonScenario
import io.gatling.core.Predef._
import singleObjects._

import scala.concurrent.duration._

class Debug extends CommonScenario {

  /***************** SETUP ************************/
  setUp(
      commonScenario("Debug").inject(
        atOnceUsers(HttpConf.userCount)
  ))
  .protocols(HttpConf.httpConf)
//      .assertions(
//        global.successfulRequests.percent.gte(97),
//        global.responseTime.max.lte(30000),
//        global.responseTime.mean.lte(6000),
//        global.requestsPerSec.gt(100)
//      )
}
