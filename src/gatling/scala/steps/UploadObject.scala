package steps

import io.gatling.core.Predef._
import io.gatling.core.feeder.Feeder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import ru.tinkoff.gatling.config.SimulationConfig.getStringParam
import singleObjects.Headers
import util.GatlingFeeder

object UploadObject extends baseObject {

	lazy val requestName = "COMSS-agent microservice Upload Object"

	val uploadStreamWithEncryption: ChainBuilder =
		feed(fileFeeder)
		.exec(http(requestName)
		.post("/api/v1/oss/uploadObject/stream/withEncryption")
		.formParam("appAK",appAk)
		.formParam("appSK",appSk)
		.formParam("secret",secret)
		.formUpload("file","${file}")
		.check(status.is(200))
		.check(jsonPath("$.data.fullId").ofType[String].optional.saveAs("fullId")))
		.exitHereIfFailed

	def uploadStreamWithEncryptionByParam(file:String): ChainBuilder = {
		exec(http(requestName)
			.post("/api/v1/oss/uploadObject/stream/withEncryption")
			.formParam("appAK",appAk)
			.formParam("appSK",appSk)
			.formParam("secret",secret)
			.formUpload("file",file)
			.check(status.is(200))
			.check(jsonPath("$.data.fullId").ofType[String].optional.saveAs("fullId")))
			.exitHereIfFailed
	}
}
