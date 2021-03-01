package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.OrganizationService

@Singleton
class OrganizationController @Inject()(cc: ControllerComponents,orgservice: OrganizationService) extends AbstractController(cc){

  //todo: create Organization

  //todo: list Organization

  //todo: Get Organization by Id


}

//noted : level. topics >>
