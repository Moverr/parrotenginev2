package services

import controllers.requests.{GuestProfileRequest, ProfileRequest}
import controllers.responses.ProfileResponse
import daos.{GuestDAO, ProfileDAO, ResidentProfileDAO}
import db.tables.{Guest, Profile, Resident}
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class GuestService @Inject()(
                              residentDAO: ResidentProfileDAO
                              , profileDAO: ProfileDAO
                              , guestDAO: GuestDAO
                              , stationService: StationService

                            ) {

  //todo create
  //list guests on a given statioon or visitor on a given day

  //todo: create
  def create(request: ProfileRequest): Either[Throwable, ProfileResponse] = {
    request match {
      case GuestProfileRequest(surname, othername, profiletype, gender, host_id, registerDate, location) => {
        //todo: check if host exists
        val result: Future[Option[(Resident, Profile)]] = residentDAO.get(host_id)
        //todo: check to see that username and othernames are mandatory
        if (surname.isEmpty && othername.isEmpty) Left(throw new Exception("Names are mandatory "))
        else {
          //todo: check if there exists a profile of the guest. as in the gueust has ever been there before.
          val res =    for{
            x <- guestDAO.getByProfileName(Some(surname), Some(othername)).map(x=>x.get)
          }yield (x)





          ???
        }
      }
      case _ => Left(new Exception("Something went wrong"))
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
