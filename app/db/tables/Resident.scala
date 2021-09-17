package db.tables


import java.sql.Timestamp
import java.util.Date

import org.joda.time.DateTime
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._



case class Resident(id:Long,profile_id:Long,author_id:Long,created_on:Timestamp,updated_by:Long,date_updated:Timestamp
                    ,station_id:Long
                   ,join_date:Timestamp
                   )



class ResidentTable(tag: Tag) extends Table[Resident](tag,"resident"){
  def id      = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def profile_id = column[Long]("profile_id")
  def author_id = column[Long]("author_id")
  def created_on = column[Timestamp]("created_on",SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def updated_by = column[Long]("updated_by")
  def date_updated = column[Timestamp]("date_updated",SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def station_id = column[Long]("station_id")
  def join_date = column[Timestamp]("join_date",SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  //todo: map a relation ship with profile to get the profile.
  override def * = (id,profile_id,author_id,created_on,updated_by,date_updated,station_id,join_date).mapTo[Resident]

  //def profile implementation
}
