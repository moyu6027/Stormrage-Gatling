package simulations

import io.gatling.core.Predef._
import demo.{HomePage, SearchFromHomePage}
import singleObjects._
import scala.concurrent.duration._

class GatlingSampleSimulation extends Simulation {

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
    exec(HomePage.homePage)
      .pause(1 * HttpConf.timeRatio seconds, 5 * HttpConf.timeRatio seconds)
      .exec(SearchFromHomePage.mSearchFromHomepageAction)
  }



  /***************** PREPARATION ************************/
  /*** NORMAL SCENARIOS ***/
  val mScn1 = scenario("mScn1")
    .exec(mScenario())

  /***************** SETUP ************************/
  setUp(
      mScn1.inject(
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
}
