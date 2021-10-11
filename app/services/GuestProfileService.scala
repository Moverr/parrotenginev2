package services

import daos.{ProfileDAO, ResidentProfileDAO}
import javax.inject.{Inject, Singleton}

@Singleton
class GuestProfileService  @Inject()(
                                      residentDAO: ResidentProfileDAO
                                      , profileDAO: ProfileDAO
                                      , stationService: StationService

                                    ) {

  //todo create
  //list guests on a given statioon or visitor on a given day

  //update profile info
  //cance information

//  register profile.
//  // check if  host exists. .
//  visitation type..


}
