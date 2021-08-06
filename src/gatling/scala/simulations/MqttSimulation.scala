package simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import cn.qe.gatling.mqtt.Predef._
import org.fusesource.mqtt.client.QoS

import scala.concurrent.duration._

class MqttSimulation extends Simulation {
  private val mqttConf = mqtt
    .host("tcp://localhost:1883")
    .version("3.1.1")

  private val scn:ScenarioBuilder = scenario("MQTT Test")
//    .feed(csv("topics-and-payloads.csv"))
    .exec(mqtt("Connection").connect())
    .exec(mqtt("Publishing").publish("foo", "Hello", QoS.EXACTLY_ONCE, retain = false))

  setUp(scn.inject(rampUsersPerSec(1) to 5 during (60 seconds), constantUsersPerSec(5) during (5 minutes)))
    .protocols(mqttConf)
    .maxDuration(10.minutes)
}
