package controllers.responses

import java.sql.Timestamp

case class GuestInvitationResponse(guestProfile: GuestResponse, hostProfile: HostResponse,timein:Option[Timestamp], timeout:Option[Timestamp], reference_id:String, status:String)
