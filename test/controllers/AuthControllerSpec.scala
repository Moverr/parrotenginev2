package controllers
import org.scalatest._
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Results
import play.api.test.FakeRequest

class AuthControllerSpec extends PlaySpec with Results {

  "respond to the index Action" in new App(applicationWithRouter) {
    val Some(result) = route(app, FakeRequest(GET_REQUEST, "/Bob"))

    status(result) mustEqual OK
    contentType(result) mustEqual Some("text/html")
    contentAsString(result) must include("Hello Bob")
  }


}