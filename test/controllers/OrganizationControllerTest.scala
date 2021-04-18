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
import play.api.libs.json.Json
import play.api.test.Helpers.contentAsString
import play.api.test.{FakeRequest, Helpers}
import services.{AuthService, OrganizationService}
import play.api.test.Helpers._

import scala.concurrent.Future









class OrganizationControllerTest extends PlaySpec {



  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]


  val userDao:UserDao =  new UserDao(dbConfProvider)
  val orgDaO:OrganisationDAO =   new OrganisationDAO(dbConfProvider)
  val orgService = new OrganizationService(orgDaO)

  val authService:AuthService =  Mockito.mock(classOf[AuthService])

  val token:String = "token"

  "Organization Controller " should {
    val controller   = new OrganizationController(orgService,authService,Helpers.stubControllerComponents())
    Mockito.when(authService.validateTokenv2("token")).thenReturn(new AuthResponse("token","mose",10))


    "list Stations" in  {

      val response = controller.list(0,6).apply(FakeRequest(Helpers.GET, "/v1/organisation/list").withHeaders(
        "authentication"->token
      ))

      val bodyText: String = contentAsString(response)
      val expectedResult:List[OrganisationResponse] = Utilities.fromJson[List[OrganisationResponse]](bodyText)

      assert(expectedResult.length > 0 )

    }
    val jsonBody = Json.parse("{\"name\":\"name\", \"details\":\"details\" }")


    "Create  Station " in {
      val response = controller.create().apply(FakeRequest(Helpers.POST, "/v1/organisation/create")
          .withJsonBody(jsonBody)
          .withHeaders(
        "authentication"-> token
      ))

      val bodyText: String = contentAsString(response)
      val expectedResult:OrganisationResponse = Utilities.fromJson[OrganisationResponse](bodyText)
      expectedResult.name mustBe  "name"


    }
  }
}
