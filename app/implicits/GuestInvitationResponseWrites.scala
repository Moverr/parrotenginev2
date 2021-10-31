package implicits

import controllers.responses.{GeneralProfileResponse, GuestInvitationResponse, ProfileResponse}


import play.api.libs.functional.syntax.{unlift,_}
import play.api.libs.json.{JsPath, Writes}



object GuestInvitationResponseWrites {
  implicit val guestProfileInvitation: Writes[GuestInvitationResponse] = (
    (JsPath \ "profile").write[ProfileResponse] and
      (JsPath \ "timein").write[Option[Long]] and
      (JsPath \ "timeout").write[Option[Long]] and
      (JsPath \ "reference_id").write[String] and
      (JsPath \ "status").write[String]
    )(unlift(GuestInvitationResponse.unapply))

}
