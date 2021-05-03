package services

import controllers.requests.{OrganisationRequest, StationRequest}
import controllers.responses.{AuthResponse, OrganisationResponse, StationResponse}
import daos.{OrganisationDAO, StationDAO}
import db.tables.Station
import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class StationService   @Inject()(
                                  stationDao: StationDAO
                                ,  organizationService: OrganizationService

                                )  {

  //todo: Create
    def create(authResponse: AuthResponse,organisation_id:Int,request:StationRequest): Either[java.lang.Throwable,Future[OrganisationResponse]]={
    if(authResponse == null )   return  Left(new Exception("Invalid Authentication"))

    //todo: Get Account Details  ::
      organizationService.get(authResponse, organisation_id) match {
        case Left(value) =>  Left(new Exception(value.getMessage))
        case Right(resp) => resp.flatMap{
          y=>
            y match {
              case Some(value) => {

                  stationDao.create(value.id,request)
                  .map(x=>   return  Left(new Exception("Organization does not exist")))
                    //return  Right(populateResponse(x) ))


              }
              case None =>  return  Left(new Exception("Organization does not exist"))
            }
        }
      }



  }

  //todo: list

  //todo: Archive

  def populateResponse(station: Station):StationResponse={

    val stationRespnse = new StationResponse(station.id,station.name,station.code,null)
    stationRespnse
  }
}
