package helpers

import java.security.MessageDigest

class Utilities {

  val encryptionAlgorithm:String  = "MD5"

  def encrypt(s:String): String ={
    val digest:String =  MessageDigest.getInstance(encryptionAlgorithm).digest(s.getBytes)
      .map(_.toChar).mkString
      digest
  }


}
