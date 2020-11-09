package AlaaS

import io.gatling.core.Predef._
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import singleObjects.Headers
import util.{GatlingFeeder, GatlingHelper}

object OCRService {

	lazy val requestName = "Invoice-OCRService of open-ai-gateway"
	lazy val requestName_invoice = "Invoice-OCRService of Chineseocr Inference"
	lazy val imageKey:String = GatlingHelper.getProperty("IMAGE_KEY", "ocr-image-all-pass")

	val ocrService: ChainBuilder = feed(GatlingFeeder.getRedisFeeder(imageKey)).exec(http(requestName)
		.post("/api/ocr/v1/invoice-ocr")
		.headers(Headers.headersPost)
		.body(ElFileBody("bodies/ocrRequestBody.json")).asJson
		.check(status.is(200)))
		.exitHereIfFailed

	val ocrAsyncService: ChainBuilder = feed(GatlingFeeder.getRedisFeeder(imageKey)).exec(http(requestName)
		.post("/api/ocr/v1/async-invoice-ocr")
		.headers(Headers.headersPost)
		.body(ElFileBody("bodies/ocrAsyncRequestBody.json")).asJson
		.check(status.is(200)))
		.exitHereIfFailed

	val invoiceOcrService: ChainBuilder = feed(GatlingFeeder.getRedisFeeder("ocr-image-all-pass")).exec(http(requestName_invoice)
		.post("/app/chineseocr/api/inference/invoice-ocr")
		.headers(Headers.headersPost)
		.body(ElFileBody("bodies/invoiceOcrRequestBody.json")).asJson
		.check(status.is(200)))
		.exitHereIfFailed
}
