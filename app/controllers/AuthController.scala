package controllers

import controllers.requests.LoginRequest
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.AuthService


class AuthController @Inject()(
                                cc:ControllerComponents
                              ,authService: AuthService

                              )   extends AbstractController(cc){

  def login() = Action {request=>
    val loginVal = request.body.asJson
    loginVal.map{ arg=>

      try{
        val username = arg("username").toString()
        val password = arg("password").toString()
        val loginRequest =  LoginRequest(username,password)
        val response =  authService.validate(loginRequest)
        Ok(response.toString)
      }
      catch {
        case e:NoSuchElementException => BadRequest("Invalid Request")

      }

    }.getOrElse(Ok("Invalid Entries "))


  }

  def register(): Unit ={
    TODO
  }
}
