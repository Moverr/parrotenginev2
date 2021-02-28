package services

import controllers.requests.OrganisationRequest
import controllers.responses.{AuthResponse, OrganisationResponse}
import daos.{OrganisationDAO, UserDao}
import db.tables.Organization
import javax.inject.{Inject, Singleton}
import utitlities.JwtUtility
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class OrganizationService  @Inject()(organisationDAO: OrganisationDAO)  {

  //todo: create organisation
  def create(authResponse: AuthResponse,request:OrganisationRequest): Future[OrganisationResponse] ={
    if(authResponse == null ) throw new Exception("Invalid Authentication")

     organisationDAO.createOrganisation(request.name,request.details,authResponse.user_id)
       .map(x=>populateResponse(x))
  }
  //todo: list organinsations
  def list(authResponse: AuthResponse,limit:Int, offset:Int): Future[Seq[OrganisationResponse]]  = {

    ???
  }
  //todo: Get Organization
  def get(authResponse: AuthResponse,id:Int,): Future[Option[OrganisationResponse]] ={

    ???
  }
  def populateResponse(organisation:Organization): OrganisationResponse ={
  val res = OrganisationResponse(organisation.id,organisation.name,organisation.details,organisation.date_created.to

  }
}
