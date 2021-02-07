package controllers
import daos.UserDao
import org.scalatest._
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers._
import services.IAuthService




class AutuhControllerTest(authservice: IAuthService) extends PlaySpec  {

  "EAuth Controller" should {
    "Login Client " in {

      val userDao =  new UserDao(null)
      val controller = new AuthController(stubControllerComponents(),authservice)
    }
  }
}