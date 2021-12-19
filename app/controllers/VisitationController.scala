package controllers

import controllers.requests.{GuestProfileRequest, PhysicalAddress, ProfileRequest, ProfileType}
import controllers.responses.{AuthResponse, GuestInvitationResponse}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.{AuthService, VisitationService}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import implicits.GuestInvitationResponseWrites._

@Singleton
class VisitationController @Inject()(
                                 controllerComponents: ControllerComponents
                                 , val authService: AuthService
                                 , val visitationService: VisitationService
                               ) extends AbstractController(controllerComponents) {

  //todo: create visiotr regirstration profile
  def register = Action.async { implicit request =>


    val surname: String = request.body.asJson.get("surname").as[String]
    val otherName: String = request.body.asJson.get("otherName").as[String]
    val profileType: String = request.body.asJson.get("profiletype").as[String]
    val gender: String = request.body.asJson.get("gender").as[String]
    //    val stationid: Int = request.body.asJson.get("stationid").as[String].trim.toInt

    val registratioon_date_long: Long = request.body.asJson.get("registration_date").as[Long]
    val location: String = request.body.asJson.get("location").as[String]
    //host information
    val host_id = request.body.asJson.get("host_id").as[Long]


    val latitude: Double = 0.0
      ///request.body.asJson.get("location").as[Double]
    val longitude: Double =  0.0
      //request.body.asJson.get("location").as[Double]

    val address_location = PhysicalAddress(location, "LOCATION", Some(latitude), Some(longitude))


    //todo: guest profile reequest
    val guestRequest: ProfileRequest = GuestProfileRequest(surname, otherName, ProfileType.withName(profileType), gender, host_id, registratioon_date_long, address_location)

    val  response : Either[Throwable, Future[GuestInvitationResponse]] = visitationService.Invitation(guestRequest)

    try{
      response match {
        case Left(exception) =>  Future.successful(BadRequest(Json.toJson(exception.getMessage)))
        case Right(value) => value.flatMap(response=> Future.successful(Ok(Json.toJson(response))) )
      }

    }
    catch {

      case e:Exception =>  Future.successful(InternalServerError(e.getMessage))
    }


  }

  //todo; view registrations etcs,
  def list(organisation_id: Option[Int],station_id: Option[Int],kiosk_id: Option[Int], offset: Int, limit: Int ):Action[AnyContent] = Action.async{ implicit  request =>

    //todo: get me all the list items
    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: Future[Option[AuthResponse]] = authService.validateTokenv2(authorization)



    authResponse.flatMap(item=> item match {
      case Some(value) =>{

        visitationService.list(value, organisation_id,station_id,kiosk_id, offset, limit)

          match {
          case Left(exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
          case Right(result) => result flatMap {
            result => Future.successful(Ok(Json.toJson(result)))
          }
        }

      }
      case None => Future.successful(Unauthorized("User  not authorized"))
    })






  }
  //todo: view registrations on a given
}
