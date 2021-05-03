package services

import controllers.requests.{OrganisationRequest, StationRequest}
import controllers.responses.{AuthResponse, OrganisationResponse, StationResponse}
import daos.{OrganisationDAO, StationDAO}
<<<<<<< HEAD
import db.tables.Station
import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
=======
import javax.inject.Inject

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Future}
>>>>>>> 17393492fed896e7726ae40acf6dc75016fce3bf


class StationService   @Inject()(
                                  stationDao: StationDAO
                                ,  organizationService: OrganizationService

                                )  {

  //todo: Create
    def create(authResponse: AuthResponse,organisation_id:Int,request:StationRequest): Either[java.lang.Throwable,Future[OrganisationResponse]]={
    if(authResponse == null )   return  Left(new Exception("Invalid Authentication"))

    //todo: Get Account Details  ::
      organizationService.get(authResponse, organisation_id) match {
        case Left(value) =>  Left(new Exception(value.getMessage))
        case Right(resp) => resp.flatMap{
          y=>
            y match {
              case Some(value) => {
<<<<<<< HEAD

                  stationDao.create(value.id,request)
                  .map(x=>   return  Left(new Exception("Organization does not exist")))
                    //return  Right(populateResponse(x) ))


=======
                //todo: Organization
                Right(organisationDAO.createOrganisation(request.name,request.details,authResponse.user_id)
                  .map(x=>populateResponse(x)))
>>>>>>> 17393492fed896e7726ae40acf6dc75016fce3bf
              }
              case None =>  return  Left(new Exception("Organization does not exist"))
            }
        }
      }



  }

  //todo: list

  //todo: Archive

<<<<<<< HEAD
  def populateResponse(station: Station):StationResponse={

    val stationRespnse = new StationResponse(station.id,station.name,station.code,null)
    stationRespnse
  }
}


import java.math.BigInteger

object Sol3 {
  private val bigIntegerTwo = new BigInteger("2")

  def main(args: Array[String]): Unit = {
    val s = new Sol3
    System.out.println(s.solution("011100"))
    System.out.println(s.solution("111"))
    System.out.println(s.solution("1111010101111"))
    System.out.println(s.solution("1"))
  }
}

class Sol3 {
  def solution(S: String): Int = {
    val input = new BigInteger(S, 2)
    getSteps(input, 0)
  }

  private def getSteps(input: BigInteger, counter: Int): Int = {
    if (input == BigInteger.ZERO) return counter
    counter += 1
    if (input.mod(Sol3.bigIntegerTwo) == BigInteger.ZERO) getSteps(input.divide(Sol3.bigIntegerTwo), counter)
    else getSteps(input.subtract(BigInteger.ONE), counter)
=======
  def populateResponse():StationResponse={

    ???
>>>>>>> 17393492fed896e7726ae40acf6dc75016fce3bf
  }
}
