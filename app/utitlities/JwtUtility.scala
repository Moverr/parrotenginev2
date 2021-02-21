package utitlities

import javax.inject.Singleton
import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtHeader}
import com.nimbusds.jose.{JWSAlgorithm, JWSHeader, JWSObject, Payload}

@Singleton
class JwtUtility {
  val JwtSecretKey = "secretKey"
  val JwtSecretAlgo = "HS256"

  JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),new Payload("Hello, world!"))




}

