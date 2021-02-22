package steps

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import singleObjects.HttpConf
import singleObjects.HttpConf.URLS
import hccn.qe.gatling.config.SimulationConfig.getStringParam

object MmockObject extends baseObject {

//	lazy val requestName = "Monster-Mock Performance Test Object"
	lazy val testAppAk: String = getStringParam("appAk")

	def monsterMock(requestName:String): ChainBuilder = {
		exec(http(requestName)
		.post(URLS.MMOCK_FORM)
		.check(status.is(200)))
		.exitHereIfFailed
	}

}
