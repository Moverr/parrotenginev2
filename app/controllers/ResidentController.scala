package controllers

import play.api.mvc.AbstractController
import javax.inject.{Inject, Singleton}
import play.api.mvc._

@Singleton
class ResidentController  @Inject()(val cc: ControllerComponents)  extends AbstractController(cc) {

  //todo: create profile
  def create =Action.async{ implicit  request =>

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
