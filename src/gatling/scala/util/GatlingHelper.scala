package util

import java.io.File
import scala.util.Random
import com.redis.RedisClientPool
import io.gatling.redis.Predef.redisFeeder
import io.gatling.redis.feeder.RedisFeederBuilder

import scala.util.Random

object GatlingHelper {

  val rnd = new Random()

  /*** Configuration Helper Method ***/
  def getProperty(propertyName: String, defaultValue: String): String = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    println(d)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  def randomFile(dir: String): String ={
    val d = new File(dir)
    var fileList = List[File]()
    if (d.exists && d.isDirectory) {
      fileList = d.listFiles.filter(_.isFile).toList
    }
    val random_index = rnd.nextInt(fileList.length)
    return fileList(random_index).toString
  }



}
