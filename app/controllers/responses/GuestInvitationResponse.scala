package controllers.responses

import java.sql.Timestamp

case class GuestInvitationResponse(guest: Option[GuestResponse], host: Option[HostResponse], time_in:Option[Timestamp], time_out:Option[Timestamp], reference_id:String, status:String)
