package demo

import com.redis.RedisClientPool
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.redis.Predef.redisFeeder
import io.gatling.redis.feeder.RedisFeederBuilder

import scala.util.Random

object SearchFromHomePage {

  private lazy val requestName0 = "SearchAjaxSuggest"

  val quantity = csv("data/quantity.csv").random
  val searchKey = csv("data/searchPageKey.csv").random
  val dataFeederPool = new RedisClientPool("localhost", 6379)
  val myDataFeeder: RedisFeederBuilder =
    redisFeeder(dataFeederPool, "search_keys").SRANDMEMBER

  val mSearchFromHomepageAction = feed(searchKey).feed(quantity)
    .exec(http(requestName0)
      .get("/s")
      .queryParam("wd", "${search_keys}")
      .queryParam("rsv_spt", "${quantity}")
      .check(status.is(200)))

}
