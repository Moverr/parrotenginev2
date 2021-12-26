package implicits

import java.sql.Timestamp

import controllers.responses.{AuthorResponse, OrganisationResponse}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}
import AuthorResponseWrites._


object OrganizationResponseWrites {
  implicit val organizationResponseWrites: Writes[OrganisationResponse] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "name").write[String] and
      (JsPath \ "details").write[String] and
      (JsPath \ "date_created").write[Timestamp] and
      (JsPath \ "author").write[Option[AuthorResponse]] and
      (JsPath \ "date_updated").write[Timestamp] and
      (JsPath \ "updated_by").write[Long]
    )(unlift(OrganisationResponse.unapply))
}
