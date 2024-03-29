package services

import controllers.requests.{LoginRequest, RegisterRequest}
import controllers.responses.UserResponse
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
   def validate(loginRequest: LoginRequest): Future[Option[UserResponse]] = {

    val response =  userDao.getUserByUsernameAndPassword(loginRequest.username,Utilities.encrypt(loginRequest.password))

    response.flatMap{
      case None => Future.successful(None)
      case Some(value) => Future.successful(populateResponse(value,loginRequest))
    }

  }

  //validate token
  def validateToken(authorizationToken: Option[String]):Either[java.lang.Throwable, Future[Option[UserResponse]] ]= {
    if(authorizationToken == "")    Left( new Exception("You are not authorized to this item "))

    val decrypted:Either[Exception,LoginRequest] =  decryptPairString(authorizationToken)
    decrypted match {
      case Left(value) =>  Left( new Exception("Invalid  token  ") )
      case Right(value) => Right(validate(value))
    }


  }


  //validate Token Overloading
  def validateTokenv2(authorizationToken: String): Future[Option[UserResponse]] = {

    val xp = JwtUtility.retrievePasswordPair(authorizationToken)
    val decrypted:Either[Exception,LoginRequest] =  decryptPairString(xp)
    decrypted match {
      case Left(value) => Future.successful(None)
      case Right(value) => validate(value)
    }


  }


  def register(registerRequest: RegisterRequest): Either[java.lang.Throwable,UserResponse] ={
     val existingUser:Seq[User] =   Await.result( userDao.getUsersByUsername(registerRequest.email),Duration.Inf)
     if(existingUser.length > 0 ) Left (new Exception("User already exists in the system "))

     val res =  Await.result(userDao.createUserAccount(registerRequest.email,Utilities.encrypt(registerRequest.password)),Duration.Inf)
    Right(populateBasic(res,registerRequest))
  }


  def populateResponse(user: User,login:LoginRequest): Option[UserResponse]  =  Some(populateBasic(user,login))

  //Populate Response and move
  /*
   Use the basic Passwrd just saw too hide the background
   */
  private def populateBasic(user: User,login:LoginRequest): UserResponse = {
    val pairString:String = login.username+":"+login.password
    UserResponse(JwtUtility.generateKey(pairString), user.username,user.id)
  }
  //todo: populate basic based ono Registratin
  private def populateBasic(user: User, register:RegisterRequest): UserResponse = {
    val pairString:String = register.email+":"+register.password
    UserResponse(JwtUtility.generateKey(pairString), user.username,user.id)
  }

  private def decryptPairString(pairString:Option[String] ):Either[Exception,LoginRequest] ={
    pairString match {
      case Some(value) =>{
        val loginiArray:Array[String] = value.split(":")
       Right(LoginRequest(loginiArray(0),loginiArray(1)))
      }
      case None =>Left(new Exception("Invalid Token"))
    }

  }




}
