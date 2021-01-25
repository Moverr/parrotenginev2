package services

import controllers.requests.LoginRequest
import db.tables.{User, UserTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.collection.mutable
import scala.concurrent.Future

@Singleton
class AuthService @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  private val users = mutable.Map[String,String]("rogers"->"moaoe")

  private  val dbConfig = dbConfigProvider.get[JdbcProfile]


  import dbConfig._
  //todo: User Table
  lazy  val UserTable = TableQuery[UserTable]


  //todo: Login Function
  def validate(loginRequest: LoginRequest): Future[Option[User]] ={
    val q = UserTable
        .filter(_.username === loginRequest.username.trim).result.headOption
    val result:Future[Option[User]] = db.run(q)
    result
  }

  //todo: Register Function
  def register(): Unit ={
    ???
  }



  }
