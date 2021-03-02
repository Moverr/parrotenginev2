package db.tables

import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

case class Organization(id:Long = 0L, name:String, details:String,owner:Long, date_created:Timestamp,author_id:Option[Long], date_updated:Option[Timestamp],updated_by:Option[Long])


class OrganizationTable(tag: Tag) extends Table[Organization](tag,"organisations"){

  def id = column[Long]("id",O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def details = column[String]("details")
  def owner =  column[Long]("owner")
  def date_created = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def date_updated = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def author_id =  column[Long]("author_id")
  def updated_by =  column[Long]("updated_by")

  override def * = (id.?,name,details,owner,date_created,author_id,date_updated.?,updated_by.?).mapTo[Organization]
}