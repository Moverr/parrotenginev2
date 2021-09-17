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


	def saveResidentProfile(profile: Profile): Future[Resident] ={
		val resident:Resident = new Resident(0L,profile.id,1,getCurrentTimeStamp(),1,getCurrentTimeStamp(),1,getCurrentTimeStamp())
		residentDAO.create(resident)

	}
	def create(authResponse: AuthResponse, request:ResidentProfileRequest): Either[java.lang.Throwable,Future[ResidentProfileResponse]]= {

		if (authResponse == null)  Left(new Exception("Invalid Authentication"))
		else {

			val resp: Either[java.lang.Throwable, Future[Option[StationResponse]]] = stationService.get(authResponse, request.stationid)
			resp match {
				case Left(exc) =>  Left(new Exception(exc.getMessage))
				case Right(station) =>{

          val stationId:Future[Long] =  for{
               x <- station.map(y=>y.get)
          } yield (x.id)


					val profile = Profile(0L, None, request.surname, request.othername, request.gender, "RESIDENT", authResponse.user_id, getCurrentTimeStamp, authResponse.user_id, getCurrentTimeStamp)

					val record = for {
						future1 <- profileDAO.create(profile).recoverWith {
							case t: Throwable => Future.failed(new Exception("Not Able to create client profile "))
						}
						future2 <- saveResidentProfile(future1).recoverWith {
							case t: Throwable => Future.failed(new Exception(t.getMessage))
						}

					} yield (future1, future2)

					Right(record.map(item => populateResponse(item._1, item._2)))

				}
			}


		}

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
