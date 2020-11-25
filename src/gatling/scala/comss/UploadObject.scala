package comss

import io.gatling.core.Predef._
import io.gatling.core.feeder.{BatchableFeederBuilder, Feeder, FileBasedFeederBuilder}
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import singleObjects.Headers
import util.{GatlingFeeder, GatlingHelper}

object UploadObject {

	lazy val requestName = "COMSS-agent microservice Upload Object"
	lazy val appAk = "ALDI-LD"
	lazy val appSk = "123456"
	lazy val secret = "19"
	val fileFeeder: Feeder[String] = GatlingFeeder.getFileListFeeder("src/gatling/resources/data")

	val uploadObjectStreamWithEncryption: ChainBuilder =
		feed(fileFeeder)
		.exec(http(requestName)
		.post("/api/v1/oss/uploadObject/stream/withEncryption")
		.headers(Headers.headersForm)
		.formParam("appAK",appAk)
			.formParam("appSK",appSk)
			.formParam("secret",secret)
			.formUpload("file","${file}")
		.check(status.is(200)))
		.exitHereIfFailed
}