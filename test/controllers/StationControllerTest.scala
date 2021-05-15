package controllers

import daos.{OrganisationDAO, UserDao}
import org.mockito.Mockito
import org.scalatest.FunSuite
import org.scalatestplus.play.PlaySpec
import play.api.Mode
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.Injector
import play.api.inject.guice.GuiceApplicationBuilder
import services.{AuthService, OrganizationService}

class StationControllerTest extends PlaySpec {

  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]


  val userDao:UserDao =  new UserDao(dbConfProvider)
  val orgDaO:OrganisationDAO =   new OrganisationDAO(dbConfProvider)
  val orgService =  new OrganizationService(orgDaO)

  val authService:AuthService =  Mockito.mock(classOf[AuthService])

  val orgDao:OrganisationDAO = Mockito.mock(classOf[OrganisationDAO])

  val token:String = "token"

  "Station Controller " should  {
    "list Organizations " in {

    }
  }



}
