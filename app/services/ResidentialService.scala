package services

import controllers.requests.{ResidentProfileRequest, StationRequest}
import controllers.responses.{AuthResponse, ResidentProfileResponse, StationResponse}
import daos._
import db.tables.{Organization, Profile, Station}
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
        var res:Option[StationResponse] = Await.result(value,Duration.Zero)


        //todo: create residential profile.. then create profile
        //todo: create profile
        //todo: then create other residential
     val result: Profile =    Await.result(residentDAO.create(authResponse,request) ,Duration.Zero)
        //if this works. create item
        //todo: create resident profile.

      Right(Future.successful( result.map(populateResponse)))


      }
    }


  }

  def populateResponse(  entity:Profile): ResidentProfileResponse =
   new ResidentProfileResponse(
      entity.surname
      ,entity.other_names
      ,entity.profile_type
      ,entity.gender
      ,null
      ,null
      ,0l
      ,null

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
