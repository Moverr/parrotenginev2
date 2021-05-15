package controllers


import controllers.responses.{AuthResponse, OrganisationResponse}
import daos.{OrganisationDAO, UserDao}
import helpers.Utilities
import org.mockito.Mockito
import org.scalatestplus.play.PlaySpec
import play.api.Mode
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.Injector
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers.contentAsString
import play.api.test.{FakeRequest, Helpers}
import services.{AuthService, OrganizationService, StationService}

import play.api.test.Helpers._


class StationControllerTest extends PlaySpec {

  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]


  val userDao:UserDao =  new UserDao(dbConfProvider)
  val orgDaO:OrganisationDAO =   new OrganisationDAO(dbConfProvider)
  val orgService =  new OrganizationService(orgDaO)

  val authService:AuthService =  Mockito.mock(classOf[AuthService])
  val stationService:StationService =  Mockito.mock(classOf[StationService])


  val orgDao:OrganisationDAO = Mockito.mock(classOf[OrganisationDAO])

  val token:String = "token"

  val controller   = new StationController(Helpers.stubControllerComponents(),authService,stationService)
  Mockito.when(authService.validateTokenv2("token")).thenReturn(  AuthResponse("token","mose",10))


  "Station Controller " should  {
    "list Stations " in {
      val response = controller.list(1,0,6).apply(FakeRequest(Helpers.GET, "/v1/station/list").withHeaders(
        "authentication"->token
      ))

      val bodyText: String = contentAsString(response)
      val expectedResult:List[OrganisationResponse] = Utilities.fromJson[List[OrganisationResponse]](bodyText)

      assert(expectedResult.length > 0 )


    }
  }



}
