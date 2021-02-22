package simulations

import _root_.scenario.{CommonScenario, MockScenario}
import io.gatling.core.Predef._
import singleObjects._
import hccn.qe.gatling.loadmodels._
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import hccn.qe.gatling.config.SimulationConfig._
import scala.concurrent.duration.DurationInt

class Debug_mock extends MockScenario with BaselineTest with BenchmarkTest with LoadTest with StressTest {

  lazy val increment: Int = getIntParam("increment")
  lazy val times: Int = getIntParam("times")

  /***************** SETUP ************************/
  setUp(
    baseline(mockScenario("Baseline Test")).andThen(
      benchmark_closed(mockScenario("Benchmark Test"),userCount,rampDuration,testDuration).andThen(
        load_closed(mockScenario("Load Test"), userCount,increment,times).andThen(
          stress_open(mockScenario("Stress Test"), userCount*100, 15.minutes,1000)
        )
      )
    )
  )
  .protocols(HttpConf.httpConf)
//      .assertions(
//        global.successfulRequests.percent.gte(97),
//        global.responseTime.max.lte(30000),
//        global.responseTime.mean.lte(6000),
//        global.requestsPerSec.gt(100)
//      )
}
