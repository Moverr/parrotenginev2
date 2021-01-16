package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}


class AuthController @Inject()(cc:ControllerComponents)   extends AbstractController(cc){

  def login() = Action {request=>
    val loginVals = request.body.asJson

    Ok("Passing"+loginVals)
  }

  def register(): Unit ={
    TODO
  }
}
