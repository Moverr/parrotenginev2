package controllers

import controllers.requests.OrganisationRequest
import controllers.responses.AuthResponse
import play.api.mvc._
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.mvc.Action
import services.{AuthService, OrganizationService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@Singleton
class OrganizationController  @Inject()(val cc: ControllerComponents,
                                        val orgservice: OrganizationService,
                                        val authService:AuthService
                                       )
  extends AbstractController(cc){

  //todo: create Organization
  def create = Action.async { implicit  request =>
    //todo: read the header params
    val authorization:String = request.headers.get("authorization").getOrElse("")
    val authResponse  =  Await.result(authService.validateToken(authorization),Duration.Inf)

    val auth:AuthResponse = authService.validateToken(authorization)
      .onComplete(f=>f.fold())

    //todo: read the body params
    val name = request.body.asJson.get("name").as[String]
    val details =  request.body.asJson.get("details").as[String]
    val orgRequest =   OrganisationRequest(name,details)

    orgservice.create(authResponse,orgRequest)
        .flatMap{
          x=>Future.successful(Ok("Writable Select"))
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
