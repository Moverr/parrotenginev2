package implicits

import controllers.responses.UserResponse
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}


object UserResponseWrites {


  implicit  val userResponseWrites:JsonWriter[UserResponse] =  new JsonWriter[UserResponse] {
    override def write(value: UserResponse): Json = {
    JsObject(Map {
      "access_token" -> JsString(value.access_token)
      "username" -> JsString(value.username)
      "user_id" -> JsString(value.user_id.toString)

    })

    }
  }

  implicit val userResponseWrites2: Writes[UserResponse] = (
    (JsPath \ "access_token").write[String] and
      (JsPath \ "username").write[String] and
        (JsPath \ "user_id").write[Long]
    )(unlift(UserResponse.unapply))

}


