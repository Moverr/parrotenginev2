package services
import controllers.requests.LoginRequest
import controllers.responses.LoginResponse
import db.tables.User

import scala.concurrent.Future

trait IAuthService {

  //todo: Login Function
  def validate(loginRequest: LoginRequest): Future[Option[LoginResponse]]

  //todo: Register Function
  def register(): Unit

  //todo : populate Response
  def populateResponse(user: User): Option[LoginResponse]
}
