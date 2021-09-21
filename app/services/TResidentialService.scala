package services
import controllers.requests.ResidentProfileRequest
import controllers.responses.{AuthResponse, ResidentProfileResponse}
import db.tables.{Profile, Resident}

import scala.concurrent.Future

trait TResidentialService {

  def saveResidentProfile(profile: Profile): Future[Resident]

  def create(authResponse: AuthResponse, request: ResidentProfileRequest): Either[Throwable, Future[ResidentProfileResponse]]

  def populateResponse(entity: Profile, resident: Resident): ResidentProfileResponse

  def populateResponse(entity: Resident): ResidentProfileResponse
}
