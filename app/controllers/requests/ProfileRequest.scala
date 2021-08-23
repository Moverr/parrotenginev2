package controllers.requests

import controllers.requests.ProfileType.ProfileType
import org.joda.time.DateTime

abstract class ProfileRequest{
  def  surname:String
  def  othername:String
  def  profiletype:ProfileType
  def  gender:String

}
//,user_id:Option[Long]
case class BasicProfileRequest(surname:String,othername:String,profiletype: ProfileType,gender:String  ) extends  ProfileRequest

//Resident
//,user_id:Option[Long]
case class ResidentProfileRequest(surname:String,othername:String,profiletype: ProfileType,gender:String  ) extends  ProfileRequest{
  val  joinDate:DateTime = DateTime.now()
}
//Guest
//,user_id:Option[Long]
case class GuestProfileRequest(surname:String,othername:String,profiletype: ProfileType,gender:String) extends  ProfileRequest{
  val  registerDate:DateTime = DateTime.now()

}



