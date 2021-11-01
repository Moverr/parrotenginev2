package implicits

import java.sql.Timestamp

import GuestResponseWrites._
import controllers.responses.{GuestInvitationResponse, GuestResponse}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}




object GuestInvitationResponseWrites {
  implicit val guestProfileInvitation: Writes[GuestInvitationResponse] = (
    (JsPath \ "profile").write[GuestResponse] and
      (JsPath \ "timein").write[Option[Timestamp]] and
      (JsPath \ "timeout").write[Option[Timestamp]] and
      (JsPath \ "reference_id").write[String] and
      (JsPath \ "status").write[String]
    )(unlift(GuestInvitationResponse.unapply))

}
