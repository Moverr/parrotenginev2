package controllers

import controllers.requests.OrganisationRequest
import controllers.responses.AuthResponse
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
                                        val orgservice: OrganizationService,
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

    orgservice.create(authResponse,orgRequest)
        .flatMap{
          result=>Future.successful(Ok(Json.toJson(result)))
        }

  }


  //todo: list Organization
def list(from:Int,limit:Int) = Action.async{

  Future.successful(Ok("Interestging"))
}
  //todo: Get Organization by Id

  def get(id:Int) = Action.async{

    Future.successful(Ok("Interestging"))
  }
}

//noted : level. topics >>
