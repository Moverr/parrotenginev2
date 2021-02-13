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

  def login = Action.async{ implicit request  =>

    try{
      val username = request.body.asJson.get("username").as[String]
      val password =  request.body.asJson.get("password").as[String]


      val loginRequest = LoginRequest(username, password)
      authService.validate(loginRequest)
        .flatMap{
          case Some(value) => Future.successful(Ok( value.access_token))
          case None => Future.successful(BadRequest("Something went wrong"))
        }

    }
    catch {
      case e:NoSuchElementException =>Future.successful(BadRequest("Invalid Requeust body "))
      case _ => Future.successful(InternalServerError("Something went wrong, contatct adminstrator"))
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
