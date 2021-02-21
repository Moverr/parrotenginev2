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

}
