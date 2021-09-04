package services

import controllers.requests.{OrganisationRequest, StationRequest}
import controllers.responses.{AuthResponse, OrganisationResponse, StationResponse}
import daos.{OrganisationDAO, StationDAO}
import db.tables.{Organization, Station}
import javax.inject.Inject

import scala.concurrent.{Await, Future}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration


class StationService   @Inject()(
                                  stationDao: StationDAO
                                ,  organisationDAO: OrganisationDAO

                                )  {



  //todo: Create

    def create(authResponse: AuthResponse,request:StationRequest): Either[java.lang.Throwable,Future[StationResponse]]= {
      if (authResponse == null) return Left(new Exception("Invalid Authentication"))

      //todo: Get Account Details  ::
     val response:Option[Organization] = Await.result(organisationDAO.get(request.organization_id.toLong),Duration.Inf)

      if(response.exists(_ =>false))   return Left(new Exception("Invalid Authentication"))

     val stationResponse:Future[Station] =   stationDao.create(request.organization_id, request)
      Right(stationResponse.flatMap(x=>Future.successful(populateResponse(x))))


    }



  //todo: list Stations
  def list(authResponse: AuthResponse,organisation_id:Int,offset:Int, limit:Int): Either[java.lang.Throwable,Future[Seq[StationResponse]] ]= {
    if(authResponse == null ) return  Left(new Exception("Invalid Authentication"))

    Right(
      stationDao.list(organisation_id.toLong,offset,limit) .map(y=>y.map(record=>populateResponse(record)))
    )
  }

  //todo: get item by id
  def get(authResponse: AuthResponse, station_id:Int):Either[java.lang.Throwable,Future[Option[StationResponse]]]={
    if(authResponse == null ) return  Left(new Exception("Invalid Authentication"))
    Right( stationDao.get(station_id).map(record=>populateResponse(record)))
  }

  //todo: Archive



  def populateResponse(station:Future[Station]):Future[StationResponse]= station.flatMap{
    record=> Future.successful(populateResponse(record))
    }

  def populateResponse(station:Station) : StationResponse = StationResponse(station.id,station.name,station.code,None )


  def populateResponse(station:Option[Station]) : Option[StationResponse] =
    station match {
      case Some(value) => Some(populateResponse(value))
      case None =>None
    }

  //StationResponse(station.id,station.name,station.code,None )



}
