package db.tables


import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery


case class Station (id:Long=0L,organisation_id:Long,name:String,code:String)

class StationTable(tag: Tag) extends Table[Station](tag,"stations"){

  def id = column[Long]("id",O.PrimaryKey, O.AutoInc)
  def organisation_id = column[Long]("organisation_id")
  def name = column[String]("name")
  def code = column[String]("code")

  def location = column[String]("location")

  def date_created = column[Timestamp]("date_created", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def date_updated = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def author_id =  column[Long]("author_id")
  def updated_by =  column[Long]("updated_by")



  override def * = (id,organisation_id,name,code) <> (Station.tupled,Station.unapply)
}

