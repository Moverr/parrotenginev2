package implicits

import controllers.responses.AuthResponse
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}


object AuthResponseWrites {


 

  implicit  val authResponseWrites:JsonWriter[AuthResponse] =  new JsonWriter[AuthResponse] {
    override def write(value: AuthResponse): Json = {
    JsObject(Map {
      "access_token" -> JsString(value.access_token)
      "username" -> JsString(value.username)
      "user_id" -> JsString(value.user_id.toString)

    })

    }
  }

  implicit val authResponseWrites2: Writes[AuthResponse] = (
    (JsPath \ "access_token").write[String] and
      (JsPath \ "username").write[String] and
        (JsPath \ "user_id").write[Long]
    )(unlift(AuthResponse.unapply))

}


