package controllers

import java.util.NoSuchElementException

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.AuthResponse
import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsPath, Json, Writes}
import play.api.mvc._
import services.{AuthService, IAuthService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.mvc.Http

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._


@Singleton
class AuthController @Inject()(
                                 cc:ControllerComponents
                                ,authService: AuthService
                              )
  extends AbstractController(cc) {

  case class Location(lat: Double, long: Double)
  case class Place(name: String, location: Location)

  implicit val locationWrites: Writes[Location] = (
    (JsPath \ "lat").write[Double] and
      (JsPath \ "long").write[Double]
    )(unlift(Location.unapply))



  implicit val placeWrites: Writes[Place] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "location").write[Location]

    )(unlift(Place.unapply))



  implicit val jsonWrites: Writes[AuthResponse] = Json.writes[AuthResponse]



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
       val response:Future[AuthResponse] =  authService.register(registrationRequest)


       val place = Place(
         "Watership Down",
         Location(51.235685, -1.309197)
       )

       Future.successful(Ok(Json.toJson(place)))
     }
     catch {
       case e:Exception => Future.successful(BadRequest(e.getMessage))
     }


   }
}
