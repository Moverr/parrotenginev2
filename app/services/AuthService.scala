package services

import controllers.requests.LoginRequest
import controllers.responses.LoginResponse
import db.tables.UserTable
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.collection.mutable

@Singleton
class AuthService @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  private val users = mutable.Map[String,String]("rogers"->"moaoe")

  private  val dbConfig = dbConfigProvider.get[JdbcProfile]


  //todo: User Table
  lazy  val UserTable = TableQuery[UserTable]


  //todo: Login Function
  def validate(loginRequest: LoginRequest): Option[LoginResponse] ={
    db.run(
      UserTable
    )
    None
  }

  //todo: Register Function
  def register(): Unit ={
    ???
  }



  }
