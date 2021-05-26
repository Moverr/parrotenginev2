package daos

import controllers.requests.{ProfileRequest, StationRequest}
import db.tables.{OrganizationTable, ProfileTable, Station, StationTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.Future

@Singleton
class ProfileDAO   @Inject()(dbConfigProvider: DatabaseConfigProvider) {


  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy  val stationTable = TableQuery[ProfileTable]
  lazy  val organizationTable = TableQuery[OrganizationTable]

  import dbConfig._

  //todo: Create Profile ..
  //todo: create station
  def create(organisation_id:Long, profile:ProfileRequest): Future[Station] = {
    db.run(stationTable.returning(stationTable) += Station(0L,organisation_id,station.name,station.code))
  }


  //todoo: update Profile

  //todo: get Profile


  //todo: Get Profiles

  //todo: Archivev Profiles



}
