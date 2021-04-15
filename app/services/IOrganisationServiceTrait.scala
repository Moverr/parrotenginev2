package services
import controllers.requests.OrganisationRequest
import controllers.responses.{AuthResponse, OrganisationResponse}
import db.tables.Organization

import scala.concurrent.Future

trait IOrganisationServiceTrait {

  //todo: create organisation
  def create(authResponse: AuthResponse, request: OrganisationRequest) : Either[java.lang.Throwable,Future[OrganisationResponse]]

  //todo: list organinsations
  def list(authResponse: AuthResponse, limit: Int, offset: Int): Either[java.lang.Throwable,Future[Seq[OrganisationResponse]] ]

  //todo: Get Organization
  def get(authResponse: AuthResponse, id: Int): Future[Option[OrganisationResponse]]

  /*
      Populate Response
   */
  def populateResponse(organisation: Organization): OrganisationResponse
}
