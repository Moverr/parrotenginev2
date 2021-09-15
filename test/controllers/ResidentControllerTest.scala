package controllers

import controllers.responses.{AuthResponse, StationResponse}
import daos.{ProfileDAO, ResidentProfileDAO, StationDAO}
import org.mockito.Mockito
import org.scalatestplus.play.PlaySpec
import play.api.Mode
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.Injector
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.test.{FakeRequest, Helpers}
import services.{AuthService, ResidentialService, StationService}

import scala.concurrent.Future

class ResidentControllerTest extends PlaySpec {

  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]


  val profileDao = new ProfileDAO(dbConfProvider)
  val residentDAO = new ResidentProfileDAO(dbConfProvider)
    //Mockito.mock(classOf[ResidentProfileDAO])
  val authService:AuthService =  Mockito.mock(classOf[AuthService])
  val stationService:StationService =  Mockito.mock(classOf[StationService])

  val residentService:ResidentialService =   new ResidentialService(residentDAO,profileDao,stationService);

  val controller   = new ResidentController(Helpers.stubControllerComponents(),authService,residentService)
  "Resident Controller " should  {
    "Create Resident  " in {
      val jsonBody = Json.parse("{\"surname\":\"surname\", \"otherName\":\"otherName\" , \"profiletype\":\"profiletype\", \"gender\":\"gender\", \"stationid\":\"1\", \"registerdate\":\"null\" }")
      val token:String = "token"
      Mockito.when(authService.validateTokenv2("token")).thenReturn(  AuthResponse("token","mose",10))

      val stationResponse:Option[StationResponse] =  Some(StationResponse(1,"station","code",None))
      val stationResultResponse: Either[java.lang.Throwable,Future[Option[StationResponse]]]  =   Right(Future.successful(stationResponse))

      Mockito.when(stationService.get(  AuthResponse("token","mose",10),1)).thenReturn(stationResultResponse)


      //todo: create stattion and move on
      val response = controller.create().apply(FakeRequest(Helpers.POST, "/v1/resident/create   ")
        .withJsonBody(jsonBody)
        .withHeaders(
          "authentication"-> token
        ))

    }
  }
}
