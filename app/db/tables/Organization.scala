package db.tables

import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType

case class Organization(id:Long = 0L, name:String, details:String,owner:Long, date_created:Timestamp, date_updated:Timestamp)

class OrganizationTable(tag: Tag) extends Table[Organization](tag,"organisations"){

  def id = column[Long]("id",O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def details = column[String]("details")
  def owner = column[User]("owner")
  def date_created = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def date_updated = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  override def * = (id,name,details,date_created,date_updated).mapTo[Organization]
}