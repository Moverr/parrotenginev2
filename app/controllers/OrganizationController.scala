package controllers

import play.api.mvc._
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.mvc.Action
import services.OrganizationService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class OrganizationController  @Inject()(val cc: ControllerComponents,val orgservice: OrganizationService)
  extends AbstractController(cc){

  //todo: create Organization
  def create = Action.async { implicit  request => 

    Future.successful(Ok)
  }


  //todo: list Organization

  //todo: Get Organization by Id


}

//noted : level. topics >>
