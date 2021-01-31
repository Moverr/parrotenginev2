package controllers

import javax.inject.Inject
import org.scalatest.FunSuite
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.mvc.ControllerComponents
import play.api.test.Helpers.stubControllerComponents
import play.api.test.Injecting

class AuthControllerSpec @Inject()(cc:ControllerComponents)
  extends PlaySpec with GuiceOneAppPerTest with Injecting{
  "Authorization Controller " should{
    "Mock Loging Experience " in {
        val x = new  AuthController(stubControllerComponents(),null)
    }

  }

}
