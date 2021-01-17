package services

import controllers.requests.LoginRequest
import controllers.responses.LoginResponse
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider

import collection.mutable

class AuthService @Inject()(db: DatabaseConfigProvider) {
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
