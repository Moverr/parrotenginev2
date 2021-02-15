package services

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.AuthResponse
import daos.{IUserDAO, UserDao}
import db.tables.User
import helpers.Utilities
import javax.inject.{Inject, Singleton}


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@Singleton
class AuthService @Inject()(userDao: UserDao )   {



  //todo: Login Function
   def validate(loginRequest: LoginRequest): Future[Option[AuthResponse]] = {

    val response:Future[Option[User]] =  userDao.getUserByUsernameAndPassword(loginRequest.username,Utilities.encrypt(loginRequest.password))

    response.flatMap {
      case Some(value) =>  Future.successful(populateResponse(value))
      case None => Future.successful(None)
    }

  }


  //todo: Register Function
   def register(registerRequest: RegisterRequest): Future[AuthResponse] ={
     //todo: cehck to see that email exists
     val existingUser:Seq[User] =   Await.result( userDao.getUsersByUsername(registerRequest.email),Duration.Inf)

     if(existingUser.length > 0 ) throw new Exception("User already Exists")

    val response:Future[User] = userDao.createUserAccount(registerRequest.email,Utilities.encrypt(registerRequest.password))
     response.map(res => populateBasic(res))
  }

  //todo : populate Response
  def populateResponse(user: User): Option[AuthResponse]  =  {
    val resp: AuthResponse = populateBasic(user)
    Some(resp)
  }


  private def populateBasic(user: User): AuthResponse = {
    val resp = AuthResponse("token", user.username)
    resp
  }


}
