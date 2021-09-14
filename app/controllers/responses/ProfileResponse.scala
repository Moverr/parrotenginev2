package controllers.responses

import java.sql.Timestamp

import controllers.requests.ProfileRequest
import controllers.requests.ProfileType.ProfileType
import org.joda.time.DateTime

abstract class ProfileResponse{
  def  surname:String
  def  othername:String
  def  profiletype:String
  def  gender:String
  def date_created:Long
  def author:String
}

case class ResidentProfileResponse(surname:String, othername:String, profiletype: String, gender:String, stationid:Int, joinDate:Timestamp, date_created:Long, author:String  ) extends  ProfileResponse
