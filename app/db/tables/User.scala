package db.tables

import slick.jdbc.PostgresProfile.api._
//import defaults ::
import slick.lifted.TableQuery
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

case  class User(id:Long = 0L,username:String,password:String)

class UserTable(tag: Tag) extends Table[User](tag,"users"){
  def id       = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def username = column[String]("username",O.Unique)
  def password = column[String]("password")
  //def account  = column[Long]("account",O.Default(0))
  override def * = (id,username,password).mapTo[User]
  //<> (User.tupled,User.unapply)

}
