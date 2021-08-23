package controllers

import org.joda.time.DateTime
import controllers.requests.{GuestProfileRequest, OrganisationRequest, ProfileType}
import controllers.responses.AuthResponse
import play.api.mvc.AbstractController
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.AuthService

import helpers.Utilities.convertLongToDateTime

@Singleton
class ResidentController  @Inject()(
                                     val cc: ControllerComponents
                                   ,  val authService: AuthService,
                                   )  extends AbstractController(cc) {

  //GuestProfileRequest
  //todo: create profile
  def create =Action.async{ implicit  request =>
    //Authorization
    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)

    //todo: read the body params
    val surname = request.body.asJson.get("surname").as[String]
    val otherName = request.body.asJson.get("otherName").as[String]
    val profiletype = request.body.asJson.get("profiletype").as[String]
    val gender = request.body.asJson.get("gender").as[String]

    //get a long register date
    val registerDate =  request.body.asJson.get("registerdate").as[Long]

    val regDate:DateTime =  convertLongToDateTime(registerDate)


    val profileRequest = GuestProfileRequest(surname,otherName,ProfileType.withName(profiletype),gender)

    //todo: send this to the middleware and move on


    ???
  }

  //todo: view list items
  def listByStation( offset: Int, limit: Int,  options:String) = Action.async { implicit request =>

    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)


    ???
  }

  //todo: update the profile
  def update =Action.async{ implicit  request =>
    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)


    ???
  }
  //todo: delete profile
  def Archive =Action.async{ implicit  request =>

    ???
  }

  //todo: restore account
  def Restore =Action.async{ implicit  request =>

    ???
  }
}
