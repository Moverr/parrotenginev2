package daos

import db.tables.{ProfileTable, ResidentTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

@Singleton
class GuestDAO  @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  //extends ProfileDAO (dbConfigProvider){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  val residentTable = TableQuery[ResidentTable]
  val profileTable = TableQuery[ProfileTable]

  import dbConfig._


}
