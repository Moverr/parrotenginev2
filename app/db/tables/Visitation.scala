package db.tables
import java.sql.Timestamp
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._


case class Visitation(id:Long,guest_id:Long,host_id:Long,time_in:Timestamp,time_out:Timestamp,station_id:Option[Long],kiosk_id:Option[Long])

class  VisitationTable(tag: Tag) extends Table[Visitation](tag,"visitations"){

  def id = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def  guest_id  = column[Long]("guest_id")
  def  host_id  = column[Long]("host_id")
  def  time_in  = column[Timestamp]("time_in")
  def  time_out  = column[Timestamp]("time_out")
  def  station_id  = column[Option[Long]]("station_id")
  def  kiosk_id= column[Option[Long]]("kiosk_id")

  override def * = (id,guest_id,host_id,time_in,time_out,station_id,kiosk_id)<>( (Visitation.apply _).tupled ,Visitation.unapply)
}
