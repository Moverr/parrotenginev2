package daos

import java.sql.Timestamp

import controllers.requests.ProfileRequest
import controllers.responses.UserResponse
import db.tables.{OrganizationTable, Profile, ProfileTable, User}
import helpers.Utilities.getCurrentTimeStamp

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

  def create(surname:String,othername:String,gender:String,author_id:Long,user_id:Option[Long],profile_type:String): Future[Profile] =  {
    val profile = Profile(0L, user_id, surname, othername, gender, profile_type, author_id, getCurrentTimeStamp(), author_id, getCurrentTimeStamp())

    val query = profileTable.returning(profileTable) += profile
    db.run(query)

  }


  //todo: gegt profile by id
  def getProfileById(profile_id:Long): Future[Option[Profile]] ={

    val query = profileTable
      .filter(_.id === profile_id)
      .result.headOption
    db.run(query)
  }




  //todoo: update Profile

  //todo: get Profile


  //todo: Get Profiles

  //todo: Archivev Profiles



}
