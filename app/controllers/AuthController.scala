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

    jsonBody.map{x =>
      val username = x("username").toString
      val password = x("password").toString
      val loginRequest = LoginRequest(username, password)
      authService.validate(loginRequest) map { items =>
        Ok(x("password"))
      }
    }.getOrElse{
      Future.successful(Ok("Scenario Fake "))
    }



    }

  def register(): Unit ={
    TODO
  }
}
