package controllers.responses

import java.sql.Timestamp

case class GuestInvitationResponse(guest: Option[ProfileResponse], host: Option[ProfileResponse], time_in:Option[Timestamp], time_out:Option[Timestamp], reference_id:String, status:String)
