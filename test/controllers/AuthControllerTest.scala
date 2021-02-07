package controllers

import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import services.IAuthService
import play.api.test.Helpers._

class AuthControllerTest  (authservice: IAuthService) extends PlaySpec {

  "AuthControllerTest" should {
    "login" in {
      val controller = new AuthController(stubControllerComponents(),authservice )
      val home = controller.login().apply(FakeRequest(POST, "/"))

    }

    "register" in {

    }
  }
}
