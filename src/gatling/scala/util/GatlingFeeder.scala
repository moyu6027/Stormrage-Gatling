package util

import java.io.File

import com.redis.RedisClientPool
import io.gatling.core.feeder.{Feeder, FileBasedFeederBuilder}
import io.gatling.redis.Predef.redisFeeder
import io.gatling.redis.feeder.RedisFeederBuilder

import scala.util.Random

object GatlingFeeder {

  def getRedisFeeder(key: String): RedisFeederBuilder= {
    val dataFeederPool = new RedisClientPool("localhost", 6379)
    return redisFeeder(dataFeederPool, key).SRANDMEMBER
  }

  def getFileListFeeder(dir: String): Feeder[String] ={
    Iterator.continually(Map(
      ("file"->GatlingHelper.randomFile(dir))
    ))
  }

}
