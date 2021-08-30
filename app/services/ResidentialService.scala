package services

import controllers.requests.{ResidentProfileRequest, StationRequest}
import controllers.responses.{AuthResponse, StationResponse}
import daos._
import db.tables.{Organization, Station}
import javax.inject.{Inject, Singleton}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ResidentialService  @Inject()(
                                     residentDAO: ResidentProfileDAO
                                   ,stationService: StationService

                                   ) {

  def create(authResponse: AuthResponse,request:ResidentProfileRequest): Either[java.lang.Throwable,Future[StationResponse]]= {

    if (authResponse == null) return Left(new Exception("Invalid Authentication"))
    val resp:Either[java.lang.Throwable,Future[Option[StationResponse]]] = stationService.get(authResponse,request.stationid)

    resp match {
      case Left(value) => Left(value)
      case Right(value) => {
        value.map(x=> x match {
          case Some(value) =>  Left(new Exception("Invalid Authentication"))
          case None =>  {
            //todo: save the day
          //  residentDAO.crea
          }
        })
      }
    }
    /*
    stationService.get(request.stationid)
      .map{
        x=>
          x match {
            case Some(value) => ???
            case None => Left(new Exception("Station does not exist "))
          }
      }

    */

    //todo: Get Account Details  ::
    val response:Option[Organization] = Await.result(organisationDAO.getOrganisation(request.organization_id.toLong),Duration.Inf)

    if(resp.exists(_ =>false))   return Left(new Exception("Invalid Authentication"))

    val stationResponse:Future[Station] =   stationDao.create(request.organization_id, request)
    Right(stationResponse.flatMap(x=>Future.successful(populateResponse(x))))
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
