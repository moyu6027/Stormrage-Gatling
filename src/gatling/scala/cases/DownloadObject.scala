package cases

import io.gatling.core.Predef._
import io.gatling.core.feeder.Feeder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import util.{GatlingFeeder, GatlingHelper}
import ru.tinkoff.gatling.config.SimulationConfig._

object DownloadObject extends baseObject {

	lazy val requestName = "COMSS-agent microservice Download Object"

	val downloadStreamWithEncryption: ChainBuilder = {
			exec(http(requestName)
			.post("/api/v1/oss/downloadObject/os/withEncryption")
			.formParam("appSK",appSk)
			.formParam("appAK",appAk)
			.formParam("secret",secret)
			.formParam("fullId", "${fullId}")
			.formParam("filename", "Test-" + GatlingHelper.randomFileName(6))
			.check(status.is(200)))
			.exitHereIfFailed
	}

	def downloadStreamWithEncryptionByParam(fullId:String): ChainBuilder ={
		exec(http(requestName)
			.post("/api/v1/oss/downloadObject/os/withEncryption")
			.formParam("appSK",appSk)
			.formParam("appAK",appAk)
			.formParam("secret",secret)
			.formParam("fullId", fullId)
			.formParam("filename", "Test-" + GatlingHelper.randomFileName(6))
			.check(status.is(200)))
			.exitHereIfFailed
	}
}
