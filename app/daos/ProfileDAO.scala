package daos

import java.sql.Timestamp

import controllers.requests.ProfileRequest
import controllers.responses.AuthResponse
import db.tables.{OrganizationTable, Profile, ProfileTable, User}

import scala.concurrent.Future
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery



@Singleton
class ProfileDAO   @Inject()(dbConfigProvider: DatabaseConfigProvider) {


  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy  val profileTable = TableQuery[ProfileTable]
  lazy  val organizationTable = TableQuery[OrganizationTable]

  import dbConfig._

  //todo: Create Profile ..
  //todo: create station

  def create(  request: Profile): Future[Profile] =  {

    val query = profileTable.returning(profileTable) += request
    db.run(query)


//   val res =  db.run(profileTable.returning(profileTable) +=   request )
//    res
  }




  //todoo: update Profile

  //todo: get Profile


  //todo: Get Profiles

  //todo: Archivev Profiles



}
