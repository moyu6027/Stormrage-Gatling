package steps

import cn.homecredit.qc.common.util.BankcardImages
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import singleObjects.HttpConf
import singleObjects.HttpConf.URLS
import hccn.qe.gatling.config.SimulationConfig.getStringParam
import hccn.qe.gatling.feeders.{BankcardImagesFeeder, BankcardNumbersFeeder, ChineseNamesFeeder, IdCardImagesFeeder, IdCardNumbersFeeder, MobileNumbersFeeder, RandomPhoneFeeder}
import io.gatling.core.feeder.Feeder
import java.io.File

object MmockObject extends baseObject {

//	lazy val requestName = "Monster-Mock Performance Test Object"
	lazy val testAppAk: String = getStringParam("appAk")
	val bankcardNumFeeder: Feeder[String] = BankcardNumbersFeeder("bankcardNum","CCB")
	val bankcardImagesFeeder: Feeder[File] = BankcardImagesFeeder("bankcardImage", "${bankcardNumber}")
	val chineseNameFeeder: Feeder[String] = ChineseNamesFeeder("name")
	val mobileNumbersFeeder: Feeder[String] = MobileNumbersFeeder("phone")
	val idCardNumbersFeeder: Feeder[String] = IdCardNumbersFeeder("idNum", 18, 88)
	val idCardImagesFeeder: Feeder[File] = IdCardImagesFeeder("idCardImage", "${IdCardNumber}", "张三", "汉", "上海市奉贤区南桥镇贝港新村120号104室", realface = false)

	def monsterMock(requestName:String): ChainBuilder = {
		feed(bankcardNumFeeder).feed(bankcardImagesFeeder).feed(chineseNameFeeder).feed(mobileNumbersFeeder)
			.feed(idCardNumbersFeeder)
			.exec(session => session.set("bankcardNumber", "${bankcardNum}").set("IdCardNumber", "${idNum}"))
		.exec(http(requestName)
		.post(URLS.MMOCK_FORM)
			.formParam("bankcard","${bankcardNum}")
			.formParam("file", "${bankcardImage}")
			.formParam("name", "${name}")
			.formParam("phone", "${phone}")
			.formParam("idNum", "${idNum}")
//			.formParam("idFile", "${idCardImage}")
		.check(status.is(200)))
		.exitHereIfFailed
	}

}
