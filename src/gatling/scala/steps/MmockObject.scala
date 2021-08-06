package steps

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import singleObjects.HttpConf
import singleObjects.HttpConf.URLS
import cn.qe.gatling.config.SimulationConfig.getStringParam
import cn.qe.gatling.feeders.RandomPhoneFeeder
import io.gatling.core.feeder.Feeder
import java.io.File

object MmockObject extends baseObject {

//	lazy val requestName = "Monster-Mock Performance Test Object"
	lazy val testAppAk: String = getStringParam("appAk")

	val randomPhoneFeeder: Feeder[String] = RandomPhoneFeeder("phone")

	def monsterMock(requestName:String): ChainBuilder = {
		feed(randomPhoneFeeder)
			.exec(session => session.set("bankcardNumber", "${bankcardNum}").set("IdCardNumber", "${idNum}"))
		.exec(http(requestName)
		.post(URLS.MMOCK_FORM).formParam("phone","${phone}")
		.check(status.is(200)))
		.exitHereIfFailed
	}

}
