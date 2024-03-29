package utitlities

import javax.inject.Singleton
import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtHeader}
import com.nimbusds.jose.crypto.{RSASSASigner, RSASSAVerifier}
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import com.nimbusds.jose.{JWSAlgorithm, JWSHeader, JWSObject, JWSSigner, JWSVerifier, Payload}

@Singleton
class JwtUtility {

  val keySize = 2048
  val keyId = "secretKey"

  def generateKey(payload:String): String ={
    val rsaJWK:RSAKey  = generateKey(keySize,keyId)
    val signer:JWSSigner  = new RSASSASigner(rsaJWK)
    val jwsObject:JWSObject  = new JWSObject(new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(), new Payload(payload));
    jwsObject.sign(signer)
    jwsObject.serialize();
  }

  def retrievePasswordPair(token:String): Option[String] ={

    try {
      val rsaJWK:RSAKey  =generateKey(keySize,keyId)
      val rsaPublicJWK:RSAKey = rsaJWK.toPublicJWK()
      val jwsObject = JWSObject.parse(token);
      val verifier:JWSVerifier  = new RSASSAVerifier(rsaPublicJWK);
      Some(jwsObject.getPayload().toString())

    }
    catch {
      case e:Exception => None
    }

  }

  def generateKey(keySize:Int,keyId:String): RSAKey =   new RSAKeyGenerator(keySize) .keyID(keyId) .generate()

}

object JwtUtility extends JwtUtility