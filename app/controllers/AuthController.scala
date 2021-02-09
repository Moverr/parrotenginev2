package controllers

import controllers.requests.LoginRequest
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.IAuthService

@Singleton
class AuthController @Inject()(
                                cc:ControllerComponents
                                ,authService: IAuthService
                              )
  extends AbstractController(cc){

  def login() =   { implicit request: Request[AnyContent] =>

    val username:String = request.body.asFormUrlEncoded.flatMap(m => m.get("username").flatMap(_.headOption)).getOrElse("")
    val password:String =  request.body.asFormUrlEncoded.flatMap(m => m.get("password").flatMap(_.headOption)).getOrElse("")
    val loginRequest = LoginRequest(username, password)

    /*
    authService.validate(loginRequest)
      .flatMap{
        case Some(value) => Future.successful(Ok( value.access_token))
        case None => Future.successful(BadRequest("Something went wrong"))
      } */
    Ok("Sucucess");
    }

  def register(): Unit ={
    TODO
  }
}
