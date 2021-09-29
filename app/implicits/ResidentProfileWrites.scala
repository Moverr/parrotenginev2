package implicits

import java.sql.Timestamp

import controllers.responses.{GeneralProfileResponse, OrganisationResponse, ProfileResponse, ResidentProfileResponse}
import play.api.libs.json.{JsPath, Writes}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}

import GeneralProfileWrites._

object ResidentProfileWrites {

  implicit val organizationResponseWrites: Writes[ResidentProfileResponse] = (
    (JsPath \ "id").write[Long] and
    (JsPath \ "profile").write[GeneralProfileResponse] and
      (JsPath \ "stationid").write[Int] and
      (JsPath \ "joinDate").write[Timestamp] and
      (JsPath \ "date_created").write[Long] and
      (JsPath \ "author").write[String]
    )(unlift(ResidentProfileResponse.unapply))

}
