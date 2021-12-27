package daos

import controllers.requests.StationRequest
import db.tables.{Organization, OrganizationTable, Station, StationTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.Future

@Singleton
class StationDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy val stationTable = TableQuery[StationTable]
  lazy val organizationTable = TableQuery[OrganizationTable]

  import dbConfig._


  //todo: create station
  def create(organisation_id: Long, station: StationRequest): Future[Station] = {
    db.run(stationTable.returning(stationTable) += Station(0L, organisation_id, station.name, station.code))
  }


  //todo: list station in an organization
  def list(organization_id: Long, offset: Int, limit: Int): Future[Seq[(Station,Option[Organization])]] = {

    val query = stationTable    filter(_.organisation_id === organization_id)  joinLeft organizationTable on (_.organisation_id=== _.id)


    db.run(query
      .drop(offset)
      .take(limit)
      .result)
  }


  def get(id: Long): Future[Option[(Station,Option[Organization])]] = {

    val  query  = stationTable.filter(_.id === id) joinLeft organizationTable on (_.organisation_id=== _.id)
    db.run(query.result.headOption)
  }

  //todo: Archive Station
  def archive(organization_id: Int, id: Int): Unit = {
    ???
  }

  //todo: Populate Response
}
