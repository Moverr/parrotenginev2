package controllers.requests

import controllers.requests.ProfileType.ProfileType
import org.joda.time.DateTime

//sealed trait  ProfileReq

sealed trait ProfileRequest
//,user_id:Option[Long]
case class BasicProfileRequest(surname:String,othername:String,profiletype: ProfileType,gender:String  ) extends  ProfileRequest

//Resident
//,user_id:Option[Long]
case class ResidentProfileRequest(surname:String,othername:String,profiletype: ProfileType,gender:String,stationid:Int,joinDate:DateTime  ) extends  ProfileRequest
//Guest
//,user_id:Option[Long]
case class VisitationRequest(profile:BasicProfileRequest, host_id:Long, registerDate:Long, location: PhysicalAddress
                             , stationId:String, kioskId:String //kiok id is device id
                              ) extends  ProfileRequest



