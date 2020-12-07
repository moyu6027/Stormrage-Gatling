package simulations

import _root_.scenario.CommonScenario
import io.gatling.core.Predef._
import singleObjects._

import scala.concurrent.duration._

/**
 *                                          +
 *                                      +   |
 *                                  +   |   |
 *                              +   |   |   |
 *                          +   |   |   |   |
 *                      +   |   |   |   |   |
 *                  +   |   |   |   |   |   |
 *              +   |   |   |   |   |   |   |
 *          +   |   |   |   |   |   |   |   |
 *      +   |   |   |   |   |   |   |   |   |
 *  +___+___+___+___+___+___+___+___+___+___+
 *  0   1   2   3   4   5   6   7   8   9   10
 */
class OpenModel_AtOnceUsers extends CommonScenario {

  val waitDuration: FiniteDuration = 5 second
  val userStep = 10
  /***************** SETUP ************************/
  setUp(
      commonScenario("OpenModel_AtOnceUsers").inject(
        atOnceUsers(1),             // 0
        nothingFor(waitDuration),
        atOnceUsers(1 * userStep),  // 1
        nothingFor(waitDuration),
        atOnceUsers(2 * userStep),  // 2
        nothingFor(waitDuration),
        atOnceUsers(3 * userStep),  // 3
        nothingFor(waitDuration),
        atOnceUsers(4 * userStep),  // 4
        nothingFor(waitDuration),
        atOnceUsers(5 * userStep),  // 5
        nothingFor(waitDuration),
        atOnceUsers(6 * userStep),  // 6
        nothingFor(waitDuration),
        atOnceUsers(7 * userStep),  // 7
        nothingFor(waitDuration),
        atOnceUsers(8 * userStep),  // 8
        nothingFor(waitDuration),
        atOnceUsers(9 * userStep),  // 9
        nothingFor(waitDuration),
        atOnceUsers(10 * userStep), // 10
      )
  )
  .protocols(HttpConf.httpConf)
//      .assertions(
//        global.successfulRequests.percent.gte(97),
//        global.responseTime.max.lte(30000),
//        global.responseTime.mean.lte(6000),
//        global.requestsPerSec.gt(100)
//      )
}
