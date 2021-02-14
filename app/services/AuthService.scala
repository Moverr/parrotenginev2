package services

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.LoginResponse
import daos.{IUserDAO, UserDao}
import db.tables.User
import helpers.Utilities
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class AuthService @Inject()( userDa: UserDao )   {

  //todo: Login Function
   def validate(loginRequest: LoginRequest): Future[Option[LoginResponse]] = {

    val response:Future[Option[User]] =  userDa.getUserByNameAndPassord(loginRequest.username,Utilities.encrypt(loginRequest.password))

    response.flatMap {
      case Some(value) =>  Future.successful(populateResponse(value))
      case None => Future.successful(None)
    }

  }


  //todo: Register Function
   def register(registerRequest: RegisterRequest): Unit ={
     //todo: throw an error.

    ???
  }

  //todo : populate Response
  def populateResponse(user: User): Option[LoginResponse]  =  {
    val resp =  LoginResponse("token",user.username)
    Some(resp)
  }



}
