package controllers

import java.util.NoSuchElementException

import controllers.requests.LoginRequest
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.{AuthService, IAuthService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.mvc.Http
@Singleton
class AuthController @Inject()(
                                 cc:ControllerComponents
                                ,authService: AuthService
                              )
  extends AbstractController(cc) {

  def login = Action { implicit request  =>

    try{
      val username = request.body.asJson.get("username")
      val password =  request.body.asJson.get("password")
      Ok(username)

    }
    catch {
      case e:NoSuchElementException => BadRequest("Invalid Requeust body ")
      case _ => InternalServerError("Something went wrong, contatct adminstrator")
    }



    /*
    val username:String = request.body.asFormUrlEncoded.flatMap(m => m.get("username").flatMap(_.headOption)).getOrElse("")
    val password:String =  request.body.asFormUrlEncoded.flatMap(m => m.get("password").flatMap(_.headOption)).getOrElse("")
    val loginRequest = LoginRequest(username, password)

    */


    /*
    authService.validate(loginRequest)
      .flatMap{
        case Some(value) => Future.successful(Ok( value.access_token))
        case None => Future.successful(BadRequest("Something went wrong"))
      }

    */
  }


  def register(): Unit ={
    TODO
  }
}
