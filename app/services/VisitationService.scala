package services

import controllers.requests.{BasicProfileRequest, ProfileRequest, ResidentProfileRequest, VisitationRequest}
import controllers.responses.{GuestInvitationResponse, GuestResponse, UserResponse}
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
                                   , visitationDAO: VisitationDAO

                                 ) {

  /*
  1 : You dont need authorization to register a visitation
  2 : you can specify the host
  3 : The host will be notified of a visitor through channels like ....

      //1: u dont need authorization
      //2: check to see that the host id exists
      //3: check to see that guestProfile for guest exists.
      //4: create guestProfile if not exists
      //5: register vistor in the book register
      //6: assigng registration external_id
   */
  def createGuestInvitation(request: ProfileRequest): Either[Throwable, Future[GuestInvitationResponse]] = {


    request match {
      case    VisitationRequest(profile, host_id, registerDate, location, stationId, kioskId) => {
        //todo: check if host exists ?? jump this


        val response: Option[(Guest, Profile)] = Await.result(guestDAO.getByProfileName(Some(profile.surname), Some(profile.othername)), Duration.Inf)

        //todo: create a  guestProfile if does not exist.
        // else continue.

        response match {
          case Some(value: (Guest, Profile)) => {
            //todo: create guestProfile
            //             visitation = Visitation(0L, value._1.id, host_id, Some(getCurrentTimeStamp()),   Some(Timestamp.valueOf(java.time.LocalDate.now().toString) , java.time.LocalDate.now(), Some(stationId), deviceId,Some("pending"),Utilities.RandomString())
            val visitation = Visitation(0L, value._1.profile_id, host_id, Some(getCurrentTimeStamp()), None, Some(stationId.longValue()), Some(kiosk_id.longValue()), Some("pending"), Utilities.RandomString())
            val record = for {
              response <- createVisitation(visitation).map(x => populateResponse(value._2, value._1, x))


            } yield (response)

            Right(record)
          }
          case None => {

            val record: Future[(Guest, Profile)] = CreateGuestProfile(GuestProfileRequest(surname, othername, profiletype, gender, host_id, registerDate, location, deviceId, stationId, kiosk_id) )

           val reat = for {
             xt <-{

                   val te = record.value.get.get


                 val visitation = Visitation(0L, te._1.profile_id, host_id, Some(getCurrentTimeStamp()), None, Some(stationId.longValue()), Some(kiosk_id.longValue()), Some("pending"), Utilities.RandomString())
                 val response = createVisitation(visitation).map(x => populateResponse(te._2, te._1, x))
                 response

             }
            } yield (xt)

            Right(reat)
          }


        }

      }

    }
  }

    //todo: list visitations
    def list(authResponse: UserResponse, organisation_id: Option[Int], station_id: Option[Int], kiosk_id: Option[Int], offset: Int, limit: Int): Either[java.lang.Throwable, Future[Seq[GuestInvitationResponse]]] = {
      if (authResponse == null) return Left(new Exception("Invalid Authentication"))

      Right(
      visitationDAO.list(organisation_id, station_id, kiosk_id, offset, limit).map(y => y.map(record => populateResponse(record._1._1._1._2.get, record._1._1._1._1._2, record._1._1._1._1._1)))
      )
    }


    //todo create
    //list guests on a given statioon or visitor on a given days
    def CreateGuestProfile(request: VisitationRequest): Future[(Guest, Profile)] = {
      val record = for {
        profile <- profileDAO.create(request.surname, request.othername, request.gender, 0L, None, "GUEST").recoverWith {
          case exception: Throwable => Future.failed(new Exception(exception.getMessage))
        }

        future2 <- {
          val guest: Guest = Guest(0L, profile.id, None, getCurrentTimeStamp(), None, getCurrentTimeStamp())
          guestDAO.create(guest).recoverWith {
            case exception: Throwable => Future.failed(new Exception(exception.getMessage))
          }
        }


      } yield (future2, profile)

      record
    }

    def createVisitation(visitation: Visitation): Future[Visitation] = visitationDAO.create(visitation)

    def populateResponse(guestProfile: Profile, guest: Guest, visitation: Visitation): GuestInvitationResponse = {
      //GuestResponse
      val profileResponse = populateResponse1(guestProfile)
      val response = GuestInvitationResponse(profileResponse, visitation.time_in, visitation.time_out, visitation.reference_id, visitation.status.get)
      response
    }


    //update guestProfile info
    //cance information

    //  register guestProfile.
    //  // check if  host exists. .
    //  visitation type..


    //todo: populate basic guestProfile
      def populateResponse1(profile: Profile): GuestResponse = GuestResponse(profile.id, profile.surname, profile.other_names, profile.profile_type, profile.gender)


  }

