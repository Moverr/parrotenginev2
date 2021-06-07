package db.tables

import java.sql.Timestamp

case class Resident(id:Long,profile_id:Long,author_id:Long,created_on:Timestamp,updated_by:Long,date_updated:Timestamp)

