package controllers.requests

sealed  trait AddressRequest

case class PhysicalAddress(location:String,addresstype:String,latititued:Option[Long],longitude:Option[Long]) extends  AddressRequest
case class PoBoxAddress(number:String,country:String,code:Int) extends  AddressRequest
//online address
case class OnlineAddress(details:String, address_type:String) extends  AddressRequest

