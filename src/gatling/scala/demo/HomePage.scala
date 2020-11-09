package demo

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import singleObjects.Headers

object HomePage {

  lazy val requestName = "HomePage"

  val homePage = exec(http(requestName)
    .get("/")
    .headers(Headers.headersGet)
    //		.check(mCategoryFromHomepageChecks:_*)
    .check(status.is(200)))
    .exitHereIfFailed
}
