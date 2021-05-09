package implicits

import controllers.responses.{OrganisationResponse, StationResponse}

import controllers.responses.{ OrganisationResponse}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}
import  implicits.OrganizationResponseWrites._


object StationResponseWrites {
  implicit val stationResponseWrites: Writes[StationResponse] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "name").write[String] and
      (JsPath \ "code").write[String] and
      (JsPath \ "organization").write[OrganisationResponse]
    )(unlift(StationResponse.unapply))
}
