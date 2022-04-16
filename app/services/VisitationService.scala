package services

import controllers.requests.VisitationRequest
import controllers.responses.{GuestInvitationResponse, GuestResponse, HostResponse, UserResponse}
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


        val response: Option[Profile] = Await.result(guestDAO.getByProfileName(Some(profile.surname), Some(profile.othername)), Duration.Inf)


        response match {
          case Some(value:  Profile) => {

            val visitation = Visitation(0L, value.id, host_id, Some(getCurrentTimeStamp()), None, Some(stationId), Some(kiosk_id), Some("pending"), Utilities.RandomString())
            val record = for {
              host <- profileDAO.getProfileById(host_id)
              response <- createVisitation(visitation).map(recordVisitation => populateResponse(Some(value),host,recordVisitation))

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


  def CreateGuestProfile(request: VisitationRequest): Future[Profile] =  profileDAO.create(request.profile.surname, request.profile.othername, request.profile.gender, 0L, None, "GUEST")



  def createVisitation(visitation: Visitation): Future[Visitation] = visitationDAO.create(visitation)


  //todo: list visitations
  def list(authResponse: UserResponse,  station_id: Option[Long], kiosk_id: Option[Long], offset: Int, limit: Int): Either[java.lang.Throwable, Future[Seq[GuestInvitationResponse]]] = {
    if (authResponse == null) return Left(new Exception("Invalid Authentication"))

    val visitationRecords = for  {
        records <- { visitationDAO.list(station_id, kiosk_id, offset, limit)  }
          .map(y=>y.map(x=>populateResponse(Some(x._1._2),Some(x._2),x._1._1)))

    } yield(records)

  Right(visitationRecords)


  }

  def populateResponse(guestProfile: Option[Profile],hostProfile: Option[Profile],   visitation: Visitation): GuestInvitationResponse =
 GuestInvitationResponse(getGuestProfile(guestProfile)  , getHostProfile(hostProfile), visitation.time_in, visitation.time_out, visitation.reference_id, getStatus(visitation.status)
 ,visitation.station_id,visitation.kiosk_id
 )




  private def getStatus(statusOption:Option[String]) = {
    statusOption match {
      case Some(value) => value
      case None => "N/A"
    }
  }

  private def getHostProfile(hostProfile: Option[Profile]) =   hostProfile match {
      case Some(value) => Some(populateResponse12(value))
      case None => None
    }

  //toodo: get guest proofile
  private def getGuestProfile(guestProfile: Option[Profile]) =    guestProfile match {
      case Some(value) => Some(populateResponse1(value))
      case None => None
    }

  def populateResponse1(profile: Profile): GuestResponse = GuestResponse(profile.id, profile.surname, profile.other_names, profile.profile_type, profile.gender)
  def populateResponse12(profile: Profile): HostResponse = HostResponse(profile.id, profile.surname, profile.other_names, profile.profile_type, profile.gender)


}

