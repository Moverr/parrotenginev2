package services

import controllers.requests.OrganisationRequest
import controllers.responses.{AuthorResponse, OrganisationResponse, UserResponse}
import daos.OrganisationDAO
import db.tables.{Organization, Profile, User}
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class OrganizationService  @Inject()(organisationDAO: OrganisationDAO)  extends IOrganisationServiceTrait
{

  //todo: create organisation
  override def create(authResponse: UserResponse, request:OrganisationRequest): Either[java.lang.Throwable,Future[OrganisationResponse]]={
    if(authResponse == null )   return  Left(new Exception("Invalid Authentication"))

      //todo: Get Account Details  ::

       Right(organisationDAO.create(request.name,request.details,authResponse.user_id)
      .map(record=>populateResponse(record,None)))
  }

  //todo: list organinsations
  override def list(authResponse: UserResponse, offset:Int, limit:Int): Either[java.lang.Throwable,Future[Seq[OrganisationResponse]] ]= {
    if(authResponse == null ) return  Left(new Exception("Invalid Authentication"))
    val result : Future[Seq[((Organization,User),Profile)]] =  organisationDAO.list(authResponse.user_id,offset,limit)


    Right{
     result.map{
       y=>y.map(p=>populateResponse(p._1._1,Some(p._2)))
     }
    }


  }

  //todo: Get Organization
  override def get(authResponse: UserResponse, id:Int):  Either[java.lang.Throwable,Future[Option[OrganisationResponse]]] ={
    if(authResponse == null )  return  Left(new Exception("Invalid Authentication"))

    Right(
    organisationDAO.get(authResponse.user_id,id)
      .flatMap{
        case Some(value) => Future.successful(Some(populateResponse(value._1._1,Some(value._2))))
        case None =>  Future.successful(None)
      }
    )
  }

  /*
      Populate Response
   */
  override def populateResponse(organisation:Organization, profile: Option[Profile]): OrganisationResponse = {
    val response =  OrganisationResponse(organisation.id,organisation.name,organisation.details
      ,organisation.date_created.getTime, populateAuthor(profile),
      organisation.date_updated.getTime,organisation.updated_by)

    response
  }



  // populate response based on
  def populateAuthor(author:Option[Profile]):Option[AuthorResponse]= author match {
      case Some(value) => Some( AuthorResponse(value.surname,value.other_names))
      case None =>None
    }

}
