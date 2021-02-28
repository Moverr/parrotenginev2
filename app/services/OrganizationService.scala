package services

import controllers.requests.OrganisationRequest
import controllers.responses.AuthResponse
import daos.{OrganisationDAO, UserDao}
import javax.inject.{Inject, Singleton}
import utitlities.JwtUtility

@Singleton
class OrganizationService  @Inject()(organisationDAO: OrganisationDAO)  {

  //todo: create organisation
  def create(request:OrganisationRequest,authorization:AuthResponse): Unit ={
    ???
  }
  //todo: list organinsations

  //todo: Get Organization
}
