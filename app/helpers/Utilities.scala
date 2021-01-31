package helpers

import java.security.MessageDigest

object Utilities {

  private val encryptionAlgorithm:String  = "MD5"

  def encrypt(s:String): String ={
      MessageDigest.getInstance(encryptionAlgorithm).digest(s.getBytes)
      .map(_.toChar).mkString
  }


}
