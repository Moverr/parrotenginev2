package daos


import db.tables.{GuestTable, KioskTable, OrganizationTable, Profile, ProfileTable, StationTable, Visitation, VisitationTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.Future


@Singleton
class VisitationDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  val guestTable = TableQuery[GuestTable]
  val profileTable = TableQuery[ProfileTable]

  val visitationTable = TableQuery[VisitationTable]
  val stationTable = TableQuery[StationTable]
  val kioskTable = TableQuery[KioskTable]
  val orgTable = TableQuery[OrganizationTable]

  import dbConfig._


  def create(request: Visitation) = db.run(visitationTable.returning(visitationTable) += request)

  //todo; get visitation on a given host.. day etc.
  //  organisation_id:Option[Int],station_id:Option[Int] ,kiosk_id:Option[Int],offset:Int, limit:Int
  def list(station_id: Option[Long], kiosk_id: Option[Long], offset: Int, limit: Int): Future[Seq[((Visitation, Profile), Profile)]] = {

    val query = for {
      query <- {
        val record = visitationTable join profileTable on (_.guest_profile_id === _.id) join profileTable on (_._1.host_profile_id === _.id)

        val stationQuery = for (query <- station_id match {
          case Some(station_id) => record.filter(_._1._1.station_id === station_id)
          case None => record
        }) yield (query)


        val kiokQuery = for (query <- kiosk_id match {
          case Some(kiosk_id) => stationQuery.filter(_._1._1.kiosk_id === kiosk_id)
          case None => stationQuery
        }) yield (query)


        kiokQuery.drop(offset).take(limit)
      }


    } yield (query)


    db.run(query.result)


  }

}
