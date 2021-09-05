package daos


import java.sql.Timestamp

import controllers.requests.{ResidentProfileRequest, StationRequest}
import controllers.responses.{AuthResponse, StationResponse}
import db.tables.{Organization, OrganizationTable, Profile, ProfileTable, Station, StationTable, User, UserTable}

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
class ResidentProfileDAO    @Inject()(dbConfigProvider: DatabaseConfigProvider) extends ProfileDAO (dbConfigProvider){
  //extends ProfileDAO (dbConfigProvider){
private  val dbConfig = dbConfigProvider.get[JdbcProfile]
override lazy  val profileTable = TableQuery[ProfileTable]
override lazy  val organizationTable = TableQuery[OrganizationTable]

import dbConfig._


  //todo: create item
  def create(authResponse: AuthResponse, request: ResidentProfileRequest): Future[Profile] = {
    val profile:Future[Profile] = db.run(profileTable.returning(profileTable) +=  Profile(0L,None,request.surname,request.othername,request.gender,"RESIDENT",authResponse.user_id,getCurrentTimeStamp,authResponse.user_id,  getCurrentTimeStamp ) )
    profile
  }

  def createResident(authResponse: AuthResponse, request: ResidentProfileRequest): Unit ={
???
  }



}
