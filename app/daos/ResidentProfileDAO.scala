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



@Singleton
class ResidentProfileDAO    @Inject()(dbConfigProvider: DatabaseConfigProvider) extends ProfileDAO (dbConfigProvider){
  //extends ProfileDAO (dbConfigProvider){
private  val dbConfig = dbConfigProvider.get[JdbcProfile]
override lazy  val profileTable = TableQuery[ProfileTable]
override lazy  val organizationTable = TableQuery[OrganizationTable]

import dbConfig._


  //todo: create item
  def create( authResponse: AuthResponse,profileRequest: ResidentProfileRequest): Future[Profile] = {
    db.run(profileTable.returning(profileTable) +=  Profile(0L,0l,"","","","",0L, null,0L, null) )
  }

  //todo: get tiems and move on


}
