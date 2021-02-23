package helpers

import org.scalatest.FunSuite
import org.scalatestplus.play.PlaySpec

class UtilitiesTest extends PlaySpec {

  "matchEncryption" should{
    "Should return the correct pattern" in {
      Utilities.matchEncryption(2) mustBe("AES")
    }

    "Should return the default  pattern" in {
      Utilities.matchEncryption(898) mustBe("MD5")
    }

  }


  "matchRegex" should{
    "Should return the correct regex expression" in {
      Utilities.matchRegex("email") mustBe(Utilities.emailRegex)
    }

    "Should return  empty string " in {
      Utilities.matchRegex("ssjsjs") mustBe("")
    }

  }


}
