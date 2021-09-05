package services

import controllers.requests.{ResidentProfileRequest, StationRequest}
import controllers.responses.{AuthResponse, ResidentProfileResponse, StationResponse}
import daos._
import db.tables.{Organization, Profile, Resident, Station}
import helpers.Utilities.getCurrentTimeStamp
import javax.inject.{Inject, Singleton}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class ResidentialService  @Inject()(
                                     residentDAO: ResidentProfileDAO
                                     ,stationService: StationService

                                   ) {

  //  def inviteUser(): PartialFunction[Try[Profile],] = ???

  def create(authResponse: AuthResponse, request:ResidentProfileRequest): Either[java.lang.Throwable,Future[ResidentProfileResponse]]= {

    if (authResponse == null) return Left(new Exception("Invalid Authentication"))
    val resp:Either[java.lang.Throwable,Future[Option[StationResponse]]] =  stationService.get(authResponse,request.stationid)


    resp match {
      case Left(value) =>     Left(new Exception("Station does not exists"))
      case Right(value) => {
        val  res:Option[StationResponse] = Await.result(value,Duration.Zero)
        val profile = Profile(0L,None,request.surname,request.othername,request.gender,"RESIDENT",authResponse.user_id,getCurrentTimeStamp,authResponse.user_id,  getCurrentTimeStamp )

        val profileResponse: Profile =    Await.result(residentDAO.create(profile) ,Duration.Zero)

        val residentProfile:Resident = Resident(0L,profileResponse.id,authResponse.user_id,getCurrentTimeStamp,authResponse.user_id,getCurrentTimeStamp,request.stationid,request.joinDate)
        //todo: work upon adding the residentProfile in there table
        Right(Future.successful( populateResponse(profileResponse,residentProfile)))


      }
    }


  }

  def populateResponse(  entity:Profile,resident: Resident): ResidentProfileResponse =
    ResidentProfileResponse(
      entity.surname
      ,entity.other_names
      ,entity.profile_type
      ,entity.gender
      ,resident.station_id.toInt
      ,resident.join_date
      ,resident.created_on.getTime
      ,"NA"

    )



  // todo : create  resident profile

  //todo: invite them to create user credentials

  //todo: let them create there own credentials

  //todo: can achive or activate them depending on the permissions

  //todo: create

  //todo: update

  //todo: archive

  //todo:list
}
