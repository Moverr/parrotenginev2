package controllers

import java.util.concurrent.CompletableFuture

import com.google.inject.Inject
import controllers.responses.AuthResponse
import db.tables.User
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
  val user:User  = new User(1,"username","password")
  val pairString:String = user.username+":"+user.password
  val result = AuthResponse(JwtUtility.generateKey(pairString), user.password)


  "AuthControllerTest" should {
    "login"   in  {
      val authService:AuthService = Mockito.mock(classOf[AuthService])
      Mockito.when(authService.validate(null)).thenReturn(Future.successful(Some(result)))
      val json = Json.parse("{\"username\":\"username\", \"password\":\"password\" }")
      val controller   = new AuthController(Helpers.stubControllerComponents(),authService)
      val p = controller.login().apply(FakeRequest(Helpers.POST, "/v1/auth/login").withJsonBody(json))
      val bodyText: String = contentAsString(p)
      println(bodyText)
      bodyText mustBe "Something went wrong, contatct adminstrator"
    }

    "register" in {

    }
  }
}
