package db.tables

import java.sql.Timestamp

case class Address (id:Long = 0L,address_type:String,name:String,details:String,parent_type:String,parent_id:Long,
                    date_created:Timestamp,author_id:Long, date_updated:Timestamp,updated_by:Long
                   )