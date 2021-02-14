package services

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.LoginResponse
import daos.{IUserDAO, UserDao}
import db.tables.User
import helpers.Utilities
import javax.inject.{Inject, Singleton}

import scala.collection.immutable.LinearSeq
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@Singleton
class AuthService @Inject()( userDa: UserDao )   {

  //todo: Login Function
   def validate(loginRequest: LoginRequest): Future[Option[LoginResponse]] = {

    val response:Future[Option[User]] =  userDa.getUserByUsernameAndPassword(loginRequest.username,Utilities.encrypt(loginRequest.password))

    response.flatMap {
      case Some(value) =>  Future.successful(populateResponse(value))
      case None => Future.successful(None)
    }

  }


  //todo: Register Function
   def register(registerRequest: RegisterRequest): Future[LoginResponse] ={
     //todo: cehck to see that email exists
     val existingUser:Seq[User] =   Await.result( userDa.getUsersByUsername(registerRequest.email),Duration.Inf)

     if(existingUser.head.isInstanceOf[User]) throw new Exception("User already Exists")

    val response:Future[User] = userDa.createUserAccount(registerRequest.email,Utilities.encrypt(registerRequest.password))
     response.map(res => populateBasic(res))
  }

  //todo : populate Response
  def populateResponse(user: User): Option[LoginResponse]  =  {
    val resp: LoginResponse = populateBasic(user)
    Some(resp)
  }


  private def populateBasic(user: User): LoginResponse = {
    val resp = LoginResponse("token", user.username)
    resp
  }


}
