package controllers.requests

import controllers.requests.ProfileType.ProfileType

abstract class ProfileRequest

case class BasicProfileRequest(surname:String,otherName:String,profileType: ProfileType,gender:String,user_id:Long = 0) extends  ProfileRequest

//Resident
case class ResidentProfileRequest(surname:String,otherName:String,profileType: ProfileType=ProfileType.Resident,gender:String,user_id:Long = 0,adress:AddressRequest) extends  ProfileRequest
//Guest
case class GuestProfileRequest(surname:String,otherName:String,profileType: ProfileType=ProfileType.Guest,gender:String,user_id:Long = 0) extends  ProfileRequest



