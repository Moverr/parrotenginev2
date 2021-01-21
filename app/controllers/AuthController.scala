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
    val loginVals = request.body.asJson
    loginVals.map{ arg=>

      try{
        val username = arg("username").toString()
        val password = arg("password").toString()
        val loginRequest = new LoginRequest(username,password)
        authService.validate(loginRequest)
        Ok("Passing"+arg("name"))
      }
      catch {
        case e:NoSuchElementException=>{
          BadRequest("Invalid Request")
        }
      }


    }.getOrElse(Ok("Invalid Entries "))


  }

  def register(): Unit ={
    TODO
  }
}
