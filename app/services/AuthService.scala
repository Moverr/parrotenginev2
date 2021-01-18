package services

import controllers.requests.LoginRequest
import controllers.responses.LoginResponse
import db.tables.User
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.collection.mutable

@Singleton
class AuthService @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  private val users = mutable.Map[String,String]("rogers"->"moaoe")

  private  val dbConfig = dbConfigProvider.get[JdbcProfile]

  val user = new User(0l,"username","password",None)

  //todo: Login Function
  def validate(loginRequest: LoginRequest): Option[LoginResponse] ={
    None
  }

  //todo: Register Function
  def register(): Unit ={
    ???
  }



  }
