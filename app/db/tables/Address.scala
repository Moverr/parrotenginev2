package db.tables

import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType
//import defaults ::
import slick.lifted.TableQuery
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery


case class Address (id:Long = 0L,address_type:String,name:String,details:String,parent_type:String,parent_id:Long,
                    date_created:Timestamp,author_id:Long, date_updated:Timestamp,updated_by:Long
                   )


class AddressTable(tag: Tag) extends  Table[Address](tag,"addreses"){

  def id       = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def address_type = column[String]("address_type")
  def name = column[String]("name")
  def details = column[String]("details")
  def parent_type = column[String]("parent_type")
  def parent_id = column[Long]("parent_id")

  def date_created = column[Timestamp]("date_created", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def date_updated = column[Timestamp]("date_updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
  def author_id =  column[Long]("author_id")
  def updated_by =  column[Long]("updated_by")


  override def * = (id,address_type,name,details,parent_type,parent_id,date_created,author_id,date_updated,updated_by).mapTo[Address]


}