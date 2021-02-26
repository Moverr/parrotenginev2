package daos

import db.tables.{Organization, OrganizationTable, User, UserTable}
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.Future

class OrganisationDAO  @Inject()(dbConfigProvider: DatabaseConfigProvider) {


  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy  val orgTable = TableQuery[OrganizationTable]
  import dbConfig._


  //todo: fetch organisation by owner

    def getUserByUsernameAndPassword(owner:Long): Future[Seq[Organization]]  ={
      db.run(orgTable.filter(_.owner === owner))
  }

  //todo: fetch
}
