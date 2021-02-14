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
   def register(registerRequest: RegisterRequest): Future[LoginResponse] ={
     //todo: throw an error.
    val response:Future[Long] =    userDa.createUserAccount(registerRequest.email,Utilities.encrypt(registerRequest.password))



     Future.successful(null)
  }

  //todo : populate Response
  def populateResponse(user: User): Option[LoginResponse]  =  {
    val resp: LoginResponse = populateResponse(user)
    Some(resp)
  }


  private def populateResponse(user: User): LoginResponse = {
    val resp = LoginResponse("token", user.username)
    resp
  }
}
