package implicits

import controllers.responses.AuthorResponse
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}


object AuthorResponseWrites {

  implicit val authorResponsewrites : Writes[AuthorResponse] = (
    (
      (JsPath \ "surname").write[String] and
      (JsPath \ "othername").write[String]

    )(unlift(AuthorResponse.unapply))
    )

}

