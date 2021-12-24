package services
import controllers.requests.LoginRequest
import controllers.responses.UserResponse
import db.tables.User

import scala.concurrent.Future

trait IAuthService {

  //todo: Login Function
  def validate(loginRequest: LoginRequest): Future[Option[UserResponse]]

  //todo: Register Function
  def register(): Unit

  //todo : populate Response
  def populateResponse(user: User): Option[UserResponse]
}
