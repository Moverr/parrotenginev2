package controllers


import controllers.responses.{UserResponse, OrganisationResponse, StationResponse}
import daos.{OrganisationDAO, StationDAO, UserDao}
import db.tables.Station
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

import scala.concurrent.Future


class StationControllerTest extends PlaySpec {

  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]


  val userDao:UserDao =  new UserDao(dbConfProvider)
  val orgDaO:OrganisationDAO =   new OrganisationDAO(dbConfProvider)
  val orgService =  new OrganizationService(orgDaO)
  val stationDao =  Mockito.mock(classOf[StationDAO])

//    new StationDAO(dbConfProvider)
  val authService:AuthService =  Mockito.mock(classOf[AuthService])


  var stations:Seq[Station] = Seq[Station]()
  stations = stations  :+  Station(0L,0L,"tetst","test")



//  val stationService:StationService =  new StationService(stationDao,orgDaO)


  val orgDao:OrganisationDAO = Mockito.mock(classOf[OrganisationDAO])

  val token:String = "token"

//  val controller   = new StationController(Helpers.stubControllerComponents(),authService,stationService)

  //todo: mock the auth service
//  Mockito.when(authService.validateTokenv2("token")).thenReturn(  UserResponse("token","mose",10))
//  todo: Mock the Station Dao
//  Mockito.when(stationDao list(1,0,6)) thenReturn(Future.successful(stations))
// todo: Mock the Organization DAO

  "Station Controller " should  {
    "list Stations " in {
//      val response = controller.list(1,0,6).apply(FakeRequest(Helpers.GET, "/v1/station/list").withHeaders(
//        "authentication"->token
//      ))
//
//      val bodyText: String = contentAsString(response)
//      val expectedResult:List[StationResponse] = Utilities.fromJson[List[StationResponse]](bodyText)
//
//      status(response) mustBe  OK
//      assert(expectedResult.length > 0 )
    }
  }



}
