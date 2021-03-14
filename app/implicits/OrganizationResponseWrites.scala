package implicits

import controllers.responses.{ OrganisationResponse}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}



object OrganizationResponseWrites {
  implicit val organizationResponseWrites: Writes[OrganisationResponse] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "name").write[String] and
      (JsPath \ "details").write[String] and
      (JsPath \ "date_created").write[Long] and
      (JsPath \ "author_id").write[Long] and
      (JsPath \ "date_updated").write[Long] and
      (JsPath \ "updated_by").write[Long]
    )(unlift(OrganisationResponse.unapply))
}
