package controllers

import java.util.concurrent.CompletableFuture

import com.google.inject.Inject
import controllers.requests.LoginRequest
import controllers.responses.AuthResponse
import db.tables.User
import helpers.Utilities
import org.mockito.Mockito
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc.{AnyContent, ControllerComponents, EssentialAction, Result, Results}
import play.api.test._
import play.api.test.Helpers._
import services.AuthService
import org.mockito.Mockito._
import play.api.libs.json.Json
import org.mockito.Mockito._
import utitlities.JwtUtility

import scala.concurrent.{Await, Future}


class AuthControllerTest extends PlaySpec     {


  //val authService = new AuthService(null,null)
  val user:User  = new User(1,"moverr@gmail.com","P@ssword?123")
  val pairString:String = "moverr@gmail.com:P@ssword?123"



  "AuthControllerTest" should {
    "login"   in  {
      val request:LoginRequest = new LoginRequest("moverr@gmail.com","P@ssword?123")
      val result =   AuthResponse(JwtUtility.generateKey(pairString), user.password)
      val authService:AuthService = Mockito.mock(classOf[AuthService])
      Mockito.when(authService.validate(request)).thenReturn(Future.successful(Some(result)))
      val json = Json.parse("{\"username\":\"moverr@gmail.com\", \"password\":\"P@ssword?123\" }")
      val controller   = new AuthController(Helpers.stubControllerComponents(),authService)
      val p = controller.login().apply(FakeRequest(Helpers.POST, "/v1/auth/login").withJsonBody(json))
      val bodyText: String = contentAsString(p)

      val resu:AuthResponse = Utilities.fromJson[AuthResponse](bodyText)


      resu.username mustBe "moverr@gmail.com"
    }

    "register" in {

    }
  }
}
