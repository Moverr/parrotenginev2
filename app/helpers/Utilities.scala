package helpers

import java.security.MessageDigest

class Utilities {

  val encryptionAlgorithm:String  = "MD5"

  def encrypt(s:String): Unit ={
    MessageDigest.getInstance(encryptionAlgorithm).digest(s.getBytes)
  }


}
