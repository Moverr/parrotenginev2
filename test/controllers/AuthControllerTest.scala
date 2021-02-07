package controllers

import org.scalatestplus.play.PlaySpec
import services.IAuthService
import play.api.test.Helpers._

class AuthControllerTest  (authservice: IAuthService) extends PlaySpec {

  "AuthControllerTest" should {
    "login" in {
      val controller = new AuthController(stubControllerComponents(),authservice )
    }

    "register" in {

    }
  }
}
