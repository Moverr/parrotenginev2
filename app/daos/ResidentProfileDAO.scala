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

  /*
  Create resident data to the database
   */
  def create(request: Resident): Future[Resident]  =    db.run(residentTable.returning(residentTable) +=   request )


  //todo: list items
  def list(owner_id:Option[Long],station_id:Option[Long],offset:Int,limit:Int): Future[Seq[Resident]] ={
    val records = residentTable
      .drop(offset)
      .take(limit)


    if(station_id.isDefined){
      records.filter(_.station_id === station_id.get)
    }
    if(owner_id.isDefined){
      records.filter(_.author_id === owner_id.get)
    }

    db.run(records.result)
  }



}
