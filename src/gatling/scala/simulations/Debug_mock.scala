package simulations

import _root_.scenario.{CommonScenario, MockScenario}
import io.gatling.core.Predef._
import singleObjects._

class Debug_mock extends MockScenario {

  /***************** SETUP ************************/
  setUp(
      mockScenario("Debug").inject(
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
