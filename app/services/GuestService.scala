package services

import controllers.requests.{GuestProfileRequest, ProfileRequest}
import controllers.responses.ProfileResponse
import daos.{GuestDAO, ProfileDAO, ResidentProfileDAO}
import db.tables.{Guest, Profile, Resident}
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

                            ) {

  //todo create
  //list guests on a given statioon or visitor on a given day
  def CreateGuestProfile(request: GuestProfileRequest): Future[Profile] = {
    val record = for {
      future1 <- profileDAO.create(request.surname, request.othername, request.gender, 0L, None, "RESIDENT").recoverWith {
        case exception: Throwable => Future.failed(new Exception(exception.getMessage))
      }

      future2 <- {
        guestDAO.create(resident)
      }


    }

    ???
  }
  //todo: create
  def CreateInvitation(request: ProfileRequest): Either[Throwable, ProfileResponse] = {
    request match {
      case GuestProfileRequest(surname, othername, profiletype, gender, host_id, registerDate, location) => {
        //todo: check if host exists ?? jump this



       val response:Option[(Guest, Profile)] =  Await.result( guestDAO.getByProfileName(Some(surname),Some(othername)),Duration.Inf)

        //todo: create a  profile if does not exist.
        // else continue.

      response match {
        case Some(value:(Guest,Profile)) => {
          val profile_id =   value._2.id
          val guest_id = value._1.id
          //todo: create profile

        }    // todo  call the other guy and continue
        case None =>     // continue
      }



        if(response.isEmpty){
          //todo: create the profile ..
        }
      }
        ???
      }

    //1: u dont need authorization
    //2: check to see that the host id exists
    //3: check to see that profile for guest exists.
    //4: create profile if not exists
    //5: register vistor in the book register
    //6: assigng registration external_id
  }



  def populateResponse(): ProfileResponse = {
    //GuestResponse
    ???
  }

  //update profile info
  //cance information

  //  register profile.
  //  // check if  host exists. .
  //  visitation type..


}
