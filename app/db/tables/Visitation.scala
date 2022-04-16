package db.tables
import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery


import slick.jdbc.PostgresProfile.api._
//import defaults ::
import slick.lifted.TableQuery
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery




case class Visitation(id:Long,guest_profile_id:Long,host_profile_id:Long,time_in:Option[Timestamp],time_out:Option[Timestamp],station_id:Option[Long],kiosk_id:Option[Long],status:Option[String],reference_id:String)




class  VisitationTable(tag: Tag) extends Table[Visitation](tag,"visitations"){


  def id = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def  guest_profile_id  = column[Long]("guest_profile_id")
  def  host_profile_id  = column[Long]("host_profile_id")
  def  time_in  = column[Option[Timestamp]]("time_in")
  def  time_out  = column[Option[Timestamp]]("time_out")
  def  station_id  = column[Option[Long]]("station_id")
  def  kiosk_id= column[Option[Long]]("kiosk_id")
  def  status= column[Option[String]]("status")
  def  reference_id= column[String]("reference_id")


  override def * = (id,guest_profile_id,host_profile_id,time_in,time_out,station_id,kiosk_id,status,reference_id) <> (Visitation.tupled,Visitation.unapply)


}
