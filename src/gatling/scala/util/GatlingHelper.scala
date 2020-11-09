package util

import com.redis.RedisClientPool
import io.gatling.redis.Predef.redisFeeder
import io.gatling.redis.feeder.RedisFeederBuilder

import scala.util.Random

object GatlingHelper {

  /*** Configuration Helper Method ***/
  def getProperty(propertyName: String, defaultValue: String): String = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

}
