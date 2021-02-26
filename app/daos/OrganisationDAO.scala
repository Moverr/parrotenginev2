package daos

import db.tables.{Organization, OrganizationTable, User, UserTable}


import scala.concurrent.Future

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

@Singleton
class OrganisationDAO  @Inject()(dbConfigProvider: DatabaseConfigProvider) {


  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy  val orgTable = TableQuery[OrganizationTable]
  import dbConfig._


  //todo: fetch organisation by owner
  def getOrganisations(owner:Long,offset:Int,limit:Int): Future[Seq[Organization]]  =  db.run(orgTable.filter(_.owner === owner).drop(offset).take(limit).result)


  //todo: fetch organisation by owner
  def get(owner:Long): Future[Seq[Organization]]  =  db.run(orgTable.filter(_.owner === owner) .result)


  //todo: fetch
}
