package services

import controllers.requests.{OrganisationRequest, StationRequest}
import controllers.responses.{AuthResponse, OrganisationResponse, StationResponse}
import daos.{OrganisationDAO, StationDAO}

import db.tables.Station
import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import javax.inject.Inject

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Future}


class StationService   @Inject()(
                                  stationDao: StationDAO
                                ,  organizationService: OrganizationService

                                )  {

  //todo: Create

    def create(authResponse: AuthResponse,organisation_id:Int,request:StationRequest): Either[Nil,Future[StationResponse]]={
  //  if(authResponse == null )   return  Left(new Exception("Invalid Authentication"))

    //todo: Get Account Details  ::
    //  val p: Either[java.lang.Throwable,Future[Option[OrganisationResponse]]] = organizationService.get(authResponse, organisation_id)

      val response = stationDao.create(organisation_id,request)


      response.flatMap{
        res=> return Right(Future.successful(populateResponse(res)))
      }

Left(Nil)


  }

  //todo: list

  //todo: Archive


  def populateResponse(station: Station):StationResponse={

    val stationRespnse = new StationResponse(station.id,station.name,station.code,null)
    stationRespnse
  }
}
