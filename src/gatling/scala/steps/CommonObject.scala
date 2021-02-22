package steps

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import cn.homecredit.qc.common.util.{BankcardImages, ChineseNames}

import java.io.File

object CommonObject {
	lazy val bankcardNumber = "6227008888888888888"

	val bankCardImage: File = BankcardImages.createFrontFile(bankcardNumber)

}
