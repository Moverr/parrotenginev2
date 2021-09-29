package controllers

import controllers.requests.{ProfileType, ResidentProfileRequest}
import controllers.responses.AuthResponse
import helpers.Utilities.convertLongToDateTime
import implicits.ResidentProfileWrites._
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, _}
import services.{AuthService, ResidentialService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class ResidentController @Inject()(
                                    val cc: ControllerComponents
                                    , val authService: AuthService
                                    , val residentialService: ResidentialService
                                  ) extends AbstractController(cc) {

  def profileType = "Resident";

  //GuestProfileRequest
  //todo: create profile
  def create = Action.async { implicit request =>
    //Authorization
    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)

    //todo: read the body params
    val surname: String = request.body.asJson.get("surname").as[String]
    val otherName: String = request.body.asJson.get("otherName").as[String]
    val profiletype: String = request.body.asJson.get("profiletype").as[String]
    val gender: String = request.body.asJson.get("gender").as[String]
    val stationid: Int = request.body.asJson.get("stationid").as[String].trim.toInt


    //get a long register date
    //    val registerDate:Option[Long] =  request.body.asJson.get("registerdate").asOpt[Long]

    val regDate: DateTime = convertLongToDateTime(request.body.asJson.get("registerdate").asOpt[Long])

    val profileRequest = ResidentProfileRequest(surname, otherName, ProfileType.withName(profileType), gender, stationid, regDate)

    //todo: send this to the middleware and move on


    try {
      residentialService.create(authResponse, profileRequest)
      match {
        case Left(exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
        case Right(value) => value.flatMap(x => Future.successful(Ok(Json.toJson(x))))

      }

    }
    catch {
      case e: Exception => Future.successful(InternalServerError(e.getMessage))
    }

  }

  /*
  -- List all the items that belong to me
  -- List all the itmes that belong to my station 

   */
  //todo: view list items
  def list(offset: Int, limit: Int, station_id: Option[Long],query:Option[String]) = Action.async { implicit request =>

    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)


    try {
      residentialService.list(authResponse, offset, limit, station_id,query) match {
        case Left(exception: Exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
        case Right(result) =>result.flatMap{
          result =>Future.successful(Ok(Json.toJson(result)))
        }
          //Future.successful(Ok(Json.toJson(value)))
      }
    }
    catch {
      case e: Exception => Future.successful(InternalServerError(e.getMessage))
    }



  }


  //todo: get item by id
  def get(id: Long) = Action.async { implicit request =>

    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)


    try {
      residentialService.get(authResponse, id) match {
        case Left(exception: Exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
        case Right(result) =>result.flatMap{
          result =>Future.successful(Ok(Json.toJson(result)))
        }

      }
    }
    catch {
      case e: Exception => Future.successful(InternalServerError(e.getMessage))
    }



  }


  //todo: update the profile
  def update = Action.async { implicit request =>
    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)


    ???
  }

  //todo: delete profile
  def Archive = Action.async { implicit request =>

    ???
  }

  //todo: restore account
  def Restore = Action.async { implicit request =>

    ???
  }
}
