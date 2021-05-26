package daos

import db.tables.{OrganizationTable, ProfileTable, StationTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

@Singleton
class ProfileDAO   @Inject()(dbConfigProvider: DatabaseConfigProvider) {


  private  val dbConfig = dbConfigProvider.get[JdbcProfile]
  lazy  val stationTable = TableQuery[ProfileTable]
  lazy  val organizationTable = TableQuery[OrganizationTable]

  import dbConfig._

  //todo: Create Profile ..

  //todoo: update Profile

  //todo: get Profile


  //todo: Get Profiles

  //todo: Archivev Profiles



}
