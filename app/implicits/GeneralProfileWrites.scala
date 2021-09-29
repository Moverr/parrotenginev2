package implicits


import controllers.responses.GeneralProfileResponse
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}


object GeneralProfileWrites {
implicit val profileResponseWrites: Writes[GeneralProfileResponse] = (
(JsPath \ "id").write[Long] and
(JsPath \ "surname").write[String] and
(JsPath \ "othername").write[String] and
(JsPath \ "profiletype").write[String] and
(JsPath \ "gender").write[String]
)(unlift(GeneralProfileResponse.unapply))

}
