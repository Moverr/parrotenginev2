package services

import controllers.requests.OrganisationRequest
import controllers.responses.{AuthResponse, OrganisationResponse}
import daos.{OrganisationDAO, UserDao}
import javax.inject.{Inject, Singleton}
import utitlities.JwtUtility

import scala.concurrent.Future

@Singleton
class OrganizationService  @Inject()(organisationDAO: OrganisationDAO)  {

  //todo: create organisation
  def create(authorization:AuthResponse,request:OrganisationRequest): Future[OrganisationResponse] ={
    ???
  }
  //todo: list organinsations
  def list(authorization:AuthResponse,limit:Int, offset:Int): Future[Seq[OrganisationResponse]]  = {
    ???
  }
  //todo: Get Organization
  def get(authorization:AuthResponse,id:Int,): Future[Option[OrganisationResponse]] ={
    ???
  }
}
