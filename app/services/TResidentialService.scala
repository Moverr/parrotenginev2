package services
import controllers.requests.ResidentProfileRequest
import controllers.responses.{AuthResponse, ResidentProfileResponse, StationResponse}
import db.tables.{Profile, Resident, Station}

import scala.concurrent.Future

trait TResidentialService {

  def saveResidentProfile(authResponse: AuthResponse,profile: Profile,stationresponse: StationResponse): Future[Resident]

  def create(authResponse: AuthResponse, request: ResidentProfileRequest): Either[Throwable, Future[ResidentProfileResponse]]

  def populateResponse(entity: Profile, resident: Resident): ResidentProfileResponse

  def populateResponse(entity: Resident): ResidentProfileResponse
}
