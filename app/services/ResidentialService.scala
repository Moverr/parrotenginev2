package services

import controllers.requests.{ResidentProfileRequest, StationRequest}
import controllers.responses.{AuthResponse, StationResponse}
import daos._
import db.tables.{Organization, Profile, Station}
import javax.inject.{Inject, Singleton}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

@Singleton
class ResidentialService  @Inject()(
                                     residentDAO: ResidentProfileDAO
                                   ,stationService: StationService

                                   ) {

//  def inviteUser(): PartialFunction[Try[Profile],] = ???

  def create(authResponse: AuthResponse, request:ResidentProfileRequest): Either[java.lang.Throwable,Future[StationResponse]]= {

    if (authResponse == null) return Left(new Exception("Invalid Authentication"))
    val resp:Either[java.lang.Throwable,Future[Option[StationResponse]]] =  stationService.get(authResponse,request.stationid)
//    val resp   = Await.result( stationService.get(authResponse,request.stationid))


    resp match {
      case Left(value) =>     Left(new Exception("Station does not exists"))
      case Right(value) => {
        var res:Option[StationResponse] = Await.result(value,Duration.Zero)


        /*
        value.map(x=> x match {
          case Some(value) =>  {
            //todo: create profile
            val station:Future[Profile] = residentDAO.create(authResponse,request)
            Right(Future.successful(station.map(populateResponse)))
          }
          case None =>   Left(new Exception("Invalid Authentication"))
        })
        */
       Right(Future.successful(populateResponse()))
      }
    }


  }

  def populateResponse(): StationResponse ={
    ???
  }


  // todo : create  resident profile

  //todo: invite them to create user credentials

  //todo: let them create there own credentials

  //todo: can achive or activate them depending on the permissions

  //todo: create

  //todo: update

  //todo: archive

  //todo:list
}
