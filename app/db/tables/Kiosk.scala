package db.tables

import java.sql.Timestamp

import slick.sql.SqlProfile.ColumnOption.SqlType
//import defaults ::
import slick.jdbc.PostgresProfile.api._
case class  Kiosk(id:Long, reference:String, details:String, device_token:String, station_id:Long, date_created:Timestamp,  date_updated:Timestamp,author_id:Long, updated_by:Long)

class KioskTable(tag: Tag) extends  Table[Kiosk](tag,"kiosks"){

  def id       = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def reference = column[String]("reference",O.Unique)
  def details = column[String]("details")
  def device_token = column[String]("device_token",O.Unique)
  def station_id = column[Long]("station_id")



  def date_created = column[Timestamp]("date_created", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def date_updated = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def author_id =  column[Long]("author_id")
  def updated_by =  column[Long]("updated_by")

//  (id,organisation_id,name,code) <> (Station.tupled,Station.unapply)
  override def * = (id,reference,details,device_token,station_id,date_created,date_updated,author_id,updated_by).mapTo[Kiosk]
  //<> (Kiosk.tupled,Kiosk.unapply)

}