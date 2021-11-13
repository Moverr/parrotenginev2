package services

import controllers.requests.{GuestProfileRequest, ProfileRequest}
import controllers.responses.{GeneralProfileResponse, GuestInvitationResponse, GuestResponse}
import daos.{GuestDAO, ProfileDAO, ResidentProfileDAO, VisitationDAO}
import db.tables.{Guest, Profile, Visitation}
import helpers.Utilities
import helpers.Utilities.getCurrentTimeStamp
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


@Singleton
class VisitationService @Inject()(
                              residentDAO: ResidentProfileDAO
                              , profileDAO: ProfileDAO
                              , guestDAO: GuestDAO
                              , stationService: StationService
                              , visitationDAO: VisitationDAO

                            ) {

/*
1 : You dont need authorization to register a visitation
2 : you can specify the host
3 : The host will be notified of a visitor through channels like ....

    //1: u dont need authorization
    //2: check to see that the host id exists
    //3: check to see that profile for guest exists.
    //4: create profile if not exists
    //5: register vistor in the book register
    //6: assigng registration external_id
 */
  def Invitation(request: ProfileRequest): Either[Throwable, Future[GuestInvitationResponse]] = {
    request match {
      case GuestProfileRequest(surname, othername, profiletype, gender, host_id, registerDate, location) => {
        //todo: check if host exists ?? jump this


        val response: Option[(Guest, Profile)] = Await.result(guestDAO.getByProfileName(Some(surname), Some(othername)), Duration.Inf)

        //todo: create a  profile if does not exist.
        // else continue.

        response match {
          case Some(value: (Guest, Profile)) => {
            //todo: create profile
            val visitation = Visitation(0L, value._1.id, host_id, Some(getCurrentTimeStamp()), None, None, None, Some("pending"),Utilities.RandomString())
            val record = for {
              response <- createVisitation(visitation).map(x => populateResponse(value._2,value._1,x))


            } yield (response)

            Right(record)
          }
          case None => {

            val response = for {
              resp <- CreateGuestProfile(GuestProfileRequest(surname, othername, profiletype, gender, host_id, registerDate, location))
              visitation = Visitation(0L, resp._1.id, host_id, Some(getCurrentTimeStamp()), None, None, None, Some("pending"),Utilities.RandomString())
              result <- createVisitation(visitation).map(x => populateResponse(resp._2,resp._1,x))

            } yield (result)

            Right(response)
          }


        }


      }

    }

  }

  //todo create
  //list guests on a given statioon or visitor on a given days
  def CreateGuestProfile(request: GuestProfileRequest): Future[(Guest, Profile)] = {
    val record = for {
      profile  <- profileDAO.create(request.surname, request.othername, request.gender, 0L, None, "GUEST").recoverWith {
        case exception: Throwable => Future.failed(new Exception(exception.getMessage))
      }

      future2  <- {
        val guest: Guest = Guest(0L, profile.id, None, getCurrentTimeStamp(), None, getCurrentTimeStamp())
        guestDAO.create(guest).recoverWith {
          case exception: Throwable => Future.failed(new Exception(exception.getMessage))
        }
      }


    } yield (future2, profile)

    record
  }

  def createVisitation(visitation: Visitation): Future[Visitation] = visitationDAO.create(visitation)

  def populateResponse(profile: Profile, guest: Guest,visitation: Visitation): GuestInvitationResponse = {
    //GuestResponse
    val profileResponse = populateResponse(profile)
    val response = GuestInvitationResponse(profileResponse ,visitation.time_in  ,visitation.time_out  ,visitation.reference_id,visitation.status.get)
    response
  }


  //update profile info
  //cance information

  //  register profile.
  //  // check if  host exists. .
  //  visitation type..


  //todo: populate basic profile
  def populateResponse(profile:Profile):GuestResponse=  GuestResponse(profile.id,profile.surname,profile.other_names,profile.profile_type,profile.gender)


}
