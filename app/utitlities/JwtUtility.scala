package utitlities

import javax.inject.Singleton
import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtHeader}
import com.nimbusds.jose.{JWSAlgorithm, JWSHeader, JWSObject, Payload}

@Singleton
class JwtUtility {
  val JwtSecretKey = "secretKey"
  val JwtSecretAlgo = "HS256"

  def generateKey: Unit ={
    ???
  }

  def retrievPasswordPair: Unit ={
    ???
  }


}

