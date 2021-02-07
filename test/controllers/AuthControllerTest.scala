package controllers

import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.IAuthService

class AuthControllerTest  (authservice: IAuthService) extends PlaySpec {

  "AuthControllerTest" should {
    "login" in {
      val controller = new AuthController(stubControllerComponents(),authservice )
      val request = FakeRequest(POST, "/")
        .withJsonBody()


      val home = controller.login().apply(request)

    }

    "register" in {

    }
  }
}
