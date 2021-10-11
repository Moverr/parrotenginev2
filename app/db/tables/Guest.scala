package db.tables
import java.sql.Timestamp
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._



case class Guest(id:Long,profile_id:Long,author_id:Option[Long],created_on:Timestamp,updated_by:Option[Long],date_updated:Timestamp)

class GuessTable(tag: Tag) extends Table[Guest](tag,"Guest"){

  ???

  override def * = ???
}