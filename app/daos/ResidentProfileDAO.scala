package daos


import db.tables.{Profile, ProfileTable, Resident, ResidentTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import db.tables._
import org.hibernate.engine.internal.JoinSequence
import org.hibernate.engine.internal.JoinSequence.Join

import scala.concurrent.Future





@Singleton
class ResidentProfileDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  //extends ProfileDAO (dbConfigProvider){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  val residentTable = TableQuery[ResidentTable]
  val profileTable = TableQuery[ProfileTable]

  import dbConfig._

  /*
  Create resident data to the database
   */
  def create(request: Resident): Future[Resident] = db.run(residentTable.returning(residentTable) += request)


  //todo: list items
  def list(owner_id: Option[Long], station_id: Option[Long], offset: Int, limit: Int,searchQuery:Option[String]): Future[Seq[(Resident, Profile)]] = {

    val records = for {
      (resident, profile) <- {
        val record = residentTable join profileTable on (_.profile_id === _.id)

        val stationQuery =  for(query<- station_id match {
          case Some(station_id) => record.filter(_._1.station_id  === station_id)
          case None => record
        }) yield (query)



        val ownerQuery =  for(query<- owner_id match {
          case Some(owner_id) => stationQuery.filter(_._2.author_id  === owner_id)
          case None => stationQuery
        }) yield (query)


        val searchedQuery = for(query<- searchQuery match {
          case Some(searchCriteria) => stationQuery.filter(_._2.surname === s"%$searchCriteria%" ) .filter(_._2.other_names === s"%$searchCriteria%" )
          case None => ownerQuery
        }) yield (query)


        searchedQuery drop (offset) take (limit)
      }

    }
      yield (resident, profile)


    db.run(records.result)
  }


  def get(id:Long): Future[Option[(Resident, Profile)]] ={
    val record = residentTable join profileTable on (_.profile_id === _.id)
    db.run(record.filter(_._1.id===id) .result.headOption)
  }


  //todo: upate and move on
  def archive(id:Long): Unit ={
???
  }
}
