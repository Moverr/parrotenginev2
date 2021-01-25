package controllers

import controllers.requests.LoginRequest
import db.tables.User
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.AuthService

import scala.concurrent.Future


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
        val response:Future[User] =  authService.validate(loginRequest)



        Ok(s"Inters=esting $username")

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
