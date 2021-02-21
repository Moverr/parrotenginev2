package utitlities

import org.scalatest.FunSuite
import org.scalatestplus.play.PlaySpec

class JwtUtilityTest extends PlaySpec {

  "Test KeySize" should{
    "should be 2048 " in {
      val x = new JwtUtility()
      x.keySize mustBe(2048)
    }
  }


  "Test generateKey" should{
    val x = new JwtUtility()

    "Should return the original text after encryption  " in {
       val encryptedResponse =  x.generateKey("key")
        x.retrievPasswordPair(encryptedResponse) mustBe("key")
    }
  }



}
