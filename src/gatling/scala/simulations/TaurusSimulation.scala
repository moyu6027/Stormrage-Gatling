package simulations

// generated automatically by Taurus

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TaurusSimulation extends Simulation {
  val concurrency = Integer.getInteger("concurrency", 1).toInt
  val rampUpTime = Integer.getInteger("ramp-up", 0).toInt
  val holdForTime = Integer.getInteger("hold-for", 0).toInt
  val throughput = Integer.getInteger("throughput")
  val iterationLimit = Integer.getInteger("iterations")

  val durationLimit = rampUpTime + holdForTime

  var httpConf = http.baseUrl("https://b2c-mini-app-bff-cn00c9.uat.homecreditcfc.cn")
    .header("Authorization", "Basic YWFhOmJiYg==")
    .header("Content-Type", "application/json")

  var testScenario = scenario("Taurus Scenario")

  var execution = exec(
    http("Token").post("/api/token")
      .body(StringBody("""{"code": "0113xWKw0cFQqd1VazMw0LBMKw03xWKa"}"""))
  ).exec(
    http("Offers").get("/api/offers/getOffers")
      .header("X-Mini-App-Token", "${token}")
  )

  if (iterationLimit == null)
    testScenario = testScenario.forever{execution}
  else
    testScenario = testScenario.repeat(iterationLimit.toInt){execution}

  val virtualUsers =
    if (rampUpTime > 0)
      rampUsers(concurrency) during (rampUpTime.seconds)
    else
      atOnceUsers(concurrency)

  var testSetup = setUp(testScenario.inject(virtualUsers).protocols(httpConf))

  if (throughput != null)
    testSetup = testSetup.throttle(
      reachRps(throughput) in (rampUpTime),
      holdFor(Int.MaxValue)
    )

  if (durationLimit > 0)
    testSetup.maxDuration(durationLimit)
}
