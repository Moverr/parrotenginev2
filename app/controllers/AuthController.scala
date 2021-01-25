package controllers

import controllers.requests.LoginRequest
import javax.inject.Inject
import play.api.mvc._
import services.AuthService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class AuthController @Inject()(
                                cc:ControllerComponents
                                ,authService: AuthService
                              )
  extends AbstractController(cc){

  def login() = Action.async { implicit request: Request[AnyContent] =>
    val body = request.body
    val jsonBody= body.asJson

    jsonBody.map{ requestBody =>
      val username = requestBody("username").toString
      val password = requestBody("password").toString
      val loginRequest = LoginRequest(username, password)
      authService.validate(loginRequest)  flatMap{
        case Some(value) => Future.successful(Ok("Intersting  Vidbes"))
        case None => Future.successful(BadRequest("Records missing Bive"))
      }
    }.getOrElse{
      Future.successful(Ok("Scenario Fake "))
    }



    }

  def register(): Unit ={
    TODO
  }
}
