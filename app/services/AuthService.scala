package services

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.AuthResponse
import daos.{IUserDAO, UserDao}
import db.tables.User
import helpers.Utilities
import javax.inject.{Inject, Singleton}
import play.api.libs.functional.syntax.unlift
import play.api.libs.json.{JsPath, Writes}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

import scala.util.{Failure, Success}



@Singleton
class AuthService @Inject()(userDao: UserDao )   {



  //todo: Login Function
   def validate(loginRequest: LoginRequest): Option[AuthResponse] = {

    val response:Future[Option[User]]=  userDao.getUserByUsernameAndPassword(loginRequest.username,Utilities.encrypt(loginRequest.password))



   response.map{
             case Some(value) => Some(populateBasic(value))
             case None => None
           }.map(x=>x.get(0))


     /*
     response  match {
       case Some(value) =>populateResponse(value)
       case None =>  None
     }*/

  }



   def register(registerRequest: RegisterRequest): AuthResponse ={
     val existingUser:Seq[User] =   Await.result( userDao.getUsersByUsername(registerRequest.email),Duration.Inf)

     val _po = userDao.getUsersByUsername(registerRequest.email

     if(existingUser.length > 0 ) throw new Exception("User already Exists")

     val res =  Await.result(userDao.createUserAccount(registerRequest.email,Utilities.encrypt(registerRequest.password)),Duration.Inf)
     populateBasic(res)
  }


  def populateResponse(user: User): Option[AuthResponse]  =  {
    val resp: AuthResponse = populateBasic(user)
    Some(resp)
  }

  private def populateBasic(user: User): AuthResponse = {

    val resp = AuthResponse("token", user.username)
    resp
  }


}
