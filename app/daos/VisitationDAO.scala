package daos

import db.tables._
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery


@Singleton
class VisitationDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  val guestTable = TableQuery[GuestTable]
  val profileTable = TableQuery[ProfileTable]

  val visitationTable = TableQuery[VisitationTable]
  val stationTable = TableQuery[StationTable]
  val kioskTable = TableQuery[KioskTable]

  import dbConfig._


  def create(request: Visitation) = db.run(visitationTable.returning(visitationTable) += request)

  //todo; get visitation on a given host.. day etc.
  //  organisation_id:Option[Int],station_id:Option[Int] ,kiosk_id:Option[Int],offset:Int, limit:Int
  def list(organisation_id: Option[Int], station_id: Option[Int], kiosk_id: Option[Int], offset: Int, limit: Int): Unit = {
    val x = for {

      xc <- {

        val initQuery  = {
          visitationTable join guestTable on (_.guest_id === _.id) joinLeft
          profileTable on (_._1.host_id === _.id) joinLeft
          //join the station if exists
          stationTable on (_._1._1.station_id === _.id) joinLeft
          //kiosk table
          kioskTable on (_._1._1._1.kiosk_id === _.id) join
          //Guest to profile  information
          profileTable on (_._1._1._1._2.profile_id === _.id)
        }

        //Fetch by Organization Query
        val orgQuery = for{
          query <- organisation_id match {
            case Some(value) => initQuery
            case None => initQuery
          }
        }yield (query)

        initQuery
      }


    } yield (xc)


  }


}
