package services

import controllers.requests.OrganisationRequest
import controllers.responses.{AuthResponse, OrganisationResponse}
import daos.{OrganisationDAO, UserDao}
import javax.inject.{Inject, Singleton}
import utitlities.JwtUtility

import scala.concurrent.Future

@Singleton
class OrganizationService  @Inject()(organisationDAO: OrganisationDAO,authService: AuthService)  {

  //todo: create organisation
  def create(authResponse: AuthResponse,request:OrganisationRequest): Future[OrganisationResponse] ={

    ???
  }
  //todo: list organinsations
  def list(authResponse: AuthResponse,limit:Int, offset:Int): Future[Seq[OrganisationResponse]]  = {

    ???
  }
  //todo: Get Organization
  def get(authResponse: AuthResponse,id:Int,): Future[Option[OrganisationResponse]] ={

    ???
  }
}
