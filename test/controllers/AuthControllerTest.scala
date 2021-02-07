package controllers

import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

class AuthControllerTest  extends PlaySpec   {

  "AuthControllerTest" should {
    "login" in {
       val controller = new AuthController(stubControllerComponents(), null)
      val json = Json.parse("{\"firstName\":\"Foo\", \"lastName\":\"Bar\", \"age\":13}")


      val request = FakeRequest(POST, "/v1/auth/login").withJsonBody(json)
      val home = controller.login().apply(request)

      status(home) mustBe OK
    }

    "register" in {

    }
  }
}
