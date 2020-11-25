package comss

import io.gatling.core.Predef._
import io.gatling.core.feeder.Feeder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import singleObjects.{Headers, HttpConf}
import util.{GatlingFeeder, GatlingHelper}

object DownloadObject {

	lazy val requestName_upload = "COMSS-agent microservice Upload Object"
	lazy val requestName_download = "COMSS-agent microservice Download Object"
	lazy val appAk = "ALDI-LD"
	lazy val appSk = "123456"
	lazy val secret = "19"
	val fileFeeder: Feeder[String] = GatlingFeeder.getFileListFeeder("src/gatling/resources/data")

	val downloadObjectStreamWithEncryption: ChainBuilder = {
		// first call , upload random file
		feed(fileFeeder)
		.exec(http(requestName_upload)
		.post("/api/v1/oss/uploadObject/stream/withEncryption")
		.formParam("appAK",appAk)
			.formParam("appSK",appSk)
			.formParam("secret",secret)
			.formUpload("file","${file}")
		.check(status.is(200)).check(jsonPath("$.data.fullId").saveAs("fullId")))
		.exitHereIfFailed
		// second call, download file
			.exec(http(requestName_download)
			.post("/api/v1/oss/downloadObject/os/withEncryption")
				.formParam("appSK",appSk)
				.formParam("appAK",appAk)
				.formParam("secret",secret)
				.formParam("fullId", "${fullId}")
				.formParam("filename", "Test-" + GatlingHelper.randomFileName(6))
				.check(status.is(200)))
				.exitHereIfFailed
	}

}
