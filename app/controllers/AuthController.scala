package controllers

import controllers.requests.LoginRequest
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.AuthService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class AuthController @Inject()(
                                cc:ControllerComponents
                                ,authService: AuthService
                              )
  extends AbstractController(cc){

  def login() = Action.async { implicit request: Request[AnyContent] =>

    val username:String = request.body.asFormUrlEncoded.flatMap(m => m.get("username").flatMap(_.headOption)).getOrElse("")
    val password:String =  request.body.asFormUrlEncoded.flatMap(m => m.get("name").flatMap(_.headOption)).getOrElse("")
    val loginRequest = LoginRequest(username, password)

    authService.validate(loginRequest)
      .flatMap{
        case Some(value) => Future.successful(Ok( value.access_token))
        case None => Future.successful(BadRequest("Something went wrong"))
      }


    }

  def register(): Unit ={
    TODO
  }
}
