package services

import controllers.requests.{OrganisationRequest, StationRequest}
import controllers.responses.{AuthResponse, OrganisationResponse, StationResponse}
import daos.{OrganisationDAO, StationDAO}
import db.tables.Station
import javax.inject.Inject

import scala.concurrent.{Await, Future}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration


class StationService   @Inject()(
                                  stationDao: StationDAO
                                ,  organizationService: OrganizationService

                                )  {

  //todo: Create

    def create(authResponse: AuthResponse,organisation_id:Int,request:StationRequest): Either[java.lang.Throwable,Future[StationResponse]]= {
      if (authResponse == null) return Left(new Exception("Invalid Authentication"))

      //todo: Get Account Details  ::
     val response =  organizationService.get(authResponse, organisation_id)



/*
      match {

        case Right(value) =>
          val result = Await.result(value, Duration.Inf)
          result match {
            case Some(value) => {

              stationDao.create(organisation_id, request).
                flatMap {
                  res =>   Right(Future.successful(populateResponse(res)))
                }
            }
            case None => Left(new Exception("Invalid"))
          }
      }

*/
      /*
          value.flatMap {
          x =>
            x match {
              case Some(value) => {
                stationDao.create(organisation_id, request).
                  flatMap {
                    res => return Right(Future.successful(populateResponse(res)))
                  }

              }
              case None => return Left(new Exception("Invalid"))
            }
        } match {
          case right: Right =>right
        }
       */
    }





  //todo: list

  //todo: Archive


  def populateResponse(station: Station):StationResponse={

    val stationRespnse = new StationResponse(station.id,station.name,station.code,null)
    stationRespnse
  }
}
