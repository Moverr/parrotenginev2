package daos
import db.tables.{User, UserTable}
import slick.lifted.TableQuery

import scala.concurrent.Future

trait IUserDAO {

  val UserTable: TableQuery[UserTable]

  /*
    Get User by Username
   */
  def getUserByName(username: String): Future[Option[User]]

  /*
    Get User by Username and Password
   */
  def getUserByUsernameAndPassword(username: String, password: String): Future[Option[User]]

  /*
    Get all Users whose username is similar. not applicable but just saw for back-end usage. .
   */
  def getUsersByUsername(username: String): Future[Seq[User]]
}
