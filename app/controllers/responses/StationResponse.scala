package controllers.responses

case class StationResponse(id:Long,name:String,code:String,organization:Option[OrganisationResponse])