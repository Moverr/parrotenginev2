package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}


class AuthController @Inject()(cc:ControllerComponents)   extends AbstractController(cc){

  def login() = Action {
    Ok("Passing")
  }

  def register(): Unit ={
    TODO
  }
}
