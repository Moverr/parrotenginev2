package controllers.requests

import controllers.requests.ProfileType.ProfileType
import org.joda.time.DateTime

abstract class ProfileRequest{
  def  surname:String
  def  otherName:String
  def  profileType:ProfileType
  def  gender:String

}

case class BasicProfileRequest(surname:String,otherName:String,profileType: ProfileType,gender:String,user_id:Option[Long]  ) extends  ProfileRequest

//Resident
case class ResidentProfileRequest(surname:String,otherName:String,profileType: ProfileType,gender:String,user_id:Option[Long]  ) extends  ProfileRequest{
  val  joinDate:DateTime = DateTime.now()
}
//Guest
case class GuestProfileRequest(surname:String,otherName:String,profileType: ProfileType,gender:String,user_id:Option[Long]) extends  ProfileRequest{
  val  registerDate:DateTime = DateTime.now()

}



