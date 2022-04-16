package implicits

import java.sql.Timestamp

import GuestResponseWrites._
import HostResponseWrites._
import controllers.responses.{GuestInvitationResponse, GuestResponse, HostResponse}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}




object GuestInvitationResponseWrites {
  implicit val guestProfileInvitation: Writes[GuestInvitationResponse] = (
    (JsPath \ "guest").write[Option[GuestResponse]] and
      (JsPath \ "host").write[Option[HostResponse]] and
      (JsPath \ "timein").write[Option[Timestamp]] and
      (JsPath \ "timeout").write[Option[Timestamp]] and
      (JsPath \ "reference_id").write[String] and
      (JsPath \ "status").write[String] and
      (JsPath \ "station_id").write[Option[Long]] and
      (JsPath \ "kiosk_id").write[Option[Long]]
    )(unlift(GuestInvitationResponse.unapply))

}
