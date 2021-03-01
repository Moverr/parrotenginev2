package controllers

import java.util.NoSuchElementException

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.AuthResponse
import implicits.AuthResponseWrites._
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.libs.json.Reads._
import play.api.mvc._
import services.AuthService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


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
      val result = authService.validate(loginRequest)
      result .flatMap{
        case Some(response) =>  Future.successful(Ok(Json.toJson(response)))
        case None => Future.successful(Unauthorized("Invalid User Credentials"))
      }

    }
    catch {
      case e:NoSuchElementException =>Future.successful(BadRequest("Invalid Requeust body "))
      case x :NullPointerException => Future.successful(InternalServerError(x.getMessage))
      case e:Exception => Future.successful(InternalServerError(e.getMessage))
    }

  }


   def register   = Action.async{ implicit request =>
     try {

       val email = request.body.asJson.get("email").as[String]
       val password =  request.body.asJson.get("password").as[String]
       val registrationRequest =  RegisterRequest(email,password)
       val response:AuthResponse =  authService.register(registrationRequest)
       Future.successful(Ok(Json.toJson(response)))
     }
     catch {
       case e:Exception => Future.successful(BadRequest(e.getMessage))
     }


   }


  def validate   = Action.async{ implicit request =>
    try{
      val token = request.body.asJson.get("token").as[String]
      val result = authService.validateToken(token)
      result .flatMap{
        case Some(response) =>  Future.successful(Ok(Json.toJson(response)))
        case None => Future.successful(Unauthorized("Invalid User Credentials"))
      }

    }
    catch {
      case e:NoSuchElementException =>Future.successful(BadRequest("Invalid Requeust body "))
      case x :NullPointerException => Future.successful(InternalServerError(x.getMessage))
      case e:Exception => Future.successful(InternalServerError(e.getMessage))
    }

  }



}
