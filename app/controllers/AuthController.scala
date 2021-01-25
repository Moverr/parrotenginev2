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
      val username = "dd"
      val password = "ddd"
      val loginRequest = LoginRequest(username, password)
      authService.validate(loginRequest) map { items =>
        Ok(x.toString)
      }
    }.getOrElse{
      Future.successful(Ok("Scenario Fake "))
    }



    }

  def register(): Unit ={
    TODO
  }
}
