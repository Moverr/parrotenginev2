package services

import controllers.requests.VisitationRequest
import controllers.responses.{GuestInvitationResponse, GuestResponse, HostResponse}
import daos.{GuestDAO, ProfileDAO, ResidentProfileDAO, VisitationDAO}
import db.tables.{Profile, Visitation}
import helpers.Utilities
import helpers.Utilities.getCurrentTimeStamp
import javax.inject.{Inject, Singleton}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.impl.Promise
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration._


@Singleton
class VisitationService @Inject()(
                                   residentDAO: ResidentProfileDAO
                                   , profileDAO: ProfileDAO
                                   , guestDAO: GuestDAO
                                   , visitationDAO: VisitationDAO

                                 ) {

  def createGuestInvitation(request: VisitationRequest): Either[Throwable, Future[GuestInvitationResponse]] = {

    request match {
      case VisitationRequest(profile, host_id, registerDate, location, stationId, kiosk_id) => {


        val response: Option[( Profile)] = Await.result(guestDAO.getByProfileName(Some(profile.surname), Some(profile.othername)), Duration.Inf)


        response match {
          case Some(value: ( Profile)) => {

            val visitation = Visitation(0L, value.id, host_id, Some(getCurrentTimeStamp()), None, Some(stationId), Some(kiosk_id), Some("pending"), Utilities.RandomString())
            val record = for {
              response <- createVisitation(visitation).map(x => populateResponse(None,None,x))

            } yield (response)

            Right(record)
          }
          case None => {



            val result = for {
                profileResponse <-  CreateGuestProfile(request)

              record <- {

                val visitation =  Visitation(0L, profileResponse.id, host_id, Some(getCurrentTimeStamp()), None, Some(stationId), Some(kiosk_id), Some("pending"), Utilities.RandomString())
                val response =  visitationDAO.create(visitation).map(visitattionRecord => populateResponse(None,None,visitattionRecord))
                response

              }
            } yield (record)

            Right(result)
          }


        }

      }

    }
  }


  def CreateGuestProfile(request: VisitationRequest): Future[Profile] = {

     profileDAO.create(request.profile.surname, request.profile.othername, request.profile.gender, 0L, None, "GUEST")

    /*
    val record = for {
      profile <- profileDAO.create(request.profile.surname, request.profile.othername, request.profile.gender, 0L, None, "GUEST").recoverWith {
        case exception: Throwable => Future.failed(new Exception(exception.getMessage))



      future2 <- {
        val guest: Guest = Guest(0L, profile.id, None, getCurrentTimeStamp(), None, getCurrentTimeStamp())
        guestDAO.create(guest).recoverWith {
          case exception: Throwable => Future.failed(new Exception(exception.getMessage))
        }
      }





    } yield (profile)

     */
  }

  def createVisitation(visitation: Visitation): Future[Visitation] = visitationDAO.create(visitation)


  //todo: list visitations
  def list(authResponse: UserResponse,  station_id: Option[Int], kiosk_id: Option[Int], offset: Int, limit: Int): Either[java.lang.Throwable, Future[Seq[GuestInvitationResponse]]] = {
    if (authResponse == null) return Left(new Exception("Invalid Authentication"))

    val visitationRecords = for  {
        records <- { visitationDAO.list(station_id, kiosk_id, offset, limit)  }

    } yield(records)


    val record:scala.collection.mutable.Buffer[GuestInvitationResponse] =  ArrayBuffer()


     visitationRecords.map(
       y=> y.foreach{x=>

         record += populateResponse(Some(x._1._2),Some(x._2),x._1._1)
       }
     )


    Right(Future.successful(record.toSeq))

  }

  def populateResponse(guestProfile: Option[Profile],hostProfile: Option[Profile],   visitation: Visitation): GuestInvitationResponse = {
    //GuestResponse
    val guest = guestProfile match {
      case Some(value) => populateResponse1(value)
      case None => None
    }
    val host = hostProfile match {
      case Some(value) =>    populateResponse12(value)
      case None => None
    }


     val response = controllers.responses.GuestInvitationResponse(None,None, visitation.time_in, visitation.time_out, visitation.reference_id, visitation.status.get)
     response

  }


  def populateResponse1(profile: Profile): GuestResponse = GuestResponse(profile.id, profile.surname, profile.other_names, profile.profile_type, profile.gender)
  def populateResponse12(profile: Profile): HostResponse = HostResponse(profile.id, profile.surname, profile.other_names, profile.profile_type, profile.gender)


}

