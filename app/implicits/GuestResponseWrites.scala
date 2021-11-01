package implicits

import controllers.responses.{ GuestResponse}
import play.api.libs.json.{JsPath, Writes}
import play.api.libs.functional.syntax.{unlift, _}

object GuestResponseWrites {

  implicit val guestResponse : Writes[GuestResponse] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "surname").write[String] and
      (JsPath \ "othername").write[String] and
      (JsPath \ "profiletype").write[String] and
      (JsPath \ "gender").write[String]
    )(unlift(GuestResponse.unapply))

}
