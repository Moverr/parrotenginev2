package daos

import java.sql.Timestamp

import db.tables.{Organization, OrganizationTable, User, UserTable}
import helpers.Utilities.getCurrentTimeStamp

import scala.concurrent.Future
import javax.inject.{Inject, Singleton}
import org.joda.time.{DateTime, DateTimeZone, LocalDateTime}
import play.api.db.slick.DatabaseConfigProvider
import play.db.NamedDatabase
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

@Singleton
class OrganisationDAO  @Inject()(@NamedDatabase("default") dbConfigProvider: DatabaseConfigProvider) extends IOrganisatioonDAO {


  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
   val orgTable = TableQuery[OrganizationTable]
       val UserTable = TableQuery[UserTable]


  import dbConfig._


  /*
  *
  * Get Organisation by owner
   */
    def list(owner:Long, offset:Int, limit:Int):  Future[Seq[(Organization,User)]]  =  {

     val record  =   for {
        record <- orgTable.filter(_.owner === owner) join UserTable  on (_.owner === _.id)
      }
        yield (record)

      db.run(record.drop(offset).take(limit).result)
    }


  /*
     Get  Organisation by Id
   */
  def get(owner:Long, orgId:Long): Future[Option[(Organization,User)]]  = {

    val record  =   for {
      record <- orgTable join UserTable  on (_.owner === _.id)
    }
      yield (record)
    db.run(record.filter(_._2.id === owner).filter(_._1.id === orgId).result.headOption)
  }

  def get(orgId:Long): Future[Option[Organization]]  =
    db.run(orgTable.filter(_.id === orgId).result.headOption)


  /*
  *
  * Create Organisation
 */
    def create(name:String, details:String, owner:Long): Future[Organization] =
    db.run(orgTable.returning(orgTable) += Organization(0L,name,details,owner, getCurrentTimeStamp(),0L,getCurrentTimeStamp(),0L))


}
