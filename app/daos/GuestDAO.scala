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

  //todoo: Just get one existing profile of a guest in the database .
  /**
   *   *
   * @param surname
   * @param otherName
   * @return
   * @FutureIMpl: make sure you are able filter both surname and otherName, since they might be interchanged at entrace.
   */

/*

If profile exists in the same name, we extend id else we gegt the rest.

 */
  def getByProfileName(surname: Option[String], otherName: Option[String]): Future[Option[(Guest, Profile)]] = {

    val records = for {
      (guest, profile) <- {
        val record = guestTable join profileTable on (_.profile_id === _.id)


        val firstNameQuery =  for(query<- surname match {
          case Some(surname) => record.filter(_._2.surname === surname)
          case None => record
        }) yield (query)



        val lastNameQuery =  for(query<- otherName match {
          case Some(otherName) => firstNameQuery.filter(_._2.other_names  === otherName)
          case None => firstNameQuery
        }) yield (query)


        lastNameQuery drop (0) take (1)
      }

    }
      yield (guest, profile)


    db.run(records.result.headOption)
  }


  def create(request: Guest) =   db.run(guestTable.returning(guestTable) += request)


}
