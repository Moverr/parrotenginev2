package services

import java.sql.Timestamp


case class Station (id:Long = 0L,organizatioon_id:Long,name:String,details:String,location:String,
                    date_created:Timestamp,author_id:Long, date_updated:Timestamp,updated_by:Long
                   )
