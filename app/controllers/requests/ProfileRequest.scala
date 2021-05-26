package controllers.requests

import controllers.requests.ProfileType.ProfileType

case class ProfileRequest(surname:String,otherName:String,profileType: ProfileType) {

  private var _x = 0
  def x = _x
  def x_=(xa:Int): Int ={
    _x  = xa
    x
  }



}
