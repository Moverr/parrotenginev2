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
}
