package implicits

import controllers.responses.{ HostResponse}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}

object HostResponseWrites {

  implicit val hostResponse : Writes[HostResponse] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "surname").write[String] and
      (JsPath \ "othername").write[String] and
      (JsPath \ "profiletype").write[String] and
      (JsPath \ "gender").write[String]
    )(unlift(HostResponse.unapply))

}
