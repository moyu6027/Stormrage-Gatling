package util

import com.redis.RedisClientPool
import io.gatling.core.feeder.FileBasedFeederBuilder
import io.gatling.redis.Predef.redisFeeder
import io.gatling.redis.feeder.RedisFeederBuilder

import scala.util.Random

object GatlingFeeder {

  def getRedisFeeder(key: String): RedisFeederBuilder= {
    val dataFeederPool = new RedisClientPool("localhost", 6379)
    return redisFeeder(dataFeederPool, key).SRANDMEMBER
  }

}
