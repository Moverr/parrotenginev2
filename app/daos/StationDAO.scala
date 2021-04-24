package daos

import java.sql.Timestamp

import controllers.requests.StationRequest
import controllers.responses.StationResponse
import db.tables.{Organization, OrganizationTable, StationTable, User, UserTable}

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
  def createOrganisation(station:StationRequest): Either[Throwable,Future[StationResponse]] = {
    //todo: Check that station does not Exist
    //todo: Create
   // db.run(orgTable.returning(orgTable) += Organization(0L,name,details,owner, new Timestamp(0L),0L,new Timestamp(0L),0L))
    ???
  }


  //todo: list station in an organization
  def createOrganisation(organization: Int,offset:Int,limit:Int):Either[Throwable,Seq[StationResponse]]={
    ???
  }
  //todo: list stations in an account
  def createOrganisation(offset:Int,limit:Int):Either[Throwable,Seq[StationResponse]]={
    ???
  }
  //todo: update station
  def createOrganisation(id:Int,station:StationRequest):Either[Throwable,StationResponse]={
    ???
  }
  //todo: Archive Station
  def Archive(id:Int):Either[Throwable,Unit]={
    ???
  }
  //todo: Populate Response
}
