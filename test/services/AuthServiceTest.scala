package services

import daos.UserDao
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec

class AuthServiceTest extends PlaySpec with MockitoSugar {
  "Auth service" should  {
    "returun user when login is succesful" in {
      val userDao = mock[UserDao]
      userDao.getUserByName("sddd").mustBe(null)
        val authService = new AuthService(userDao)
      authService.validate(null) mustBe(null)
 
    }
  }
}
