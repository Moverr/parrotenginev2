package controllers

import controllers.requests.LoginRequest
import javax.inject.Inject
import play.api.mvc._
import services.AuthService

import scala.concurrent.ExecutionContext.Implicits.global


class AuthController @Inject()(
                                cc:ControllerComponents
                                ,authService: AuthService
                              )
  extends AbstractController(cc){

  def login() = Action.async { implicit request: Request[AnyContent] =>
    val body = request.body

    val username = "dd"
    val password = "ddd"
    val loginRequest = LoginRequest(username, password)
    authService.validate(loginRequest) map { items =>
      Ok(items.password.toString)
    }

    }

  def register(): Unit ={
    TODO
  }
}
