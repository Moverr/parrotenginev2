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
  override def getUserByName(useername:String): Future[Option[User]] ={
    val query = UserTable
      .filter(_.username === useername)
      .result.headOption
    db.run(query)
  }

  /*
    Get User by Username and Password
   */
  override def getUserByNameAndPassord(useername:String,password:String): Future[Option[User]] ={
    val query = UserTable
      .filter(_.username === useername)
      //  .filter(_.password === password)
      .result.headOption
    db.run(query)
  }

  /*
    Get all Users whose username is similar. not applicable but just saw for back-end usage. .
   */
  override def getUsersByUsername(useername:String,offset:Int,limit:Int): Future[Seq[User]] ={
    val query = UserTable.filter(_.username === useername)
      .result
    db.run(query)
  }
}
