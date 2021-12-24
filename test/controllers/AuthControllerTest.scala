package controllers

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.UserResponse
import db.tables.User
import helpers.Utilities
import org.mockito.Mockito
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
import services.AuthService
import utitlities.JwtUtility

import scala.concurrent.Future


class AuthControllerTest extends PlaySpec     {


  val user:User  = new User(1,"moverr@gmail.com","Password")
  val pairString:String = "moverr@gmail.com:Password"
  val request:LoginRequest = new LoginRequest(user.username,user.password)

  val authResponse2:UserResponse  =    UserResponse(JwtUtility.generateKey(pairString), user.username,0L)
  val authResponse: Either[java.lang.Throwable,UserResponse]  =   Right(authResponse2)

  val jsonLoginRequest = Json.parse("{\"username\":\""+user.username+"\", \"password\":\""+user.password+"\" }")
  val jsonRegistrationRequest = Json.parse("{\"email\":\""+user.username+"\", \"password\":\""+user.password+"\" }")



  "AuthController login" should {
    val authService:AuthService = Mockito.mock(classOf[AuthService])
    "Return positive regisiter "   in  {


      Mockito.when(authService.validate(request)).thenReturn(Future.successful(Some(authResponse2)))

      val controller   = new AuthController(Helpers.stubControllerComponents(),authService)
      val response = controller.login().apply(FakeRequest(Helpers.POST, "/v1/auth/login").withJsonBody(jsonLoginRequest))
      val bodyText: String = contentAsString(response)

      val expectedResult:UserResponse = Utilities.fromJson[UserResponse](bodyText)
      expectedResult.username mustBe user.username
    }


  }



  "AuthController register" should {
    val registrationRequest:RegisterRequest = RegisterRequest(user.username,user.password)

    val authService:AuthService = Mockito.mock(classOf[AuthService])
    "Return positive login "   in  {

      Mockito.when(authService.register(registrationRequest)).thenReturn(authResponse)

      val controller   = new AuthController(Helpers.stubControllerComponents(),authService)
      val response = controller.register().apply(FakeRequest(Helpers.POST, "/v1/auth/register").withJsonBody(jsonRegistrationRequest))
      val bodyText: String = contentAsString(response)

      val expectedResult:UserResponse = Utilities.fromJson[UserResponse](bodyText)
      expectedResult.username mustBe user.username
    }


  }




}
