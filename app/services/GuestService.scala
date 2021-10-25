package services

import java.sql.Timestamp

import controllers.requests.{GuestProfileRequest, ProfileRequest}
import controllers.responses.{GuestInvitationResponse, ProfileResponse}
import daos.{GuestDAO, ProfileDAO, ResidentProfileDAO, VisitationDAO}
import db.tables.{Guest, Profile, Resident, Visitation}
import helpers.Utilities.getCurrentTimeStamp
import javax.inject.{Inject, Singleton}
import play.api.mvc.Results

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.impl.Promise


@Singleton
class GuestService @Inject()(
                              residentDAO: ResidentProfileDAO
                              , profileDAO: ProfileDAO
                              , guestDAO: GuestDAO
                              , stationService: StationService
                            ,visitationDAO: VisitationDAO

                            ) {

  //todo create
  //list guests on a given statioon or visitor on a given day
  def CreateGuestProfile(request: GuestProfileRequest): Future[(Guest,Profile)] = {
    val record = for {
      future1:Future[Profile] <- profileDAO.create(request.surname, request.othername, request.gender, 0L, None, "RESIDENT").recoverWith {
        case exception: Throwable => Future.failed(new Exception(exception.getMessage))
      }

      future2:Future[Guest] <- {
        val guest:Guest =   Guest(0L,future1.id,None,getCurrentTimeStamp(),None,getCurrentTimeStamp())
        guestDAO.create(guest).recoverWith {
          case exception: Throwable => Future.failed(new Exception(exception.getMessage))
        }
      }


    } yield (future2,future1)

    record
  }

  def createVisitation(visitation: Visitation): Future[Visitation] =  visitationDAO.create(visitation)

  //todo: create
  def Inviation(request: ProfileRequest): Either[Throwable, GuestInvitationResponse] = {
    request match {
      case GuestProfileRequest(surname, othername, profiletype, gender, host_id, registerDate, location) => {
        //todo: check if host exists ?? jump this



       val response:Option[(Guest, Profile)] =  Await.result( guestDAO.getByProfileName(Some(surname),Some(othername)),Duration.Inf)

        //todo: create a  profile if does not exist.
        // else continue.

      response match {
        case Some(value:(Guest,Profile)) => {
          //todo: create profile
          val visitation = Visitation(0L,value._1.id,host_id,Some(getCurrentTimeStamp()),None,None,None,Some("pending"))
        for {
              response <- createVisitation(visitation)


             } yield (response)

          Right( populateResponse(???))
        }    // todo  call the other guy and continue
        case None =>  {

      val result =    for {
           resp <-CreateGuestProfile(GuestProfileRequest(surname, othername, profiletype, gender, host_id, registerDate, location) )
           visitation = Visitation(0L,resp._1.id,host_id,Some(getCurrentTimeStamp()),None,None,None,Some("pending"))
           result = createVisitation(visitation)
         }yield (result)
          Right( populateResponse(???))
        }


      }



      }

      }

    //1: u dont need authorization
    //2: check to see that the host id exists
    //3: check to see that profile for guest exists.
    //4: create profile if not exists
    //5: register vistor in the book register
    //6: assigng registration external_id
  }



  def populateResponse(visitation: Visitation): GuestInvitationResponse = {
    //GuestResponse
    ???
  }

  //update profile info
  //cance information

  //  register profile.
  //  // check if  host exists. .
  //  visitation type..


}
