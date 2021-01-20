package db.tables

import slick.jdbc.PostgresProfile.api._
//import defaults ::

case  class User(id:Long = 0L,username:String,password:String,account: Option[Long])

class UserTable(tag: Tag) extends Table[User](tag,"users"){
  def id       = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def username = column[String]("username",O.Unique)
  def password = column[String]("password")
  def account  = column[Long]("account")
  override def * = ???
}
