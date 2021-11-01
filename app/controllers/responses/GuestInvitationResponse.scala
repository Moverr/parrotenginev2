package controllers.responses

import java.sql.Timestamp

case class GuestInvitationResponse(profile: GuestResponse, timein:Option[Timestamp], timeout:Option[Timestamp], reference_id:String, status:String)
