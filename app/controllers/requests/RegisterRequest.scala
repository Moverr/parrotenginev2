package controllers.requests



sealed trait   Registration

case class NormalRegistration(  email:String,   password:String)  extends  Registration
case class InviteRegistration(  author:Long,   email:String)  extends  Registration
case class SocialAuthRegistration(  token:String)  extends  Registration
