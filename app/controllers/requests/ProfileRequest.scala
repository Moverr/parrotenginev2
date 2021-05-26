package controllers.requests

import controllers.requests.ProfileType.ProfileType

case class ProfileRequest(surname:String,otherName:String,profileType: ProfileType,gender:String) {

  private var _user_id:Int = 0
  private var _author_id:Int = 0

  //Todo: Add User Ids
  def user_id = _user_id
  def  user_id_=(user_id:Int): Unit ={
    _user_id = user_id
  }

  //Todo: Add Author Id
  def author_id = _author_id
  def  author_id=(author_id:Int): Unit ={
    _user_id = _author_id
  }


}
