package services

import controllers.requests.LoginRequest
import controllers.responses.LoginResponse
import daos.UserDao
import db.tables.{User, UserTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class AuthService @Inject()(
                             dbConfigProvider: DatabaseConfigProvider
                           ,userDa: UserDao
                           ) {
  private val users = mutable.Map[String,String]("rogers"->"moaoe")

  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  //todo: User Table
  lazy  val UserTable = TableQuery[UserTable]


  //todo: Login Function
  def validate(loginRequest: LoginRequest): Future[LoginResponse] ={

   val response:Future[Option[User]] =  userDa.getUserByNameAndPassord(loginRequest.username,loginRequest.password)

   val result =    response.flatMap {
        case Some(value) =>  Future.successful(populateResponse(value))
        case None => Future.successful(null)
      }
    result
  }


  //todo: Register Function
  def register(): Unit ={
    ???
  }

  def populateResponse(user: User): LoginResponse ={
    val x = LoginResponse("token","username")
    x
  }

  }
