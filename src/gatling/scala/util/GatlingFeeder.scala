package util

import java.io.File

import com.redis.RedisClientPool
import io.gatling.core.feeder.{Feeder, FileBasedFeederBuilder}
import io.gatling.redis.Predef.redisFeeder
import io.gatling.redis.feeder.RedisFeederBuilder
import hccn.qe.gatling.feeders._

import scala.util.Random

object GatlingFeeder {

  def getRedisFeeder(key: String): RedisFeederBuilder= {
    val dataFeederPool = new RedisClientPool("localhost", 6379)
    redisFeeder(dataFeederPool, key).SRANDMEMBER
  }

  def getFileListFeeder(dir: String): Feeder[String] ={
    Iterator.continually(Map(
      ("file"->GatlingHelper.randomFile(dir))
    ))
  }

  def getRandomStringFeeder(name:String,length:Int): Feeder[String] ={
    RandomStringFeeder(name,length)
  }

  def getRandomIntFeeder(name:String): Feeder[Int] = {
    RandomDigitFeeder(name)
  }

  def getRandomRangeStringFeeder(name:String,min:Int,max:Int,specified:String): Feeder[String] = {
    RandomRangeStringFeeder(name,min,max,specified)
  }

  def getRandomUUIDFeeder(name:String): Feeder[String] ={
    RandomUUIDFeeder(name)
  }


}
