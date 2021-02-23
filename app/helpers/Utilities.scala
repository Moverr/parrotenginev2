package helpers

import java.security.MessageDigest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import java.util.regex.Pattern

object Utilities {

  val emailRegex:String = "^(.+)@(.+)$"


  private val encryptionAlgorithm:String  = "MD5"
  val mapper = new ObjectMapper() with ScalaObjectMapper
  //this line my be needed depending on your case classes
  mapper.registerModule(DefaultScalaModule)



  def encrypt(s:String): String =  MessageDigest.getInstance(encryptionAlgorithm).digest(s.getBytes)
      .map(_.toChar).mkString


  def fromJson[T](json: String)(implicit m: Manifest[T]): T = mapper.readValue[T](json)


  def validateString(matchString: String, regex:String):Boolean= Pattern.compile(regex).matcher(matchString).matches()


}
