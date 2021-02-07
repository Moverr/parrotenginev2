package controllers

import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.IAuthService

class AuthControllerTest  (authservice: IAuthService) extends PlaySpec {

  "AuthControllerTest" should {
    "login" in {
      val json = Json.parse("{\"firstName\":\"Foo\", \"lastName\":\"Bar\", \"age\":13}")

      val controller = new AuthController(stubControllerComponents(),authservice )
      val request = FakeRequest(POST, "/v1/auth/login").withJsonBody(json)



      val home = controller.login().apply(request)

    }

    "register" in {

    }
  }
}
