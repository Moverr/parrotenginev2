package controllers

import akka.stream.ActorMaterializer
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.mvc.AnyContent
import play.api.test.{FakeRequest, Injecting}
import play.api.test.Helpers._



class AuthControllerTest  extends PlaySpec  with Injecting  {

  val controller  = inject[AuthController]
  "AuthControllerTest" should {
    "login" in {
       val controller = new AuthController(stubControllerComponents(), null)
      val json = Json.parse("{\"firstName\":\"Foo\", \"lastName\":\"Bar\", \"age\":13}")


      val request:FakeRequest[AnyContent] = FakeRequest(POST, "/v1/auth/login").withJsonBody(json)
      val result = controller.login().apply(request)

      status(result) mustBe OK

    }

    "register" in {

    }
  }
}
