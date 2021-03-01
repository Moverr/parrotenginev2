package controllers

import controllers.requests.OrganisationRequest
import controllers.responses.AuthResponse
import play.api.mvc._
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.mvc.Action
import services.{AuthService, OrganizationService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class OrganizationController  @Inject()(val cc: ControllerComponents,
                                        val orgservice: OrganizationService,
                                        val authService:AuthService
                                       )
  extends AbstractController(cc){

  //todo: create Organization
  def create = Action.async { implicit  request =>
    //todo: read the header params
    val token = request.headers.get("authorization").get
    val authResponse:AuthResponse  = authService.validateToken(token).map[AuthResponse]


    //todo: read the body params
    val name = request.body.asJson.get("name").as[String]
    val details =  request.body.asJson.get("details").as[String]
    val orgRequest =   OrganisationRequest(name,details)
    //orgservice.create(orgRequest)
    Future.successful(Ok)
  }


  //todo: list Organization

  //todo: Get Organization by Id


}

//noted : level. topics >>
