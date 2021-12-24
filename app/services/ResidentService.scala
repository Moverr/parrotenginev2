package services

import controllers.requests.{ProfileRequest, ResidentProfileRequest}
import controllers.responses.{UserResponse, GeneralProfileResponse, ResidentProfileResponse, StationResponse}
import daos._
import db.tables.{Profile, Resident}
import helpers.Utilities.getCurrentTimeStamp
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class ResidentService @Inject()(
                                    residentDAO: ResidentProfileDAO
                                    , profileDAO: ProfileDAO
                                    , stationService: StationService

                                  ) extends TResidentialService {


  override def saveResidentProfile(authResponse: UserResponse, profile: Profile, stationResponse: StationResponse): Future[Resident] = {
    val resident: Resident = new Resident(0L, profile.id, authResponse.user_id, getCurrentTimeStamp(), authResponse.user_id, getCurrentTimeStamp(), stationResponse.id, getCurrentTimeStamp())
    residentDAO.create(resident)

  }

  override def create(authResponse: UserResponse, request: ProfileRequest): Either[java.lang.Throwable, Future[ResidentProfileResponse]] = {

    if (authResponse == null) Left(new Exception("Invalid Authentication"))
    else {

      request match {

        case ResidentProfileRequest(surname, othername, profiletype, gender, stationid, joinDate) => {


          val resp: Either[java.lang.Throwable, Future[Option[StationResponse]]] = stationService.get(authResponse, stationid)
          resp match {
            case Left(exc) => Left(new Exception(exc.getMessage))
            case Right(station) => {


              val record = for {
                future1 <- profileDAO.create(surname, othername, gender, authResponse.user_id, None, "RESIDENT").recoverWith {
                  case exception: Throwable => Future.failed(new Exception(exception.getMessage))
                }
                _station_record <- for (record <- station) yield (record.get)

                future2 <- saveResidentProfile(authResponse, future1, _station_record).recoverWith {
                  case exception: Throwable => Future.failed(new Exception(exception.getMessage))
                }

              } yield (future1, future2)

              Right(record.map(item => populateResidentialProfile(item._1, item._2)))

            }

            case _ => Left(new Exception("Invalid request"))
          }
        }

      }

    }
  }

  //todo: list the items

  def list(authResponse: UserResponse, offset: Int, limit: Int, station: Option[Long], query: Option[String]): Either[java.lang.Throwable, Future[Seq[ResidentProfileResponse]]] = {
    if (authResponse == null) return Left(new Exception("Invalid Authentication"))
    val result: Future[Seq[(Resident, Profile)]] = residentDAO.list(Some(authResponse.user_id), station, offset, limit, query)


    Right {
      result.map {
        record => record.map(x => populateResidentialProfile(x._2, x._1))
      }
    }

  }


  //todo: dealingn with muultiple owners. owner_id...
  //todo: get item details
  def get(authResponse: UserResponse, id: Long): Either[java.lang.Throwable, Future[Option[ResidentProfileResponse]]] = {
    if (authResponse == null) return Left(new Exception("Invalid Authentication"))
    val result: Future[Option[(Resident, Profile)]] = residentDAO.get(id)

    Right {
      result.map {
        record => record.map(x => populateResidentialProfile(x._2, x._1))
      }
    }
  }


  //todo: get items by station

  def populateResidentialProfile(profile: Profile, resident: Resident): ResidentProfileResponse =
    ResidentProfileResponse(
      resident.id
      , populateBasicProfile(profile)
      , resident.station_id.toInt
      , resident.join_date
      , resident.created_on.getTime
      , "NA"

    )

  def populateBasicProfile(profile: Profile): GeneralProfileResponse = {
    GeneralProfileResponse(
      profile.id
      , profile.surname
      , profile.other_names
      , profile.profile_type
      , profile.gender

    )
  }


  //todo: invite them to create user credentials


  //todo: can achive or activate them depending on the permissions

  //todo: create

  //todo: update

  //todo: archive

  //todo:list
}
