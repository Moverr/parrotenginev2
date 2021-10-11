package controllers





import java.sql.Timestamp

import controllers.responses.{AuthResponse, OrganisationResponse, ResidentProfileResponse, StationResponse}
import daos.{OrganisationDAO, ProfileDAO, ResidentProfileDAO, StationDAO}
import db.tables.{Profile, Resident}
import helpers.Utilities._
import helpers.Utilities.getCurrentTimeStamp
import org.joda.time.DateTime
import org.mockito.Mockito
import org.scalatestplus.play.PlaySpec
import play.api.Mode
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.Injector
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.test.Helpers.{contentAsString, status}
import play.api.test.{FakeRequest, Helpers}
import services.{AuthService, ResidentialService, StationService}

import scala.concurrent.{ExecutionContext, Future}
import play.api.test.Helpers._



class ResidentControllerTest extends PlaySpec {

  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]

  //mocking the utils.
  val staticDateforTests= new Timestamp( new DateTime().getMillis)
  val utils = Mockito.mock(classOf[helpers.Utilities])

  Mockito.when(utils.getCurrentTimeStamp()).thenReturn( staticDateforTests)
  val profileDao =   new ProfileDAO(dbConfProvider)
    //Mockito.mock(classOf[ProfileDAO])
  val residentDAO =   new ResidentProfileDAO(dbConfProvider)
    //Mockito.mock(classOf[ResidentProfileDAO])



  val profile = Profile(0L, None, "surname", "otherName", "male", "RESIDENT", 1, getCurrentTimeStamp(),1, getCurrentTimeStamp())
  val profileRes = Profile(1, None, "surname", "otherName", "male", "RESIDENT", 1, getCurrentTimeStamp(),1, getCurrentTimeStamp())

  val resident: Resident = new Resident(0L, profile.id, 1, getCurrentTimeStamp(), 1, getCurrentTimeStamp(), 1, getCurrentTimeStamp())





  //Mockito.mock(classOf[ResidentProfileDAO])
  val authService:AuthService =  Mockito.mock(classOf[AuthService])
  val stationService:StationService =  Mockito.mock(classOf[StationService])

  val residentService:ResidentialService =   new ResidentialService(residentDAO,profileDao,stationService)
  val token:String = "token"

  val controller   = new ResidentController(Helpers.stubControllerComponents(),authService,residentService)
  "Resident Controller " should  {
    "Create Resident  " in {




      val jsonBody = Json.parse("{\"surname\":\"surname\", \"otherName\":\"otherName\" , \"profiletype\":\"profiletype\", \"gender\":\"male\", \"stationid\":\"1\", \"registerdate\":\"null\" }")


      Mockito.when(authService.validateTokenv2("token")).thenReturn(  AuthResponse("token","mose",10))

      val stationResponse:Option[StationResponse] =  Some(StationResponse(1,"station","code",None))
      val stationResultResponse: Either[java.lang.Throwable,Future[Option[StationResponse]]]  =   Right(Future.successful(stationResponse))

      Mockito.when(stationService.get(  AuthResponse("token","mose",10),1)).thenReturn(stationResultResponse)


      //todo: create stattion and move on
      val response = controller.create().apply(FakeRequest(Helpers.POST, "/v1/resident/create")
        .withJsonBody(jsonBody)
        .withHeaders(
          "authentication"-> token
        ))

      val bodyText: String = contentAsString(response)

      status(response) mustBe  OK
      val expectedResult:ResidentProfileResponse = fromJson[ResidentProfileResponse](bodyText)
      expectedResult.profile.surname mustBe  "surname"



    }
  }
}
