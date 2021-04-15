package services

import java.sql.Timestamp

import db.tables.Organization
import slick.sql.SqlProfile.ColumnOption.SqlType


import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType
//import defaults ::
import slick.lifted.TableQuery
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery




case class Station (id:Long = 0L,organization_id:Long,name:String,details:String,location:String,
                    date_created:Timestamp,author_id:Long, date_updated:Timestamp,updated_by:Long
                   )


class StationTable(tag: Tag) extends Table[Station](tag,"stations"){

  def id = column[Long]("id",O.PrimaryKey, O.AutoInc)
  def organization_id = column[Long]("organization_id")
  def name = column[String]("name")
  def details = column[String]("details")
  def location = column[String]("location")

  def date_created = column[Timestamp]("date_created", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def date_updated = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def author_id =  column[Long]("author_id")
  def updated_by =  column[Long]("updated_by")

  def * = (id,organization_id,name,details,location,date_created,author_id,date_updated,updated_by).mapTo[Station]
  //mapTo[Organization]
}
