package simulations

import hccn.qe.gatling.kafka.Predef._
import hccn.qe.gatling.kafka.protocol.KafkaProtocol
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import org.apache.kafka.clients.producer.ProducerConfig

import scala.concurrent.duration._

class KafkaByteArraySimulation extends Simulation {
  val kafkaConf: KafkaProtocol = kafka
    .topic("test")
    .properties(
      Map(
        ProducerConfig.ACKS_CONFIG -> "1",
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG ->
          "org.apache.kafka.common.serialization.ByteArraySerializer",
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG ->
          "org.apache.kafka.common.serialization.ByteArraySerializer"))

  val scn: ScenarioBuilder = scenario("Kafka Test")
    .exec(kafka("request").send("kafkaConfigurationTest".getBytes: Array[Byte]))

  setUp(
    scn
      .inject(constantUsersPerSec(10) during(90.seconds)))
    .protocols(kafkaConf)
}
