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
  import dbConfig._


  //todo: create station
  def creata(organisation_id:Int, station:StationRequest): Future[StationResponse] =   db.run(stationTable.returning(stationTable) += Station(0L,organisation_id,station.name,station.code)



  //todo: list station in an organization
  def createOrganisation(organization: Int,offset:Int,limit:Int):Seq[StationResponse]={
    //validate organization
    ???
  }
  //todo: list stations in an account
  def createOrganisation(offset:Int,limit:Int):Seq[StationResponse]={
    ???
  }
  //todo: update station
  def createOrganisation(id:Int,station:StationRequest):StationResponse={
    ???
  }
  //todo: Archive Station
  def Archive(id:Int):Unit={
    ???
  }
  //todo: Populate Response
}
