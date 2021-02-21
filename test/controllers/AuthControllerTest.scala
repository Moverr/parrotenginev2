package controllers

import com.google.inject.Inject
import db.tables.User
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc.{AnyContent, ControllerComponents, EssentialAction, Result, Results}
import play.api.test._
import play.api.test.Helpers._

import services.AuthService
import org.mockito.Mockito._
import play.api.libs.json.Json


import scala.concurrent.{Await, Future}


class AuthControllerTest extends PlaySpec     {
  
  val authService:AuthService = new AuthService(null,null)
  //val authService = new AuthService(null,null)
  val user:User  = new User(1,"username","password")

  "AuthControllerTest" should {
    "login"   in  {

      when(authService.validate(null)).thenReturn( Future.successful(authService.populateResponse(user)))
      val controller = new AuthController(Helpers.stubControllerComponents(), authService)
      val json = Json.parse("{\"username\":\"username\", \"password\":\"password\" }")


      val result: Future[Result] = controller.login().apply(FakeRequest(Helpers.POST, "/v1/auth/login").withJsonBody(json))


      val bodyText: String = contentAsString(result)
      println(bodyText)
      bodyText mustBe "ok"


    }

    "register" in {

    }
  }
}
