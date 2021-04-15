package controllers

import controllers.requests.OrganisationRequest
import controllers.responses.{AuthResponse, OrganisationResponse}
import controllers.traits.BController
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import services.{AuthService, OrganizationService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import implicits.OrganizationResponseWrites._

@Singleton
class OrganizationController  @Inject()(
                                         val orgService: OrganizationService,
                                         val authService:AuthService,
                                         val cc: ControllerComponents

                                       )
  extends AbstractController(cc){

  //todo: create Organization
  def create = Action.async { implicit  request =>
    //todo: read the header params
    val authorization:String = request.headers.get("authorization").getOrElse("")
    val authResponse:AuthResponse = authService.validateToken(authorization)

    //todo: read the body params
    val name = request.body.asJson.get("name").as[String]
    val details =  request.body.asJson.get("details").as[String]
    val orgRequest =   OrganisationRequest(name,details)

    try{
    orgService.create(authResponse,orgRequest)
    match {
        case Left(exception) =>Future.successful(BadRequest(Json.toJson(exception.getMessage)))
        case Right(value) =>value.flatMap{
          result=>Future.successful(Ok(Json.toJson(result)))
        }
      }

    }
    catch {
      case e:Exception=>Future.successful(InternalServerError(e.getMessage))
    }

  }


  //todo: list Organization
def list(offset:Int,limit:Int) = Action.async{  implicit  request =>
  val authorization:String = request.headers.get("authorization").getOrElse("")
  val authResponse:AuthResponse = authService.validateToken(authorization)
  try {
     orgService.list(authResponse, limit, offset)
     match {
      case Left(exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
      case Right(result) => result flatMap {
                            result => Future.successful(Ok(Json.toJson(result)))
                            }
    }

  }
  catch {
    case e:Exception=>Future.successful(InternalServerError(e.getMessage))
  }

}
  //todo: Get Organization by Id

  def get(id:Int) = Action.async{  implicit  request =>
    val authorization:String = request.headers.get("authorization").getOrElse("")
    val authResponse:AuthResponse = authService.validateToken(authorization)
    try {


    orgService.get(authResponse,id)

        match {
        case Left(exception) => Future.successful(BadRequest(Json.toJson(exception.getMessage)))
        case Right(result) =>  result.flatMap{
            result =>
                 Future.successful(Ok(Json.toJson(result.get)))
      }
      }

    }
    catch {
      case e:Exception=>Future.successful(InternalServerError(e.getMessage))
    }

  }
}

//noted : level. topics >>
