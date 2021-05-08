package controllers

import controllers.responses.AuthResponse
import javax.inject.{Inject, Singleton}
import play.api.mvc.{BaseController, ControllerComponents}
import services.AuthService

import scala.concurrent.Future


@Singleton
class StationController @Inject()(
                                   val controllerComponents: ControllerComponents
                                   ,val authService:AuthService
                                 ) extends BaseController  {

  //todo: Create
  def create()= Action.async {  implicit  request =>
    val authorization:String = request.headers.get("authentication").getOrElse("")
    val authResponse:AuthResponse = authService.validateTokenv2(authorization)

    //todo: read the body params
    val organization_id = request.body.asJson.get("organization_id").as[String]
    val name =  request.body.asJson.get("name").as[String]
    val code =  request.body.asJson.get("code").as[String]



    Future.successful(Ok)
  }
  //todo: List
  def list(): Unit = {

  }

  //todo: Update
  def update():Unit={

  }
    //todo: Archive
  def archive():Unit={

  }
}
