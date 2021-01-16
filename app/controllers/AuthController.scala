package controllers

import controllers.requests.LoginRequest
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}


class AuthController @Inject()(cc:ControllerComponents)   extends AbstractController(cc){

  def login() = Action {request=>
    val loginVals = request.body.asJson
    loginVals.map{ arg=>
      val username = arg("username").toString()
      val password = arg("password").toString()
      val authentication = new LoginRequest(username,password)

      Ok("Passing"+arg("name"))


    }.getOrElse(Ok("Invalid Entries "))


  }

  def register(): Unit ={
    TODO
  }
}
