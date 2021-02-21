package controllers

import com.google.inject.Inject
import db.tables.User
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc.{AnyContent, ControllerComponents, Results}
import play.api.test._
import services.AuthService
import org.mockito.Mockito._
import play.api.libs.json.Json
import play.test
import play.test.Helpers._

import scala.concurrent.Future


class AuthControllerTest  @Inject()(authService:AuthService) extends PlaySpec     {


  //val authService = new AuthService(null,null)
  val user:User  = new User(1,"username","password")

  "AuthControllerTest" should {
    "login"   in  {

      when(authService.validate(null)).thenReturn( Future.successful(authService.populateResponse(user)))
      val controller = new AuthController(Helpers.stubControllerComponents(), authService)
      val json = Json.parse("{\"username\":\"username\", \"password\":\"password\" }")
      val request:FakeRequest[AnyContent] = FakeRequest(POST, "/v1/auth/login").withJsonBody(json)
      val result = controller.login().apply(request)

      Helpers.status(result) mustBe Results.Ok
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
