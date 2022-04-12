package db.tables
import java.sql.Timestamp
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

case class Visitation(id:Long,guest_id:Long,host_id:Long,time_in:Option[Timestamp],time_out:Option[Timestamp],station_id:Option[String],kiosk_id:Option[String],status:Option[String],reference_id:String)




class  VisitationTable(tag: Tag) extends Table[Visitation](tag,"visitations"){


  def id = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def  guest_id  = column[Long]("guest_id")
  def  host_id  = column[Long]("host_id")
  def  time_in  = column[Option[Timestamp]]("time_in")
  def  time_out  = column[Option[Timestamp]]("time_out")
  def  station_id  = column[Option[String]]("station_id")
  def  kiosk_id= column[Option[String]]("kiosk_id")
  def  status= column[Option[String]]("status")
  def  reference_id= column[String]("reference_id")


  override def * = (id,guest_id,host_id,time_in,time_out,station_id,kiosk_id,status,reference_id) <> (Visitation.tupled,Visitation.unapply)


}
