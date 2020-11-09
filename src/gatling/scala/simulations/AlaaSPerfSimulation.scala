package simulations

import io.gatling.core.Predef._
import singleObjects._
import AlaaS._

import scala.concurrent.duration._

class AlaaSPerfSimulation extends Simulation {

  /*** BEFORE ***/
  before {
    println(s"Running test on ${HttpConf.baseUrl}")
    println(s"Running test with ${HttpConf.userCount} users per each scenario")
    println(s"Ramping users over ${HttpConf.rampDuration} seconds")
    println(s"Total Test duration: ${HttpConf.testDuration} minutes")
    println(s"Running test with random timeRatio factor between: 1*${HttpConf.timeRatio} and 5*${HttpConf.timeRatio} seconds")
  }

  /***************** DEFINITION ************************/
  /*** NORMAL SCENARIOS ***/
  def mScenario() = {
    exec(OCRService.ocrService)
      .pause(1 * HttpConf.timeRatio seconds, 2 * HttpConf.timeRatio seconds)
  }

  println(s"Redis Feeder OCR image key is : ${OCRService.imageKey}")

  /***************** PREPARATION ************************/
  /*** NORMAL SCENARIOS ***/
  val mScn = scenario("OCR Performance Full Chain Scenario")
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
