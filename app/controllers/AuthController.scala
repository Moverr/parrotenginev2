package controllers

import controllers.traits.BasicController
import javax.inject.Inject
import play.api.mvc.ControllerComponents

class AuthController @Inject()(cc:ControllerComponents)   extends BasicController(cc){

  def  login(): Unit ={
    TODO
  }
  def register(): Unit ={
    TODO
  }
}
