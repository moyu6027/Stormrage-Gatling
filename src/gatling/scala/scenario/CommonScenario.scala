package scenario

import cases.{DownloadObject, UploadObject}
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import singleObjects._

import scala.concurrent.duration._

trait CommonScenario extends Simulation {

  /*** BEFORE ***/
  before {
    println(s"Running test on ${HttpConf.baseUrl}")
    println(s"Running test with ${HttpConf.userCount} users per each scenario")
    println(s"Ramping users over ${HttpConf.rampDuration} seconds")
    println(s"Total Test duration: ${HttpConf.testDuration} minutes")
    println(s"Running test with random timeRatio factor between: 1*${HttpConf.timeRatio} and 5*${HttpConf.timeRatio} seconds")
  }
  /*** After ***/
  after {
    println("Performance test completed!")
  }

  /***************** DEFINITION ************************/
  /*** NORMAL SCENARIOS ***/
  def commonScenario(): ChainBuilder = {
    exec(UploadObject.uploadStreamWithEncryption)
//    .pause(1 * HttpConf.timeRatio seconds, 2 * HttpConf.timeRatio seconds)
    .exec(DownloadObject.downloadStreamWithEncryption)
  }

  def commonScenarioWithGroups(): ChainBuilder = {
    group("Upload") {
      exec(UploadObject.uploadStreamWithEncryption)
    }
    .group("Download") {
      exec(DownloadObject.downloadStreamWithEncryption)
    }
  }

  /***************** PREPARATION ************************/
  /*** NORMAL SCENARIOS ***/
  def commonScenario(name: String): ScenarioBuilder = scenario(name)
    .exec(
      commonScenario()
    )

  def commonScenarioWithGroups(name: String): ScenarioBuilder = scenario(name)
    .exec(
      commonScenarioWithGroups()
    )

  def commonScenario(name: String, groupName: String): ScenarioBuilder = scenario(name)
    .group(groupName) {
      exec(
        commonScenario(name)
      )
    }

}
