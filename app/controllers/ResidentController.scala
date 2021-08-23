package controllers

import controllers.responses.AuthResponse
import play.api.mvc.AbstractController
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.AuthService

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


    ???
  }

  //todo: view list items
  def listByStation( offset: Int, limit: Int,  options:String) = Action.async { implicit request =>

    ???
  }

  //todo: update the profile
  def update =Action.async{ implicit  request =>

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
