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
  def create(token:String,request:OrganisationRequest): Future[OrganisationResponse] ={
     val auth:Future[Option[AuthResponse]] =  authService.validate(token)
    ???
  }
  //todo: list organinsations
  def list(token:String,limit:Int, offset:Int): Future[Seq[OrganisationResponse]]  = {
    val auth:Future[Option[AuthResponse]] =  authService.validate(token)
    ???
  }
  //todo: Get Organization
  def get(token:String,id:Int,): Future[Option[OrganisationResponse]] ={
    val auth:Future[Option[AuthResponse]] =  authService.validate(token)
    ???
  }
}
