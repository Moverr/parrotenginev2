package daos

import db.tables._
import javax.inject.{Inject, Singleton}
import org.hibernate.engine.internal.JoinSequence
import org.hibernate.engine.internal.JoinSequence.Join
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
  def list(organisation_id: Option[Int], station_id: Option[Int], kiosk_id: Option[Int], offset: Int, limit: Int): Future[Seq[(((((Visitation,Guest),Option[Profile]),Option[Profile]),Option[Kiosk]),Profile)]] = {

    ???
  }

   // val records = for {

   //   record <- {

   //     val initQuery  = {
       //   visitationTable join  guestTable on (_.guest_id === _.id) join
         // stationTable on (_._1.station_id === _.id) join
        //  kioskTable on (_._1._1.kiosk_id === _.id) joinLeft
        //    profileTable on (_._1._1._1.host_id === _.id) joinLeft
          //Guest to profile  information
        //  profileTable on (_._1._1._1._2.profile_id === _.id) join
       //odoo mising

     //   }

        //Fetch by kiosk Query
        /*
        val KioskQuery = for{
          query <- kiosk_id match {
            case Some(value) => initQuery.filter(_. === value.toLong)
            case None => initQuery
          }
        }yield (query)


        //Fetch by Station Query
        val stationQuery = for{
          query <- station_id match {
            case Some(value) => KioskQuery.filter(_._1._1._1._1._1.station_id === value.toLong)
            case None => initQuery
          }
        }yield (query)
        */

//        val orgQuery = for{
//          query <- organisation_id match {
//            case Some(value) => {
//
//             stationQuery.filter(_._2. === value.toLong)
//            }
//            case None => stationQuery
//          }
//        }yield (query)


        //todo:

//        initQuery drop offset take limit
      //  initQuery
    //  }


  //  } yield (record)

  //  db.run(records.result)
  //}


}
