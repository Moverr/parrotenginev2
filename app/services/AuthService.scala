package services

import controllers.requests.LoginRequest
import controllers.responses.LoginResponse
import  collection.mutable

class AuthService {
  private val users = mutable.Map[String,String]("rogers"->"moaoe")

  //todo: Login Function
  def validate(loginRequest: LoginRequest): LoginResponse ={
    users.get(loginRequest.username).exists(_== loginRequest.password)
    null
  }

  //todo: Register Function
  def register(): Unit ={
    ???
  }
}
