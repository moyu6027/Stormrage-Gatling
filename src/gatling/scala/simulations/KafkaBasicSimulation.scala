package simulations

import io.gatling.core.Predef._
import org.apache.kafka.clients.producer.ProducerConfig

import scala.concurrent.duration._
import hccn.qe.gatling.kafka.Predef._
import hccn.qe.gatling.kafka.protocol.KafkaProtocol
import io.gatling.core.structure.ScenarioBuilder

class KafkaBasicSimulation extends Simulation {
  val kafkaConf: KafkaProtocol = kafka
    // Kafka topic name
    .topic("test")
    // Kafka producer configs
    .properties(
      Map(
        ProducerConfig.ACKS_CONFIG -> "1",
        // list of Kafka broker hostname and port pairs
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092",

        // in most cases, StringSerializer or ByteArraySerializer
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG ->
          "org.apache.kafka.common.serialization.StringSerializer",
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG ->
          "org.apache.kafka.common.serialization.StringSerializer"))

  val scn: ScenarioBuilder = scenario("Kafka Test")
    .exec(
      kafka("request")
        // message to send
        .send[String]("gatling kafka test"))

  setUp(
    scn
      .inject(constantUsersPerSec(10) during(90.seconds)))
    .protocols(kafkaConf)
}
