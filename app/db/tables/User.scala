package db.tables

import slick.jdbc.PostgresProfile.api._
//import defaults ::

case  class User(id:Long = 0L,username:String,password:String,account: Option[Int])

class UserTable(tag: Tag) extends Table[User](tag,"users"){
  override def * = ???
}
