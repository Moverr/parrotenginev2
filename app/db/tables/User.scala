package db.tables

import tables.Account

case  class User(id:Long = 0L,username:String,password:String,account: Account)
