package db.tables

import java.sql.Timestamp

import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType
//import defaults ::
import slick.lifted.TableQuery
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

case class Profile(id:Long,user_id:Long,surname:String,other_names:String,gender:String,profile_type:String,author_id:Long,created_on:Timestamp,updated_by:Long,date_updated:Timestamp)

class ProfileTable(tag: Tag) extends Table[Profile](tag,"profile"){
  override def * = ???
}