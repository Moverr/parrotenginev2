package controllers

import daos.{OrganisationDAO, UserDao}
import org.mockito.Mockito
import org.scalatestplus.play.PlaySpec
import play.api.Mode
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.Injector
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers.contentAsString
import play.api.test.{FakeRequest, Helpers}
import services.{AuthService, OrganizationService}



import play.api.test.Helpers._









class OrganizationControllerTest extends PlaySpec {



  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]


  val userDao:UserDao =  new UserDao(dbConfProvider)
  val orgDaO:OrganisationDAO =  Mockito.mock(classOf[OrganisationDAO])
  val orgService = new OrganizationService(orgDaO)

  val authService:AuthService = new AuthService(userDao)
  "Organization Controller " should {
    "list" in  {
      val controller   = new OrganizationController(orgService,authService,Helpers.stubControllerComponents())
      val response = controller.list(0,6).apply(FakeRequest(Helpers.GET, "/v1/organisation/list").withHeaders(
        "Authorization"->"eyJraWQiOiJzZWNyZXRLZXkiLCJhbGciOiJSUzI1NiJ9.a29sYUBnbWFpbC5jb206UEBzc3dvcmQ_MTIz.TisoaWUsGQJIAcAFzpHvaFglEEN0GsC09lOvdL9qWn4V0qhVwUd0NG9_0S2OkvZ9rpzsWUYGfl2q3fN55UxLiOi_Q_ClAfj4Q6Hu8M1B2XYE_F8wZXclychzR98SV7wZK8elrPHgNZC4TILM3q7USJDOubVsG9ghnCEdRGEiqVAnjkdArEt7uP_wx068WfikqoIoJ_XGaijbq8J2Sua2pQYOq3PpADGtJIyGY0AdoribBYJRzruoYiGjYbmbxE1wZujCMllSzl8g1vY9JS7t2Etwec61IzdxTW-qR2T7Pf8UgDAsz4danKo1nh433WqN4H7AEcLy9eFGB7C1tQGDqQ"
      ))

      val bodyText: String = contentAsString(response)
      bodyText mustBe "ok"


    }
  }
}
