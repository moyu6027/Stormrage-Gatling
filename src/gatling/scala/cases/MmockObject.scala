package cases

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object MmockObject extends baseObject {

	lazy val requestName = "Monster-Mock Performance Test Object"

	val monsterMock: ChainBuilder = {

		exec(http(requestName)
		.post("/body/form")
		.check(status.is(200)))
		.exitHereIfFailed
	}


}
