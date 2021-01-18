package db.tables

case  class User(id:Long = 0L,username:String,password:String,account: Option[Int])
