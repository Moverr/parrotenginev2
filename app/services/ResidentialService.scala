package services

import java.sql.Timestamp

import controllers.requests.{ResidentProfileRequest, StationRequest}
import controllers.responses.{AuthResponse, ResidentProfileResponse, StationResponse}
import daos._
import db.tables.{Organization, Profile, Resident, Station}
import helpers.Utilities.getCurrentTimeStamp
import javax.inject.{Inject, Singleton}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


@Singleton
class ResidentialService  @Inject()(
                                     residentDAO: ResidentProfileDAO
                                   ,   profileDAO: ProfileDAO
                                     ,stationService: StationService

                                   ) {


  def create(authResponse: AuthResponse, request:ResidentProfileRequest): Either[java.lang.Throwable,Future[ResidentProfileResponse]]= {

    if (authResponse == null) return Left(new Exception("Invalid Authentication"))
    val resp:Either[java.lang.Throwable,Future[Option[StationResponse]]] =  stationService.get(authResponse,request.stationid)

    val profile = Profile(0L,None,request.surname,request.othername,request.gender,"RESIDENT",authResponse.user_id,getCurrentTimeStamp,authResponse.user_id,  getCurrentTimeStamp )
   Right( profileDAO.create(profile)
        .map(
          populateResponse
        )
   )
  /*  resp match {
      case Left(value) =>     Left(new Exception("Station does not exists"))
      case Right(value) => {
        value.map{
          case Some(value) => println("esee")
          case None =>  println("aloelpse")
        }

        //val  res:Option[StationResponse] = Await.result(value,Duration.Zero)








      }
    }
    */

   // Right(Future.successful("testing"))
  }

  //todo: list the items

  //todo: get item details

  //todo: get items by station

  def populateResponse(  entity:Profile,resident: Resident): ResidentProfileResponse =
    ResidentProfileResponse(
      entity.surname
      ,entity.other_names
      ,entity.profile_type
      ,entity.gender
      ,resident.station_id.toInt
      ,resident.join_date
      ,resident.created_on.getTime
      ,"NA"

    )

  def populateResponse(  entity:Profile): ResidentProfileResponse =
    ResidentProfileResponse(
      entity.surname
      ,entity.other_names
      ,entity.profile_type
      ,entity.gender
      ,1
        ,new Timestamp(0l)
      ,0l
,""
    )



  //todo: invite them to create user credentials



  //todo: can achive or activate them depending on the permissions

  //todo: create

  //todo: update

  //todo: archive

  //todo:list
}
