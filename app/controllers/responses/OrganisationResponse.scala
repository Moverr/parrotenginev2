package controllers.responses

case class OrganisationResponse(id:Long,name:String,details:String,date_created:Long,author:AuthorResponse,date_updated:Long,updated_by:Long)

