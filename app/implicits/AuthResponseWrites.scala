package implicits

import controllers.responses.AuthResponse
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}


object AuthResponseWrites {

  implicit val authResponseWrites: Writes[AuthResponse] = (
    (JsPath \ "access_token").write[String] and
      (JsPath \ "username").write[String] and
        (JsPath \ "user_id").write[Long]
    )(unlift(AuthResponse.unapply))

}


