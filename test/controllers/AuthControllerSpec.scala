package controllers
import daos.UserDao
import org.scalatest._
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test.Injecting
import services.IAuthService




class AuthControllerSpec(authservice: IAuthService) extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "Example Page#index" should {
    "should be valid" in {

      val userDao =  new UserDao(null)
      val controller = new AuthController(stubControllerComponents(),authservice)
    }
  }
}