package controllers

import com.google.inject.Inject
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc.{ControllerComponents, Results}
import play.api.test._
import services.AuthService



class AuthControllerTest @Inject()(authservice:AuthService) extends PlaySpec  with Results   {


  "AuthControllerTest" should {
    "login"   in  {
      val controller = new AuthController(Helpers.stubControllerComponents(), authservice)
      
    /*   val controller = new AuthController(stubControllerComponents(), null)
      val json = Json.parse("{\"firstName\":\"Foo\", \"lastName\":\"Bar\", \"age\":13}")


      val request:FakeRequest[AnyContent] = FakeRequest(POST, "/v1/auth/login").withJsonBody(json)
      val result = controller.login().apply(request)

      status(result) mustBe OK */

    }

    "register" in {

    }
  }
}
