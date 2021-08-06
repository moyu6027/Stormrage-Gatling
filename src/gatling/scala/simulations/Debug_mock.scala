package simulations

import _root_.scenario.{CommonScenario, MockScenario}
import io.gatling.core.Predef._
import singleObjects._
import cn.qe.gatling.loadmodels._
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import cn.qe.gatling.config.SimulationConfig._
import scala.concurrent.duration.DurationInt

class Debug_mock extends MockScenario with BaselineTest with BenchmarkTest with LoadTest with StressTest {

  lazy val times: Int = getIntParam("times")

  /***************** SETUP ************************/
//  setUp(
//    baseline(mockScenario("Baseline Test")).andThen(
//      benchmark_closed(mockScenario("Benchmark Test"),userCount,rampDuration,testDuration).andThen(
//        load_closed(mockScenario("Load Test"), userCount,increment,times,testDuration).andThen(
//          stress_open(mockScenario("Stress Test"), userRate, 5.minutes,500)
//        )
//      )
//    )
//  )
//  .protocols(HttpConf.httpConf)

  setUp(
    baseline(mockScenario("Stress Test"))
  ).protocols(HttpConf.httpConf)
//      .assertions(
//        global.successfulRequests.percent.gte(97),
//        global.responseTime.max.lte(30000),
//        global.responseTime.mean.lte(6000),
//        global.requestsPerSec.gt(100)
//      )
}
