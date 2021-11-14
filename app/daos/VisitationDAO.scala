package daos

import db.tables.{Guest, GuestTable, ProfileTable, Visitation, VisitationTable}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import slick.jdbc.PostgresProfile.api._


@Singleton
class VisitationDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  val guestTable = TableQuery[GuestTable]
  val profileTable = TableQuery[ProfileTable]

  val visitationTable = TableQuery[VisitationTable]
  val guestTable = TableQuery[GuestTable]
  val profileTable = TableQuery[ProfileTable]
  import dbConfig._


  def create(request: Visitation) =   db.run(visitationTable.returning(visitationTable) += request)

  //todo; get visitation on a given host.. day etc.
//  organisation_id:Option[Int],station_id:Option[Int] ,kiosk_id:Option[Int],offset:Int, limit:Int
  def list(organisation_id:Option[Int],station_id:Option[Int],kiosk_id:Option[Int],offset:Int, limit:Int): Unit ={
  val x = for{
    vistation <- visitationTable join guestTable on (_.guest_id === _.id) joinLeft profileTable on(_._1.host_id === _.id)
  }yield(vistation)


  }


}
