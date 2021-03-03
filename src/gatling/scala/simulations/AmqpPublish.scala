package simulations

import _root_.scenario.CommonScenario
import hccn.qe.gatling.amqp.Predef._
import hccn.qe.gatling.amqp.protocol.AmqpProtocolBuilder
import hccn.qe.gatling.feeders.IdFeeder
import io.gatling.core.Predef._
import io.gatling.core.feeder.Feeder
import io.gatling.core.structure.ScenarioBuilder
import singleObjects._

import scala.concurrent.duration.DurationInt

/**
 * Execute this test.
 * - start docker-compose for the `docker-compose.yaml` - docker-compose -f docker-compose.yml up
 * -- open http://localhost:15672 (gatling publishes messages here), user: guest, password: guest
 * -- open http://localhost:15673 (consumer writes messages here), user: guest, password: guest
 * - run AmqpPublish from IDE - it will
 * -- gatling publish messages to test_q_in
 */
class AmqpPublish extends Simulation {
  lazy val idFeeder: Feeder[Int] = IdFeeder("id")

  val amqpConf: AmqpProtocolBuilder = amqp
    .connectionFactory(
      rabbitmq
        .host("localhost")
        .port(5672)
        .username("guest")
        .password("guest")
        .vhost("/")
    )
    .usePersistentDeliveryMode

  val scn: ScenarioBuilder = scenario("AMQP test")
    .feed(idFeeder)
    .exec(
      amqp("publish to exchange").publish
        .topicExchange("test_q_in","performance")
        .textMessage("Hello message - ${id}")
        .messageId("${id}")
        .priority(0)
        .contentType("application/json")
        .headers("test"-> "performance", "extra-test" -> "34-${id}")
    )

  setUp(
    scn.inject(rampUsersPerSec(1) to 5 during (60 seconds), constantUsersPerSec(5) during (5 minutes))
  ).protocols(amqpConf)
    .maxDuration(10 minutes)


}
