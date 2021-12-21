package services
import controllers.requests.{ProfileRequest, ResidentProfileRequest}
import controllers.responses.{UserResponse, ResidentProfileResponse, StationResponse}
import db.tables.{Profile, Resident, Station}

import scala.concurrent.Future

trait TResidentialService {

  def saveResidentProfile(authResponse: UserResponse, profile: Profile, stationresponse: StationResponse): Future[Resident]

  def create(authResponse: UserResponse, request: ProfileRequest): Either[Throwable, Future[ResidentProfileResponse]]

}
