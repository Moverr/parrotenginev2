package daos

import db.tables.{Guest, GuestTable, Profile, ProfileTable, Resident, ResidentTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.Future


import slick.jdbc.PostgresProfile.api._

@Singleton
class GuestDAO  @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  //extends ProfileDAO (dbConfigProvider){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  val guestTable = TableQuery[GuestTable]
  val profileTable = TableQuery[ProfileTable]

  import dbConfig._
  def getByProfileName(firstname: Option[String], lastname: Option[String]): Future[Seq[(Guest, Profile)]] = {

    val records = for {
      (resident, profile) <- {
        val record = guestTable join profileTable on (_.profile_id === _.id)


        val firstNameQuery =  for(query<- firstname match {
          case Some(firstName) => record.filter(_._2.surname === firstName)
          case None => record
        }) yield (query)



        val lastNameQuery =  for(query<- lastname match {
          case Some(lastName) => firstNameQuery.filter(_._2.other_names  === lastName)
          case None => firstNameQuery
        }) yield (query)


        lastNameQuery drop (0) take (1)
      }

    }
      yield (resident, profile)


    db.run(records.result)
  }




}
