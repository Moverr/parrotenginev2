package controllers

import javax.inject.Inject
import play.api.mvc.{BaseController, ControllerComponents}

class AuthController @Inject()(cc: ControllerComponents)  extends  BaseController(cc){

  def  login(): Unit ={
    TODO
  }
  def register(): Unit ={
    TODO
  }
}
