package controllers

import controllers.requests.StationRequest
import controllers.responses.{AuthResponse, StationResponse}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AnyContent, BaseController, ControllerComponents}
import services.{AuthService, StationService}

import scala.concurrent.Future


@Singleton
class StationController @Inject()(
                                   val controllerComponents: ControllerComponents
                                   , val authService: AuthService
                                   , val stationService: StationService
                                 ) extends BaseController {

  //todo: Create
  def create() = Action.async { implicit request =>
    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)

    //todo: read the body params
    val organization_id = request.body.asJson.get("organization_id").as[Int]
    val name = request.body.asJson.get("name").as[String]
    val code = request.body.asJson.get("code").as[String]
    val stationRequest: StationRequest = StationRequest(organization_id, name, code)


    val response: Either[java.lang.Throwable, Future[StationResponse]] = stationService.create(authResponse, stationRequest)
    try {
      response match {
        case Left(exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
        case Right(value) => Future.successful(Ok(Json.toJson(value)))
      }
    }
    catch {
      case e: Exception => Future.successful(InternalServerError(e.getMessage))
    }


  }

  //todo: List
  def list(organisation_id:Int,offset: Int, limit: Int)   = Action.async { implicit request =>

    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)

    stationService.list(authResponse,organisation_id,offset,limit)
      match {
      case Left(exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
      case Right(result) =>   Future.successful(Ok(Json.toJson(result)))
    }

  }

  //todo: Update
  def update(): Unit = {
    ???
  }

  //todo: Archive
  def archive(): Unit = {
    ???
  }
}
