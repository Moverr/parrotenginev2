package controllers.requests

import controllers.requests.ProfileType.ProfileType


case class ProfileRequest(surname:String,otherName:String,profileType: ProfileType,gender:String,user_id:Long = 0)


