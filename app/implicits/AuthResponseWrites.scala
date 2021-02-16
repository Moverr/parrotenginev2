package implicits

import java.sql.Timestamp

import controllers.responses.AuthResponse
import org.joda.time.DateTime
import org.joda.time.DateTimeZone.UTC
import play.api.libs.functional.syntax.unlift
import play.api.libs.json.{JsPath, Writes}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._


object AuthResponseWrites {

  implicit val authResponseWrites: Writes[AuthResponse] = (
    (JsPath \ "access_token").write[String] and
      (JsPath \ "username").write[String]

    )(unlift(AuthResponse.unapply))

}


