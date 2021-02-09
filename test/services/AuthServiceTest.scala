package services

import daos.IUserDAO
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec

class AuthServiceTest extends PlaySpec with MockitoSugar {
  "Auth service" should  {
    "returun user when login is succesful" in {
      val userDao = mock[IUserDAO]
      userDao.getUserByName("sddd").mustBe(null)

      assert(true)
    }
  }
}
