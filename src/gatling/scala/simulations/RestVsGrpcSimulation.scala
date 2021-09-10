package simulations

// generated automatically by Taurus

import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import singleObjects.HttpConf

import scala.concurrent.duration._

class RestVsGrpcSimulation extends Simulation {

  var httpConf = http.baseUrl("http://172.17.162.193:8080")

  var testScenario: ScenarioBuilder = scenario("Rest Vs Grpc Scenario")

  var execution: ChainBuilder = exec(
    http("Request").get("/api/rest/unary/user/100")
  )

  def commonScenario(name: String): ScenarioBuilder = testScenario
    .exec(
      execution
    )

  setUp(
    commonScenario("Rest Vs Grpc Scenario").inject(
      rampConcurrentUsers(1) to (20) during (60.seconds),
      constantConcurrentUsers(20) during (30.minutes)),
  )
    .protocols(httpConf)

}
