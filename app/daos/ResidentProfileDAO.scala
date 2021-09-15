package daos


import java.sql.Timestamp

import controllers.requests.{ResidentProfileRequest, StationRequest}
import controllers.responses.{AuthResponse, StationResponse}
import db.tables.{Organization, OrganizationTable, Profile, ProfileTable, Resident, ResidentTable, Station, StationTable, User, UserTable}

import scala.concurrent.Future
import javax.inject.{Inject, Singleton}
import org.joda.time.{DateTime, DateTimeZone, LocalDateTime}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery
import java.sql.Timestamp

import helpers.Utilities.getCurrentTimeStamp

@Singleton
class ResidentProfileDAO    @Inject()(dbConfigProvider: DatabaseConfigProvider)  {
  //extends ProfileDAO (dbConfigProvider){
private  val dbConfig = dbConfigProvider.get[JdbcProfile]

 val residentTable = TableQuery[ResidentTable]
import dbConfig._

  def create(request: Resident): Future[Resident]  =    db.run(residentTable.returning(residentTable) +=   request )





}
