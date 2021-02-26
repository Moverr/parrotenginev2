package daos

import db.tables.{OrganizationTable, UserTable}
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

class OrganisationDAO  @Inject()(dbConfigProvider: DatabaseConfigProvider) {


  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy  val orgTable = TableQuery[OrganizationTable]
  import dbConfig._


  //todo: fetch organisation by owner

  //todo: fetch
}
