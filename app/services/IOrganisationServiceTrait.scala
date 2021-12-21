package services
import controllers.requests.OrganisationRequest
import controllers.responses.{UserResponse, OrganisationResponse}
import db.tables.Organization

import scala.concurrent.Future

trait IOrganisationServiceTrait {

  //todo: create organisation
  def create(authResponse: UserResponse, request: OrganisationRequest) : Either[java.lang.Throwable,Future[OrganisationResponse]]

  //todo: list organinsations
  def list(authResponse: UserResponse, limit: Int, offset: Int): Either[java.lang.Throwable,Future[Seq[OrganisationResponse]] ]

  //todo: Get Organization
  def get(authResponse: UserResponse, id: Int):  Either[java.lang.Throwable,Future[Option[OrganisationResponse]]]

  /*
      Populate Response
   */
  def populateResponse(organisation: Organization): OrganisationResponse
}
