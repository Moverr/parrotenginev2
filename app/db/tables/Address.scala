package db.tables

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
//import defaults ::
import slick.lifted.TableQuery
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery


case class Address (id:Long = 0L,address_type:String,name:String,details:String,parent_type:String,parent_id:Long,
                    date_created:Timestamp,author_id:Long, date_updated:Timestamp,updated_by:Long
                   )


class AddressTable(tag: Tag) extends  Table[Address](tag,"addreses"){

}