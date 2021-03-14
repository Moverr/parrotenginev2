package controllers.traits

import controllers.responses.AuthResponse
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.AuthService
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

abstract  class BController @Inject()( val authService:AuthService,
                                       val cc: ControllerComponents) extends AbstractController(cc){

}
