package simulations

import scala.concurrent.duration._
import scala.util.Random

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

trait Settings {

  val baseUrl = sys.env.getOrElse("baseUrl", "http://localhost")
  val execUsers = sys.env.getOrElse("execUsers", "1").toDouble
  val execSeconds = sys.env.getOrElse("execSeconds", "1").toDouble

  val httpProtocol = http.baseUrl(baseUrl)

  object Test {
    val test = exec(
      http("get test")
        .get(s"/test/${Random.nextInt}")
        .check(status.is(200))
    )
  }

  var getTestScn = scenario("Test").exec(Test.test)

}
