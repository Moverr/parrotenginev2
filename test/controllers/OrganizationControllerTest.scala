package controllers

import daos.OrganisationDAO
import org.mockito.Mockito
import org.scalatest.FunSuite
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers
import services.{AuthService, OrganizationService}

class OrganizationControllerTest extends PlaySpec {

  val authService:AuthService = Mockito.mock(classOf[AuthService])
  val orgDaO:OrganisationDAO =  Mockito.mock(classOf[OrganisationDAO])
  val orgService = new OrganizationService(orgDaO)

  "Organization Controller " should {
    "list" in  {
      //initilize Controller
      val controller   = new OrganizationController(orgService,authService,Helpers.stubControllerComponents())

      assert(1==1)
    }
  }
}
