package daos


import db.tables.{Profile, ProfileTable, Resident, ResidentTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.Future

@Singleton
class ResidentProfileDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  //extends ProfileDAO (dbConfigProvider){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  val residentTable = TableQuery[ResidentTable]
  val profileTable = TableQuery[ProfileTable]

  import dbConfig._

  /*
  Create resident data to the database
   */
  def create(request: Resident): Future[Resident] = db.run(residentTable.returning(residentTable) += request)


  //todo: list items
  def list(owner_id: Option[Long], station_id: Option[Long], offset: Int, limit: Int): Future[Seq[(Resident, Profile)]] = {

    val records = for {
      (resident, profile) <- {
        val record = residentTable join profileTable on (_.profile_id === _.id) drop (offset) take (limit)

        if (station_id.isDefined) {
          record.filter(_._1.station_id === station_id.get)
        }
        if (owner_id.isDefined) {
          record.filter(_._1.author_id === owner_id.get)
        }

        record
      }

    }
      yield (resident, profile)


    db.run(records.result)
  }


}
