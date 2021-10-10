package controllers

import controllers.responses.AuthResponse
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.AuthService

@Singleton
class GuestController @Inject()(
                                   controllerComponents: ControllerComponents
                                   , val authService: AuthService
                                 )  extends  AbstractController(controllerComponents) {

  //todo: create visiotr regirstration profile
  def create = Action.async { implicit request =>

    val authorization: String = request.headers.get("authentication").getOrElse("")
    val authResponse: AuthResponse = authService.validateTokenv2(authorization)



    ???

  }

  //todo; view registrations etcs,
}
