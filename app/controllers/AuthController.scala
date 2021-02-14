package controllers

import java.util.NoSuchElementException

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.LoginResponse
import javax.inject.{Inject, Singleton}
import play.api.libs.json.{Json, Writes}
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

  implicit val jsonWrites: Writes[LoginResponse] = Json.writes[LoginResponse]



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
  }


   def register   = Action.async{ implicit request =>
     try {
       val email = request.body.asJson.get("email").as[String]
       val password =  request.body.asJson.get("password").as[String]
       val registrationRequest =  RegisterRequest(email,password)
       val response:Future[LoginResponse] =  authService.register(registrationRequest)
       Future.successful(Ok(Json.toJson(response)))
     }
     catch {
       case e:Exception => Future.successful(BadRequest(e.getMessage))
     }


   }
}
