package controllers.requests

import java.util.Date


sealed trait ProfileRequest

case class ResidentProfileRequest(surname:String,otherName:String,gender:String,date_of_birth:Date) extends ProfileRequest
case class GuestProfileRequest(surname:String,otherName:String,gender:String,age:Int) extends ProfileRequest



