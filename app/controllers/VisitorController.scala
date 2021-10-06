package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class VisitorController @Inject()(controllerComponents: ControllerComponents)  extends  AbstractController(controllerComponents) {

  //todo: create visiotr regirstration profile
  def create = Action.async { implicit request => {

    ???
  }
  }

  //todo; view registrations etcs,
}
