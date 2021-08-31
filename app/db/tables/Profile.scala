package db.tables


import java.sql.Timestamp
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._


case  class Profile(id:Long,user_id:Option[Long],surname:String,other_names:String,gender:String,profile_type:String,author_id:Long,created_on:Timestamp,updated_by:Long,date_updated:Timestamp)

class ProfileTable(tag: Tag) extends Table[Profile](tag,"profile"){
  def id      = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def user_id = column[Option[Long]]("user_id")
  def surname = column[String]("surname")
  def other_names = column[String]("other_names")
  def gender = column[String]("gender")
  def profile_type = column[String]("profile_type")
  def author_id = column[Long]("author_id")
  def created_on = column[Timestamp]("created_on",SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def updated_by = column[Long]("updated_by")
  def date_updated = column[Timestamp]("date_updated",SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  override def * = (id,user_id,surname,other_names,gender,profile_type,author_id,created_on,updated_by,date_updated).mapTo[Profile]

}