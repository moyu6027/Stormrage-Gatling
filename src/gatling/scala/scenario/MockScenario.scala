package scenario

import steps.MmockObject.{chineseName, testAppAk}
import steps.{DownloadObject, MmockObject, UploadObject}
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import singleObjects._
import java.text.SimpleDateFormat
import java.util.TimeZone

trait MockScenario extends Simulation {

  /*** BEFORE ***/
  before {
    val df:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"))
    println(chineseName)
    println(testAppAk)
    println(s"TimeZone is ${TimeZone.getDefault}")
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
  /*** NORMAL CHAIN ***/
  def mockScenario(requestName:String): ChainBuilder = {
    exec(MmockObject.monsterMock(requestName))
//    .pause(1 * HttpConf.timeRatio seconds, 2 * HttpConf.timeRatio seconds)
  }

  /***************** PREPARATION ************************/
  /*** NORMAL SCENARIOS ***/
  def mockScenario(name: String, requestName:String): ScenarioBuilder = scenario(name)
    .exec(
      mockScenario(requestName)
    )

  /*** GROUP SCENARIOS ***/
  def mockScenario(name: String, requestName:String, groupName: String): ScenarioBuilder = scenario(name)
    .group(groupName) {
      exec(
        mockScenario(name, requestName)
      )
    }

}
