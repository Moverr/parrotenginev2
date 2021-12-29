package services

import controllers.requests.StationRequest
import controllers.responses.{StationResponse, UserResponse}
import daos.{OrganisationDAO, StationDAO}
import db.tables.{Organization, Station}
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration


@Singleton
class StationService @Inject()(
                                stationDao: StationDAO
                                , organisationDAO: OrganisationDAO
                              ,organizationService: OrganizationService

                              ) {


  //todo: Create

  def create(authResponse: UserResponse, request: StationRequest): Either[java.lang.Throwable, Future[StationResponse]] = {
    if (authResponse == null) return Left(new Exception("Invalid Authentication"))

    //todo: Get Account Details  ::
    val response: Option[Organization] = Await.result(organisationDAO.get(request.organization_id.toLong), Duration.Inf)

    if (response.exists(_ => false)) return Left(new Exception("Organization Exists"))

    val stationResponse: Future[Station] = stationDao.create(request.organization_id, request)
    Right(stationResponse.flatMap(x => Future.successful(populateResponse(x,None))))


  }


  //todo: list Stations
  def list(authResponse: UserResponse, organisation_id: Int, offset: Int, limit: Int): Either[java.lang.Throwable, Future[Seq[StationResponse]]] = {
    if (authResponse == null) return Left(new Exception("Invalid Authentication"))

    Right(
      stationDao.list(organisation_id.toLong, offset, limit).map(y => y.map(record => {
        populateResponse(record._1, record._2)
      }))
    )
  }

  //todo: get item by id
  def get(authResponse: UserResponse, station_id: Int): Either[java.lang.Throwable, Future[Option[StationResponse]]] = {
    if (authResponse == null) return Left(new Exception("Invalid Authentication"))
    val record = stationDao.get(station_id.toLong)

    Right {
      record.map {
        x =>
          x.map(y => {
            populateResponse(y._1, y._2)
          })
      }

    }

  }

  //todo: Archive

  def populateResponse(station: Station, organization: Option[Organization]): StationResponse = {
    StationResponse(station.id, station.name, station.code, organization match {
      case Some(value) =>Some(organizationService.populateResponse(value,None))
      case None =>None
    })
  }

  def populateResponse(station: Future[Station]): Future[StationResponse] = station.flatMap {
    record => Future.successful(populateResponse(record, None))
  }

  def populateResponse(station: Option[Station],organization: Option[Organization]): Option[StationResponse] =
    station match {
      case Some(value) => Some(populateResponse(value, organization))
      case None => None
    }


}
