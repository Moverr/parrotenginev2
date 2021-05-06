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

    def create(authResponse: AuthResponse,organisation_id:Int,request:StationRequest): Either[java.lang.Throwable,Future[StationResponse]]= {
      if (authResponse == null) return Left(new Exception("Invalid Authentication"))

      //todo: Get Account Details  ::
     val response:Option[Organization] = Await.result(organisationDAO.getOrganisation(organisation_id.toLong),Duration.Inf)

      if(response.exists(p=>false))   return Left(new Exception("Invalid Authentication"))


     val statioonResponse:Future[Station] =   stationDao.create(organisation_id, request)
      Right(populateResponse(statioonResponse))


    }





  //todo: list

  //todo: Archive



  def populateResponse(station:Future[Station]):Future[StationResponse]=    station.flatMap{
      x=> Future.successful(StationResponse(x.id,x.name,x.code,null))
    }


}
