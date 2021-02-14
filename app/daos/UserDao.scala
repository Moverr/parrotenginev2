package daos

import db.tables.{User, UserTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.Future

@Singleton
class UserDao @Inject()(dbConfigProvider: DatabaseConfigProvider) extends IUserDAO {
  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  override lazy  val UserTable = TableQuery[UserTable]
  import dbConfig._

  /*
    Get User by Username
   */
  override def getUserByName(username:String): Future[Option[User]] ={
    val query = UserTable
      .filter(_.username === username)
      .result.headOption
      db.run(query)
  }


  override def getUserByNameAndPassord(username:String, password:String): Future[Option[User]] ={
    val query = UserTable
      .filter(_.username === username)
      .filter(_.password === password)
      .result.headOption
      db.run(query)
  }

  /*
    Get all Users whose username is similar. not applicable but just saw for back-end usage. .
   */
  override def getUsersByUsername(username:String, offset:Int, limit:Int): Future[Seq[User]] ={
    val query = UserTable.filter(_.username === username)
      .result
      db.run(query)
  }

  //insert new user account and return new id
  def createUserAccount(username:String,password:String): Future[User] ={
    val query = UserTable.returning(UserTable) += User(0L,username,password)
    db.run(query)
  }
}
