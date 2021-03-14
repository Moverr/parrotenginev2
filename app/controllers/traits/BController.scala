package controllers.traits

import controllers.responses.AuthResponse
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.AuthService
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

abstract  class BController @Inject()( val authService:AuthService,
                                       val cc: ControllerComponents) extends AbstractController(cc){
  def validateToken(authorization:String ): Unit ={

    val authResponse:Option[AuthResponse] = (authService.validateToken(authorization)
      .map(response=>response).value.get).get

    //todo: Matching
    authResponse match {
      case Some(value) => ???
      case None =>   Future.successful(Unauthorized("You are not authorized to this item "))
    }
  }
}
