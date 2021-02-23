package helpers

import controllers.requests.LoginRequest
import controllers.responses.AuthResponse
import db.tables.User
import org.scalatest.FunSuite
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json

class UtilitiesTest extends PlaySpec {

  "matchEncryption" should{
    "  return the correct pattern" in {
      Utilities.matchEncryption(2) mustBe("AES")
    }

    "  return the default  pattern" in {
      Utilities.matchEncryption(898) mustBe("MD5")
    }

  }


  "matchRegex" should{
    "  return the correct regex expression" in {
      Utilities.matchRegex("email") mustBe(Utilities.emailRegex)
    }

    "  return  empty string " in {
      Utilities.matchRegex("ssjsjs") mustBe("")
    }

  }


  "fromJson" should{
    val jsonBoy = Json.parse("{\"username\":\"username\", \"password\":\"password\" }")


    "   convert from json to a case class successfully" in {
     Utilities.fromJson[LoginRequest](jsonBoy.toString()).password mustBe("password")
    }



  }



}
