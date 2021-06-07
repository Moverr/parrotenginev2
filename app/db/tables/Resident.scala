package db.tables

import java.sql.Timestamp
import java.sql.Timestamp

import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType
//import defaults ::
import slick.lifted.TableQuery
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery
case class Resident(id:Long,profile_id:Long,author_id:Long,created_on:Timestamp,updated_by:Long,date_updated:Timestamp)

class ResidentTable(tag: Tag) extends Table[Resident](tag,"residents"){
  override def * = ???
}
