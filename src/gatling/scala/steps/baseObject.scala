package steps

import cn.homecredit.qc.common.util.ChineseNames
import io.gatling.core.Predef._
import io.gatling.core.feeder.Feeder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import hccn.qe.gatling.config.SimulationConfig.getStringParam
import util.GatlingFeeder

class baseObject {

	lazy val appAk: String = getStringParam("appAk")
	lazy val appSk: String = getStringParam("appSk")
	lazy val secret: String = getStringParam("secret")
	lazy val dataPath: String = getStringParam("dataPath")
	val fileFeeder: Feeder[String] = GatlingFeeder.getFileListFeeder(dataPath)
	val fileNameFeeder: Feeder[String] = GatlingFeeder.getRandomStringFeeder("filename",10)
	val chineseName: String = ChineseNames.random()
}
