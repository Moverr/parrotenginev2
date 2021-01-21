package services

import controllers.requests.LoginRequest
import controllers.responses.LoginResponse
import db.tables.{User, UserTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.collection.mutable
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

@Singleton
class AuthService @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  private val users = mutable.Map[String,String]("rogers"->"moaoe")

  private  val dbConfig = dbConfigProvider.get[JdbcProfile]


  import dbConfig._
  //todo: User Table
  lazy  val UserTable = TableQuery[UserTable]


  //todo: Login Function
  def validate(loginRequest: LoginRequest): LoginResponse ={
   val result:Future[User] =  db.run(
      UserTable
        .filter(_.username === loginRequest.username )
        .filter(_.password === loginRequest.password)
        .result
        .head
    )
    if(result.isCompleted){
      val response:LoginResponse = new LoginResponse("weewe","username")
      response
    }else{
      null
    }

  }

  //todo: Register Function
  def register(): Unit ={
    ???
  }



  }
