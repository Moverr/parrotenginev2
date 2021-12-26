package controllers.responses
import java.sql.Timestamp

case class OrganisationResponse(id:Long,name:String,details:String,date_created:Timestamp,author:Option[AuthorResponse],date_updated:Timestamp,updated_by:Long)

