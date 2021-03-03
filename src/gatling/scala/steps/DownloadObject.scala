package steps

import io.gatling.core.Predef._
import io.gatling.core.feeder.Feeder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import util.{GatlingFeeder, GatlingHelper}
import hccn.qe.gatling.config.SimulationConfig._

object DownloadObject extends baseObject {

	lazy val requestName = "COMSS-agent microservice Download Object"

	val downloadStreamWithEncryption: ChainBuilder = {
		feed(fileNameFeeder)
		.exec(http(requestName)
		.post("/api/v1/oss/downloadObject/os/withEncryption")
		.formParam("appSK",appSk)
		.formParam("appAK",appAk)
		.formParam("secret",secret)
		.formParam("fullId", "${fullId}")
		.formParam("filename", "${filename}")
		.check(status.is(200)))
		.exitHereIfFailed
	}

	def downloadStreamWithEncryptionByParam(fullId:String): ChainBuilder ={
		feed(fileNameFeeder)
		.exec(http(requestName)
		.post("/api/v1/oss/downloadObject/os/withEncryption")
		.formParam("appSK",appSk)
		.formParam("appAK",appAk)
		.formParam("secret",secret)
		.formParam("fullId", fullId)
		.formParam("filename", "${filename}")
		.check(status.is(200)))
		.exitHereIfFailed
	}
}
