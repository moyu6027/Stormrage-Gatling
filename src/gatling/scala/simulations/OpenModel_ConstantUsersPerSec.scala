package simulations

import _root_.scenario.CommonScenario
import io.gatling.core.Predef._
import singleObjects._

import scala.concurrent.duration._

/**
 * Injects users at a constant rate, defined in users per second, during a given duration
 *    constantUsersPerSec(nbUsers) during(dur unit)
 * Injects nbUsers each second for duration.
 *
 * Example:
 *    constantUsersPerSec(10) during(5 seconds)
 *                             +----+
 *                             |    |
 *                             |    |
 *                             |    |
 *                        +----+    |
 *                        |         |
 *                        |         |
 *                   +----+         |
 *            +----+ |              |
 *     +----+ |    | |              +----+
 *   +_+    +_+    +_+                   +_+
 *   0  1111   2222   3333 4444 5555 6666  7
 */
class OpenModel_ConstantUsersPerSec extends CommonScenario {

  /***************** SETUP ************************/
  setUp(
      commonScenario("OpenModel_ConstantUsersPerSec").inject(
        atOnceUsers(1),                                // 0
        nothingFor(5.seconds),
        constantUsersPerSec(10)  during (20.seconds),   // 1111
        constantUsersPerSec(0)  during (5.seconds),
        constantUsersPerSec(20)  during (20.seconds),   // 2222
        //      constantUsersPerSec(0)  during (5 seconds),
        //      constantUsersPerSec(30)  during (20 seconds),   // 3333
        //      constantUsersPerSec(60)  during (20 seconds),   // 4444
        //      constantUsersPerSec(150)  during (20 seconds),  // 5555
        //      constantUsersPerSec(30)  during (20 seconds),   // 6666
        nothingFor(5.seconds),
        atOnceUsers(1)                                 // 7
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
