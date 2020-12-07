package simulations

import _root_.scenario.CommonScenario
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import singleObjects._

import scala.concurrent.duration._

/**
 * Meta DSL to write closed increment tests (succession of several increasing levels)
 *
 * incrementConcurrentUsers(incrementConcurrentUsers)
 * .times(numberOfSteps)
 * .eachLevelLasting(levelDuration)
 * .separatedByRampsLasting(rampDuration)
 * .startingFrom(initialConcurrentUsers)
 *
 * Inject a succession of numberOfSteps levels each one
 * during levelDuration
 * and increasing the number of users per sec by incrementConcurrentUsers
 * starting from zero or the optional initialConcurrentUsers
 * and separated by optional ramps lasting rampDuration
 */
class CloseModel_IncrementConcurrentUsers extends CommonScenario {


  /*
               +------+
               |      |
        +------+      |
        |             |
 +------+             |
 |                    |
 +                    +


 */
  /***************** SETUP ************************/
  setUp(
    commonScenario("CloseModel_IncrementConcurrentUsers").inject(
      incrementConcurrentUsers(4)
        .times(10)
        .eachLevelLasting(10 second)
        .separatedByRampsLasting(0 second)
        .startingFrom(2)
  )
  .protocols(HttpConf.httpConf)
//    .throttle(
//      jumpToRps(1),
//      holdFor(20 second),
//      jumpToRps(2),
//      holdFor(20 second),
//      jumpToRps(3),
//      holdFor(20 second),
//      jumpToRps(4),
//      holdFor(20 second),
//      jumpToRps(5),
//      holdFor(20 second),
//      jumpToRps(6),
//      holdFor(20 second),
//      jumpToRps(7),
//      holdFor(20 second),
//      jumpToRps(8),
//      holdFor(20 second),
//      jumpToRps(9),
//      holdFor(20 second),
//      jumpToRps(10),
//      holdFor(20 second),
//      jumpToRps(11),
//      holdFor(20 second),
//      jumpToRps(12),
//      holdFor(20 second),
//      jumpToRps(13),
//      holdFor(10 second)
//    )
  )
//      .assertions(
//        global.successfulRequests.percent.gte(97),
//        global.responseTime.max.lte(30000),
//        global.responseTime.mean.lte(6000),
//        global.requestsPerSec.gt(100)
//      )
}
