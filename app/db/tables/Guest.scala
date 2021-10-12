package db.tables
import java.sql.Timestamp
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._



case class Guest(id:Long,profile_id:Long,author_id:Option[Long],created_on:Timestamp,updated_by:Option[Long],date_updated:Timestamp)

class GuestTable(tag: Tag) extends Table[Guest](tag,"Guest"){
  def id      = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def profile_id      = column[Long]("profile_id")
  def author_id      = column[Option[Long]]("author_id")
  def created_on = column[Timestamp]("created_on",SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def updated_by = column[Option[Long]]("updated_by")
  def date_updated = column[Timestamp]("date_updated",SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))


  override def * = (id,profile_id,author_id,created_on,updated_by,date_updated) <>((Guest.apply _).tupled,Guest.unapply)
}