package controllers

import controllers.requests.LoginRequest
import javax.inject.Inject
import play.api.mvc._
import services.AuthService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class AuthController @Inject()(
                                cc:ControllerComponents
                                ,authService: AuthService
                              )
  extends AbstractController(cc){

  def login() = Action.async { implicit request: Request[AnyContent] =>
    val body = request.body
    val jsonBody= body.asJson

    jsonBody.map{ requestBody =>
      //note. using to string adds "" charactes to the string. use as[String]
      val username:String =  requestBody("username").as[String]
      val password = requestBody("password").as[String]

      val loginRequest = LoginRequest(username, password)
       authService.validate(loginRequest)
        .map{
          x=>x
        }
      Future.successful(Ok("Intersting  Vidbes"))
       /* .flatMap{
          case value =>  Future.successful(Ok("Intersting  Vidbes"))
          case None => Future.successful(BadRequest(loginRequest.username.toString().trim()))
        }*/
      /*flatMap {
        case Some(value) => Future.successful(Ok("Intersting  Vidbes"))
        case None => Future.successful(BadRequest(loginRequest.username.toString().trim()))
      }
      */
    }.getOrElse{
      Future.successful(Ok("Scenario Fake "))
    }


    }

  def register(): Unit ={
    TODO
  }
}
