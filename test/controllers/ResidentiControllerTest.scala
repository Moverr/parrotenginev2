package controllers

import org.scalatestplus.play.PlaySpec
import play.api.Mode
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.Injector
import play.api.inject.guice.GuiceApplicationBuilder

class ResidentiControllerTest extends PlaySpec {

  lazy val appBuilder: GuiceApplicationBuilder = new GuiceApplicationBuilder().in(Mode.Test)
  lazy val injector: Injector = appBuilder.injector()
  lazy val dbConfProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]


  "Resident Controller " should  {
    "Create Station  " in {

      //todo: create stattion and move on  
    }
  }
}
