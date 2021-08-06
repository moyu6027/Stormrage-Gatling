package singleObjects

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import cn.qe.gatling.config.SimulationConfig
import cn.qe.gatling.config.SimulationConfig._

/** HTTP configuration */
object HttpConf {

		private val env_url = SimulationConfig.baseUrl // an optional url if a variable is not set in CLI command
//	private val log_auth_qa = "base_auth"
//	private val pass_auth_qa = "base_auth"

	/*
  Setting up Http Protocols (BaseUrl , Headers etc.)
   */
	val httpConf: HttpProtocolBuilder = http
  	.baseUrl(baseUrl)
		.acceptHeader("text/html,application/xhtml+xml,application/json,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate, br")
		.shareConnections

//	.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36 PerformanceTest/1.0") // sample value, you can add e.g. phrase PerformanceTest/1.0 to find requests easier e.g. in Kibana logs
// .acceptLanguageHeader("en-US,en;q=0.9,pl-PL;q=0.8,pl;q=0.7")

//		.inferHtmlResources(BlackList(), WhiteList())
//		.proxy(Proxy("localhost", 8080)) // -> you can use it for debugging e.g. with Burp Suite or Fiddler (remember to set certain certifcates for https urls debugging)
//		.disableWarmUp
//		.disableCaching
//		.basicAuth(log_auth_qa,pass_auth_qa) // -> you can use it for testing QA environment if needed

	/*** Variables ***/
	// runtime variables
	def baseUrl: String = getProperty("BASE_URL", env_url)
	def userCount: Int = getProperty("USERS", "10").toInt
	def rampDuration: Int = getProperty("RAMP_DURATION", "30").toInt		//in seconds
	def testDuration: Int = getProperty("DURATION", "1").toInt 				//in minutes
	def timeRatio: Float = getProperty("TIME_RATIO", "1.0").toFloat		//in seconds


	/*** Helper Method ***/
	private def getProperty(propertyName: String, defaultValue: String) = {
		Option(System.getenv(propertyName))
			.orElse(Option(System.getProperty(propertyName)))
			.getOrElse(defaultValue)
	}

	object URLS {
		val MMOCK_FORM = s"${baseUrl}/body/form"
	}
}
