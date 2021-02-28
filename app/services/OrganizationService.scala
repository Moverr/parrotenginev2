package services

import controllers.requests.OrganisationRequest
import controllers.responses.AuthResponse
import daos.{OrganisationDAO, UserDao}
import javax.inject.{Inject, Singleton}
import utitlities.JwtUtility

@Singleton
class OrganizationService  @Inject()(organisationDAO: OrganisationDAO)  {
  OrganisationResponse
  //todo: create organisation
  def create(authorization:AuthResponse,request:OrganisationRequest): Unit ={
    ???
  }
  //todo: list organinsations
  def list(authorization:AuthResponse,limit:Int, offset:Int): Unit = {
    ???
  }
  //todo: Get Organization
  def get(authorization:AuthResponse,id:Int,): Unit ={
    ???
  }
}
