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
class AuthService @Inject()(userDao: UserDao )   {


  //todo: Login Function
   def validate(loginRequest: LoginRequest): Future[Option[AuthResponse]] = {

    val response =  userDao.getUserByUsernameAndPassword(loginRequest.username,Utilities.encrypt(loginRequest.password))

    response.flatMap{
      case None => Future.successful(None)
      case Some(value) => Future.successful(populateResponse(value,loginRequest))
    }

  }

  //validate token
  def validateToken(authorizationToken: String):Either[java.lang.Throwable, Future[Option[AuthResponse]] ]= {
    if(authorizationToken == "")    Left( new Exception("You are not authorized to this item "))
    Right(validate(decryptPairString(authorizationToken)).map(x=>x))
  }


  //validate Token Overloading
  def validateTokenv2(authorizationToken: String): AuthResponse=  Await.result( validate( decryptPairString(JwtUtility.retrievePasswordPair(authorizationToken))).map(x=>x.get),Duration.Inf)

  def register(registerRequest: RegisterRequest): Either[java.lang.Throwable,AuthResponse] ={
     val existingUser:Seq[User] =   Await.result( userDao.getUsersByUsername(registerRequest.email),Duration.Inf)
     if(existingUser.length > 0 ) Left (new Exception("User already exists in the system "))

     val res =  Await.result(userDao.createUserAccount(registerRequest.email,Utilities.encrypt(registerRequest.password)),Duration.Inf)
    Right(populateBasic(res,registerRequest))
  }


  def populateResponse(user: User,login:LoginRequest): Option[AuthResponse]  =  Some(populateBasic(user,login))

  //Populate Response and move
  /*
   Use the basic Passwrd just saw too hide the background
   */
  private def populateBasic(user: User,login:LoginRequest): AuthResponse = {
    val pairString:String = login.username+":"+login.password
    AuthResponse(JwtUtility.generateKey(pairString), user.username,user.id)
  }
  //todo: populate basic based ono Registratin
  private def populateBasic(user: User, register:RegisterRequest): AuthResponse = {
    val pairString:String = register.email+":"+register.password
    AuthResponse(JwtUtility.generateKey(pairString), user.username,user.id)
  }

  private def decryptPairString(pairString:String ):LoginRequest ={
    val loginiArray:Array[String] = pairString.split(":")
    LoginRequest(loginiArray(0),loginiArray(1))
  }




}
