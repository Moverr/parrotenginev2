package services

import controllers.requests.LoginRequest
import controllers.responses.LoginResponse
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import tables.Account

import scala.collection.mutable

@Singleton
class AuthService @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  private val users = mutable.Map[String,String]("rogers"->"moaoe")

  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  var _table = Account

  //todo: Login Function
  def validate(loginRequest: LoginRequest): LoginResponse ={


    users.get(loginRequest.username).exists(_== loginRequest.password)
    null
  }

  //todo: Register Function
  def register(): Unit ={
    ???
  }


 
  }
