package services

import java.sql.Timestamp

import controllers.requests.ResidentProfileRequest
import controllers.responses.{AuthResponse, OrganisationResponse, ResidentProfileResponse, StationResponse}
import daos._
import db.tables.{Organization, Profile, Resident}
import helpers.Utilities.getCurrentTimeStamp
import javax.inject.{Inject, Singleton}
import slick.util.SQLBuilder.Result

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


@Singleton
class ResidentialService @Inject()(
                                    residentDAO: ResidentProfileDAO
                                    , profileDAO: ProfileDAO
                                    , stationService: StationService

                                  ) extends TResidentialService {


  override def saveResidentProfile(profile: Profile): Future[Resident] = {
    val resident: Resident = new Resident(0L, profile.id, 1, getCurrentTimeStamp(), 1, getCurrentTimeStamp(), 1, getCurrentTimeStamp())
    residentDAO.create(resident)

  }

  override def create(authResponse: AuthResponse, request: ResidentProfileRequest): Either[java.lang.Throwable, Future[ResidentProfileResponse]] = {

    if (authResponse == null) Left(new Exception("Invalid Authentication"))
    else {

      val resp: Either[java.lang.Throwable, Future[Option[StationResponse]]] = stationService.get(authResponse, request.stationid)
      resp match {
        case Left(exc) => Left(new Exception(exc.getMessage))
        case Right(station) => {

          val stationId: Future[Long] = for {
            record <- station.map(y => y.get)
          } yield (record.id)


//          val profile = Profile(0L, None, request.surname, request.othername, request.gender, "RESIDENT", authResponse.user_id, getCurrentTimeStamp(), authResponse.user_id, getCurrentTimeStamp())

          val respo:Profile  = Await.result(profileDAO.create(request.surname,request.othername,request.gender,authResponse.user_id,None,"RESIDENT"),Duration.Inf)


          val record = for {
            future1 <- profileDAO.create(request.surname,request.othername,request.gender,authResponse.user_id,None,"RESIDENT").recoverWith {
              case exception: Throwable => Future.failed(new Exception(exception.getMessage))
            }

            //todoo: let me see this.
            future2 <- saveResidentProfile(future1).recoverWith {
              case exception: Throwable => Future.failed(new Exception(exception.getMessage))
            }

          } yield (future1, future2)

          Right(record.map(item => populateResponse(item._1, item._2)))

        }
      }

    }

  }

  //todo: list the items

    def list(authResponse: AuthResponse,offset:Int, limit:Int,station:Option[Int]): Either[java.lang.Throwable,Future[Seq[ResidentProfileResponse]] ]= {
    if(authResponse == null ) return  Left(new Exception("Invalid Authentication"))
    val result:Future[Seq[(Resident,Profile)]]  =  residentDAO.list(Some(authResponse.user_id),None,offset,limit)

    Right{
      result.map{
        y=>
            y.map(x=>populateResponse(x._2,x._1))

      }
    }


  }

  //todo: get item details

  //todo: get items by station

  override def populateResponse(entity: Profile, resident: Resident): ResidentProfileResponse =
    ResidentProfileResponse(
      entity.surname
      , entity.other_names
      , entity.profile_type
      , entity.gender
      , resident.station_id.toInt
      , resident.join_date
      , resident.created_on.getTime
      , "NA"

    )


  override def populateResponse(entity: Resident): ResidentProfileResponse =
    ResidentProfileResponse(
      "name"
      , "'othernames"
      , "type"
      , "gender"
      , 1
      , new Timestamp(0L)
      , 0L
      , ""
    )


  //todo: invite them to create user credentials


  //todo: can achive or activate them depending on the permissions

  //todo: create

  //todo: update

  //todo: archive

  //todo:list
}
