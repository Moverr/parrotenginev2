package services

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.AuthResponse
import daos.UserDao
import db.tables.User
import helpers.Utilities
import javax.inject.{Inject, Singleton}
import utitlities.JwtUtility

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}



@Singleton
class AuthService @Inject()(userDao: UserDao,jwtUtility: JwtUtility )   {


  //todo: Login Function
   def validate(loginRequest: LoginRequest): Future[Option[AuthResponse]] = {

    val response =  userDao.getUserByUsernameAndPassword(loginRequest.username,Utilities.encrypt(loginRequest.password))

    response.flatMap{
      case None => Future.successful(None)
      case Some(value) => Future.successful(populateResponse(value))
    }

  }

   def register(registerRequest: RegisterRequest): AuthResponse ={
     val existingUser:Seq[User] =   Await.result( userDao.getUsersByUsername(registerRequest.email),Duration.Inf)
     if(existingUser.length > 0 ){
       throw new Exception("User already exists in the system ")
     }

     val res =  Await.result(userDao.createUserAccount(registerRequest.email,Utilities.encrypt(registerRequest.password)),Duration.Inf)
     populateBasic(res)
  }


  def populateResponse(user: User): Option[AuthResponse]  =  {
    val resp: AuthResponse = populateBasic(user)
    Some(resp)
  }

  private def populateBasic(user: User): AuthResponse = {
    val pairString:String = user.username+":"+user.password
    AuthResponse(jwtUtility.generateKey(pairString), user.username)
  }





}
