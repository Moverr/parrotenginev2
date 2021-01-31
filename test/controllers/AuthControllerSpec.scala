package controllers
import org.scalatest._


val application: Application = new GuiceApplicationBuilder()
.configure("some.configuration" -> "value")
.build()