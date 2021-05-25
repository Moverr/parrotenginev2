package db.tables

import java.sql.Timestamp

case class Profile(id:Long,user_id:Long,surname:String,other_names:String,gender:String,profile_type:String,author_id:Long,created_on:Timestamp,updated_by:Long,date_updated:Timestamp)
