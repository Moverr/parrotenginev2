package services
import controllers.requests.LoginRequest
import controllers.responses.AuthResponse
import db.tables.User

import scala.concurrent.Future

trait IAuthService {

  //todo: Login Function
  def validate(loginRequest: LoginRequest): Future[Option[AuthResponse]]

  //todo: Register Function
  def register(): Unit

  //todo : populate Response
  def populateResponse(user: User): Option[AuthResponse]
}
