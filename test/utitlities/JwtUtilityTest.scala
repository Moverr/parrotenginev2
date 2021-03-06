package utitlities

import org.scalatestplus.play.PlaySpec

class JwtUtilityTest extends PlaySpec {

  "Test KeySize" should{
    "  be 2048 " in {
      JwtUtility.keySize mustBe(2048)
    }
  }


  "Test generateKey" should{
    "  return the original text after encryption  " in {
      JwtUtility retrievePasswordPair(JwtUtility generateKey("key")) mustBe("key")
    }
  }



}
