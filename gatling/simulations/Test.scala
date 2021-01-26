package simulations

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Test extends Simulation with Settings {
	setUp(
    getTestScn.inject(constantUsersPerSec(execUsers) during (execSeconds seconds))
  ).protocols(httpProtocol)
}
