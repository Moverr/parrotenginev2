package controllers.responses

case class GuestInvitationResponse(profile: ProfileResponse,timein:Option[Long],timeout:Option[Long],reference_id:String,status:String)
