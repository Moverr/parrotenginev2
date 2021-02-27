package daos

import java.sql.Timestamp

import db.tables.{Organization, OrganizationTable, User, UserTable}

import scala.concurrent.Future
import javax.inject.{Inject, Singleton}
import org.joda.time.{DateTime, DateTimeZone}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

@Singleton
class OrganisationDAO  @Inject()(dbConfigProvider: DatabaseConfigProvider) {


  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy  val orgTable = TableQuery[OrganizationTable]
  import dbConfig._


 /*
 *
 * Get Organisation by owner

  */
  def getOrganisations(owner:Long,offset:Int,limit:Int): Future[Seq[Organization]]  =
    db.run(orgTable.filter(_.owner === owner).drop(offset).take(limit).result)


  /*
  *
  * Create Organisation
 */
  def createOrganisation(name:String,details:String,owner:Long): Future[Organization] =
    db.run(orgTable.returning(orgTable) += Organization(0L,name,details,owner, null,null,null,null))


}