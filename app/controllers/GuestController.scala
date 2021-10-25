package controllers

import controllers.requests.{GuestProfileRequest, PhysicalAddress, ProfileRequest, ProfileType}
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.{AuthService, GuestService}

@Singleton
class GuestController @Inject()(
                                 controllerComponents: ControllerComponents
                                 , val authService: AuthService
                                 , val guestService: GuestService
                               ) extends AbstractController(controllerComponents) {

  //todo: create visiotr regirstration profile
  def selfregister = Action.async { implicit request =>


    val surname: String = request.body.asJson.get("surname").as[String]
    val otherName: String = request.body.asJson.get("otherName").as[String]
    val profileType: String = request.body.asJson.get("profiletype").as[String]
    val gender: String = request.body.asJson.get("gender").as[String]
    //    val stationid: Int = request.body.asJson.get("stationid").as[String].trim.toInt

    val registratioon_date_long: Long = request.body.asJson.get("registration_date").as[Long]
    val location: String = request.body.asJson.get("location").as[String]
    //host information
    val host_id = request.body.asJson.get("host_id").as[Long]


    val latitude: Double = request.body.asJson.get("location").as[Double]
    val longitude: Double = request.body.asJson.get("location").as[Double]

    val address_location = PhysicalAddress(location, "LOCATION", Some(latitude), Some(longitude))


    //todo: guest profile reequest
    val guestRequest: ProfileRequest = GuestProfileRequest(surname, otherName, ProfileType.withName(profileType), gender, host_id, registratioon_date_long, address_location)


    ???

  }

  //todo; view registrations etcs,
}
