package controllers

import play.api.mvc.AbstractController
import javax.inject.{Inject, Singleton}

class ResidentContnroller  @Inject()( val cc: ControllerComponents)

  extends AbstractController(cc) {

}
