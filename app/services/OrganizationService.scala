package services

import controllers.requests.OrganisationRequest
import controllers.responses.{AuthResponse, OrganisationResponse}
import daos.OrganisationDAO
import db.tables.Organization
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Future}


@Singleton
class OrganizationService  @Inject()(organisationDAO: OrganisationDAO)  extends IOrganisationServiceTrait
{

  //todo: create organisation
  override def create(authResponse: AuthResponse,request:OrganisationRequest): Either[java.lang.Throwable,Future[OrganisationResponse]]={
    if(authResponse == null )   return  Left(new Exception("Invalid Authentication"))

      //todo: Get Account Details  ::

       Right(organisationDAO.create(request.name,request.details,authResponse.user_id)
      .map(x=>populateResponse(x)))
  }

  //todo: list organinsations
  override def list(authResponse: AuthResponse,offset:Int, limit:Int): Either[java.lang.Throwable,Future[Seq[OrganisationResponse]] ]= {
    if(authResponse == null ) return  Left(new Exception("Invalid Authentication"))
    val result : Future[Seq[Organization]] =  organisationDAO.list(authResponse.user_id,offset,limit)


    Right{
     result.map{
       y=>y.map(p=>populateResponse(p))
     }
    }


  }

  //todo: Get Organization
  override def get(authResponse: AuthResponse,id:Int):  Either[java.lang.Throwable,Future[Option[OrganisationResponse]]] ={
    if(authResponse == null )  return  Left(new Exception("Invalid Authentication"))

    Right(
    organisationDAO.get(authResponse.user_id,id)
      .flatMap{
        case Some(value) => Future.successful(Some(populateResponse(value)))
        case None =>  Future.successful(None)
      }
    )
  }

  /*
      Populate Response
   */
  override def populateResponse(organisation:Organization): OrganisationResponse =
    OrganisationResponse(organisation.id,organisation.name,organisation.details
      ,organisation.date_created.getTime,organisation.author_id,
      organisation.date_updated.getTime,organisation.updated_by)
}
