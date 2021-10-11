package services
import controllers.requests.{ProfileRequest, ResidentProfileRequest}
import controllers.responses.{AuthResponse, ResidentProfileResponse, StationResponse}
import db.tables.{Profile, Resident, Station}

import scala.concurrent.Future

trait TResidentialService {

  def saveResidentProfile(authResponse: AuthResponse,profile: Profile,stationresponse: StationResponse): Future[Resident]

  def create(authResponse: AuthResponse, request: ProfileRequest): Either[Throwable, Future[ResidentProfileResponse]]

}
