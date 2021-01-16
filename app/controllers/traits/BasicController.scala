package controllers.traits

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

abstract  class BasicController @Inject()(cc: ControllerComponents) extends AbstractController(cc){
  def validateToken(): Unit ={
    TODO
  }
}
