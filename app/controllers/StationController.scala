package controllers

import controllers.requests.StationRequest
import controllers.responses.{AuthResponse, StationResponse}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents}
import services.{AuthService, StationService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import implicits.StationResponseWrites._

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
    val organization_id = request.body.asJson.get("organization_id").as[String]
    val name = request.body.asJson.get("name").as[String]
    val code = request.body.asJson.get("code").as[String]
    val stationRequest: StationRequest = StationRequest(organization_id.toInt, name, code)


    val response: Either[java.lang.Throwable, Future[StationResponse]] = stationService.create(authResponse, stationRequest)
    try {
      response match {
        case Left(exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
        case Right(value) =>  value.flatMap(response=> Future.successful(Ok(Json.toJson(response))) )


      }
    }
    catch {
      case e: Exception => Future.successful(InternalServerError(e.getMessage))
    }


  }

  //todo: List
  def list(organisation_id: Int, offset: Int, limit: Int) = Action.async { implicit request =>

    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)

    var result = stationService.list(authResponse, organisation_id, offset, limit)

    result match {
      case Left(exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
      case Right(result) => result flatMap {
        result => Future.successful(Ok(Json.toJson(result)))
      }
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
