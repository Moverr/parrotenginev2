package controllers

import daos.OrganisationDAO
import org.mockito.Mockito
import org.scalatest.FunSuite
import org.scalatestplus.play.PlaySpec
import play.api.test.{FakeRequest, Helpers}
import services.{AuthService, OrganizationService}

class OrganizationControllerTest extends PlaySpec {

  val authService:AuthService = Mockito.mock(classOf[AuthService])
  val orgDaO:OrganisationDAO =  Mockito.mock(classOf[OrganisationDAO])
  val orgService = new OrganizationService(orgDaO)

  "Organization Controller " should {
    "list" in  {
      //initilize Controller
      val controller   = new OrganizationController(orgService,authService,Helpers.stubControllerComponents())
      val response = controller.list(0,6).apply(FakeRequest(Helpers.GET, "/v1/organisation/list").withHeaders(
        "Authorization"->"eyJraWQiOiJzZWNyZXRLZXkiLCJhbGciOiJSUzI1NiJ9.a29sYUBnbWFpbC5jb206Te-_jU3vv6zvv57vvq1s776w77-NSwMyHu--ju-_oO--kA.G-dXiik8psyamRhPXRAnC6KR32dqgIyIeERVEs-UH6A_izzVfKfxnzjY19-9zReGJjqXqT-LE2OPIAH9PgeFdAHa67T4uHmmqGVs3Qm6qDe3cehx_gbfkkpa0mDIi_VRlX-CVJ5Hi8DHlirn0OXPTTSAQKmxgvESCjQtTPOxkf_x4uGW4m9SB6BrLGONV_6napkCsaFKjvGqvxu-ligs12to8VL2HJWabf2P3qdn5qubzKWywd-4exg2_i7iD_NDL1ClBYvRtISWyLMvT7sCJO0c3Uuyju1DPQfl760rfx9HGFzZ8cJrUF5aJLrSdRUBI3zx2hq-LTq0gXIxCAOFNw"
      ))



      assert(1==1)
    }
  }
}
