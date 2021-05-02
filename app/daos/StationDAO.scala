package daos

import java.sql.Timestamp

import controllers.requests.StationRequest
import controllers.responses.StationResponse
import db.tables.{Organization, OrganizationTable, Station, StationTable, User, UserTable}

import scala.concurrent.Future
import javax.inject.{Inject, Singleton}
import org.joda.time.{DateTime, DateTimeZone, LocalDateTime}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

@Singleton
class StationDAO    @Inject()(dbConfigProvider: DatabaseConfigProvider) {

  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy  val stationTable = TableQuery[StationTable]
  lazy  val organizationTable = TableQuery[OrganizationTable]

  import dbConfig._


  //todo: create station
  def create(organisation_id:Int, station:StationRequest): Future[Station] =
    db.run(stationTable.returning(stationTable) += Station(0L,organisation_id,station.name,station.code)



  //todo: list station in an organization
  def list(organization_id: Int,offset:Int,limit:Int):Future[Seq[Station]]=
    db.run(stationTable
      .filter(_.organisation_id === organization_id.toLong)
      .drop(offset)
      .take(limit)
      .result)



  //todo: Archive Station
  def archive(organization_id:Int,id:Int):Unit={
  ???
  }

  //todo: Populate Response
}
