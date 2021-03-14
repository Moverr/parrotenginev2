package services

import controllers.requests.OrganisationRequest
import controllers.responses.{AuthResponse, OrganisationResponse}
import daos.OrganisationDAO
import db.tables.Organization
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


@Singleton
class OrganizationService  @Inject()(organisationDAO: OrganisationDAO)  extends IOrganisationServiceTrait {

  //todo: create organisation
  override def create(authResponse: AuthResponse,request:OrganisationRequest): Future[OrganisationResponse] ={
    if(authResponse == null ) throw new Exception("Invalid Authentication")



    organisationDAO.createOrganisation(request.name,request.details,authResponse.user_id)
      .map(x=>populateResponse(x))
  }

  //todo: list organinsations
  override def list(authResponse: AuthResponse,limit:Int, offset:Int): Future[Seq[OrganisationResponse]]  = {
    if(authResponse == null ) throw new Exception("Invalid Authentication")
    organisationDAO.getOrganisations(authResponse.user_id,limit,offset)
      .map(y=>y.map(p=>populateResponse(p)))
  }

  //todo: Get Organization
  override def get(authResponse: AuthResponse,id:Int): Future[Option[OrganisationResponse]] ={
    if(authResponse == null ) throw new Exception("Invalid Authentication")
    organisationDAO.getOrganisation(authResponse.user_id,id)
      .flatMap{
        case Some(value) => Future.successful(Some(populateResponse(value)))
        case None =>  Future.successful(None)
      }
  }

  /*
      Populate Response
   */
  override def populateResponse(organisation:Organization): OrganisationResponse =
    OrganisationResponse(organisation.id,organisation.name,organisation.details
      ,organisation.date_created.getTime,organisation.author_id,
      organisation.date_updated.getTime,organisation.updated_by)
}
