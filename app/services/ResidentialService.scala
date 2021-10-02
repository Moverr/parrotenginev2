package services

import java.sql.Timestamp

import controllers.requests.ResidentProfileRequest
import controllers.responses.{AuthResponse, GeneralProfileResponse, ProfileResponse, ResidentProfileResponse, StationResponse}
import daos._
import db.tables.{Profile, Resident, Station}
import helpers.Utilities.getCurrentTimeStamp
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class ResidentialService @Inject()(
                                    residentDAO: ResidentProfileDAO
                                    , profileDAO: ProfileDAO
                                    , stationService: StationService

                                  ) extends TResidentialService {


  override def saveResidentProfile(authResponse: AuthResponse, profile: Profile,stationResponse: StationResponse): Future[Resident] = {
    val resident: Resident = new Resident(0L, profile.id, authResponse.user_id, getCurrentTimeStamp(), authResponse.user_id, getCurrentTimeStamp(), stationResponse.id, getCurrentTimeStamp())
    residentDAO.create(resident)

  }

  override def create(authResponse: AuthResponse, request: ResidentProfileRequest): Either[java.lang.Throwable, Future[ResidentProfileResponse]] = {

    if (authResponse == null) Left(new Exception("Invalid Authentication"))
    else {

      val resp: Either[java.lang.Throwable, Future[Option[StationResponse]]] = stationService.get(authResponse, request.stationid)
      resp match {
        case Left(exc) => Left(new Exception(exc.getMessage))
        case Right(station) => {




          val record = for {
            future1 <- profileDAO.create(request.surname,request.othername,request.gender,authResponse.user_id,None,"RESIDENT").recoverWith {
              case exception: Throwable => Future.failed(new Exception(exception.getMessage))
            }
            _station_record <- for (record <- station) yield (record.get)

            future2 <- saveResidentProfile(authResponse,future1,_station_record).recoverWith {
              case exception: Throwable => Future.failed(new Exception(exception.getMessage))
            }

          } yield (future1, future2)

          Right(record.map(item => populateResponse(item._1, item._2)))

        }
      }

    }

  }

  //todo: list the items

    def list(authResponse: AuthResponse,offset:Int, limit:Int,station:Option[Long],query:Option[String]): Either[java.lang.Throwable,Future[Seq[ResidentProfileResponse]] ]= {
    if(authResponse == null ) return  Left(new Exception("Invalid Authentication"))
    val result:Future[Seq[(Resident,Profile)]]  =  residentDAO.list(Some(authResponse.user_id),station,offset,limit,query)


    Right{
      result.map{
        record=>  record.map(x=>populateResponse(x._2,x._1))
      }
    }

  }


  //todo: dealingn with muultiple owners. owner_id...
  //todo: get item details
  def get(authResponse: AuthResponse,id:Long): Either[java.lang.Throwable,Future[Option[ResidentProfileResponse]] ]= {
    if(authResponse == null ) return  Left(new Exception("Invalid Authentication"))
    val result:Future[Option[(Resident,Profile)]]  =  residentDAO.get(id)

    Right{
      result.map{
        record=>  record.map(x=>populateResponse(x._2,x._1))
      }
    }
  }


  //todo: get items by station

  override def populateResponse(profile: Profile, resident: Resident): ResidentProfileResponse =
    ResidentProfileResponse(
      resident.id
        ,populateResponse(profile)
      , resident.station_id.toInt
      , resident.join_date
      , resident.created_on.getTime
      , "NA"

    )

  def populateResponse(profile:Profile): GeneralProfileResponse ={
    GeneralProfileResponse(
      profile.id
      ,profile.surname
      , profile.other_names
      , profile.profile_type
      , profile.gender

    )
  }

  override def populateResponse(entity: Resident): ResidentProfileResponse = ???


  //todo: invite them to create user credentials


  //todo: can achive or activate them depending on the permissions

  //todo: create

  //todo: update

  //todo: archive

  //todo:list
}
