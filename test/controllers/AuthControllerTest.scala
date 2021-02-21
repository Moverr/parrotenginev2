package controllers

import com.google.inject.Inject
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc.{ControllerComponents, Results}
import play.api.test._
import services.AuthService
import org.mockito.Mockito._

import scala.concurrent.Future


class AuthControllerTest  @Inject()(authService:AuthService) extends PlaySpec     {


  //val authService = new AuthService(null,null)

  "AuthControllerTest" should {
    "login"   in  {


      when(authService.validate(null)).thenReturn( Future.successful(None))

      val controller = new AuthController(Helpers.stubControllerComponents(), authService)
      
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
