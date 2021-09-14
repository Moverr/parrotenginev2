package controllers

import daos.{ResidentProfileDAO, StationDAO}
import org.mockito.Mockito
import org.scalatestplus.play.PlaySpec
import play.api.Mode
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.Injector
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.test.{FakeRequest, Helpers}
import services.{AuthService, ResidentialService, StationService}

class ResidentiControllerTest extends PlaySpec {

  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]

  val residentDAO =  Mockito.mock(classOf[ResidentProfileDAO])
  val authService:AuthService =  Mockito.mock(classOf[AuthService])
  val stationService:StationService =  Mockito.mock(classOf[StationService])

  val residentService:ResidentialService =   new ResidentialService(residentDAO,stationService);

  val controller   = new ResidentController(Helpers.stubControllerComponents(),authService,residentService)
  "Resident Controller " should  {
    "Create Station  " in {
      val jsonBody = Json.parse("{\"name\":\"name\", \"details\":\"details\" }")
      val token:String = "token"

      //todo: create stattion and move on
      val response = controller.create().apply(FakeRequest(Helpers.POST, "/v1/organisation/create")
        .withJsonBody(jsonBody)
        .withHeaders(
          "authentication"-> token
        ))

    }
  }
}
