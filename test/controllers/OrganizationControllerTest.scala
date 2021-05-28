package controllers

import java.sql.Timestamp

import controllers.responses.{AuthResponse, OrganisationResponse}
import daos.{OrganisationDAO, UserDao}
import db.tables.{Organization, Station}
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
import scala.concurrent.{ExecutionContext, Future}


class OrganizationControllerTest extends PlaySpec {



  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]


  val userDao:UserDao =  new UserDao(dbConfProvider)
  val orgDaO:OrganisationDAO = Mockito.mock(classOf[OrganisationDAO])
    //Mockito.mock(classOf[OrganisationDAO])
  val orgService =  new OrganizationService(orgDaO)

  val authService:AuthService =  Mockito.mock(classOf[AuthService])



  val token:String = "token"

  var  org =  Organization( 0L, "name", "details", 10,new Timestamp(0L),0L,new Timestamp(0L),0L);
  var organizations:Seq[Organization] = Seq[Organization]()
  organizations = organizations  :+   Organization( 1, "name", "details", 10,new Timestamp(0L),0,new Timestamp(0L),0)



  "Organization Controller " should {



    val controller   = new OrganizationController(orgService,authService,Helpers.stubControllerComponents())
   //todo: Mock Auth Service

    "list Organizations " in  {

      Mockito.when(authService.validateTokenv2("token")).thenReturn(  AuthResponse("token","mose",10))

      Mockito.when(orgDaO.getOrganisations(10,0,6)).thenReturn(Future.successful(organizations))

      val response = controller.list(0,6).apply(FakeRequest(Helpers.GET, "/v1/organisation/list").withHeaders(
        "authentication"->token
      ))

      val bodyText: String = contentAsString(response)
      val expectedResult:List[OrganisationResponse] = Utilities.fromJson[List[OrganisationResponse]](bodyText)

      assert(expectedResult.length > 0 )

    }

    "Create  Organization " in {
      val jsonBody = Json.parse("{\"name\":\"name\", \"details\":\"details\" }")

      Mockito.when(authService.validateTokenv2("token")).thenReturn(  AuthResponse("token","mose",10))


      Mockito.when(orgDaO.createOrganisation("name","details",10)).thenReturn(Future.successful(org))

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
