package simulations

import AlaaS._
import io.gatling.core.Predef._
import singleObjects._

import scala.concurrent.duration._

class AlaaSAsyncPerfSimulation extends Simulation {

  /*** BEFORE ***/
  before {
    println(s"Running test on ${HttpConf.baseUrl}")
    println(s"Running test with ${HttpConf.userCount} users per each scenario")
    println(s"Ramping users over ${HttpConf.rampDuration} seconds")
    println(s"Total Test duration: ${HttpConf.testDuration} minutes")
    println(s"Running test with random timeRatio factor between: 1*${HttpConf.timeRatio} and 2*${HttpConf.timeRatio} seconds")
  }

  /***************** DEFINITION ************************/
  /*** NORMAL SCENARIOS ***/
  def mScenario() = {
    exec(OCRService.ocrAsyncService)
      .pause(1 * HttpConf.timeRatio seconds, 2 * HttpConf.timeRatio seconds)
  }



  /***************** PREPARATION ************************/
  /*** NORMAL SCENARIOS ***/
  val mScn = scenario("OCR Performance Full Chain Async Scenario")
    .exec(mScenario())

  /***************** SETUP ************************/
  setUp(
      mScn.inject(
        rampConcurrentUsers(1) to (HttpConf.userCount) during (HttpConf.rampDuration seconds),
        constantConcurrentUsers(HttpConf.userCount) during (HttpConf.testDuration minutes)),
  )
  .protocols(HttpConf.httpConf)
//      .assertions(
//        global.successfulRequests.percent.gte(97),
//        global.responseTime.max.lte(30000),
//        global.responseTime.mean.lte(6000),
//        global.requestsPerSec.gt(100)
//      )
  /*** After ***/
  after {
    println("Stress test completed")
  }
}
