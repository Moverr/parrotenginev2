package implicits

import java.sql.Timestamp

import controllers.responses.{OrganisationResponse, ProfileResponse, ResidentProfileResponse}
import play.api.libs.json.{JsPath, Writes}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{JsPath, Writes}



object ResidentProfileWrites {

  implicit val organizationResponseWrites: Writes[ResidentProfileResponse] = (
    (JsPath \ "surname").write[String] and
      (JsPath \ "othername").write[String] and
      (JsPath \ "profiletype").write[String] and
      (JsPath \ "gender").write[String] and
      (JsPath \ "stationid").write[Int] and
      (JsPath \ "joinDate").write[Timestamp] and
      (JsPath \ "date_created").write[Long] and
      (JsPath \ "author").write[String]
    )(unlift(ResidentProfileResponse.unapply))

}
//case class ResidentProfileResponse(surname:String, othername:String, profiletype: String, gender:String, stationid:Int, joinDate:Timestamp, date_created:Long, author:String  ) extends  ProfileResponse
