import org.scalatest._
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder


val application: Application = new GuiceApplicationBuilder()
  .configure("some.configuration" -> "value")
  .build()