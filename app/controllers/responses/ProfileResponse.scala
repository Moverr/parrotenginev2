package controllers.responses

import java.sql.Timestamp

import controllers.requests.ProfileRequest
import controllers.requests.ProfileType.ProfileType
import org.joda.time.DateTime

sealed  trait ProfileResponse
//abstract class ProfileResponse{
//  def id:Long
//  def  surname:String
//  def  othername:String
//  def  profiletype:String
//  def  gender:String
//  def date_created:Long
//  def author:String
//}
case  class GeneralProfileResponse(id:Long, surname:String, othername:String, profiletype: String, gender:String) extends ProfileResponse

case class ResidentProfileResponse(id:Long, profile:GeneralProfileResponse, stationid:Int, joinDate:Timestamp, date_created:Long, author:String  ) extends  ProfileResponse

case class GuestResponse(id:Long, surname:String, othername:String, profiletype: String, gender:String) extends ProfileResponse

// thisis to hellp in the authors
case class AuthorResponse(id:Long,surname:String, othername:String) extends  ProfileResponse

