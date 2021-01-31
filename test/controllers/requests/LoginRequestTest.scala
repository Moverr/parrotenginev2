package controllers.requests

import org.scalatestplus.play.PlaySpec

class LoginRequestTest extends PlaySpec {
val x = new LoginRequest("username","password")
  "Login Request" should {
    "Username should initialize  Properly" in {
      assert(x.username == "username")
    }

    "Password should initialize  Properly" in {
      assert(x.password == "password")
    }


  }
}
