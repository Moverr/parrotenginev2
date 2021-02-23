package helpers

import java.security.MessageDigest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import java.util.regex.Pattern

object Utilities {

  val emailRegex:String = "^(.+)@(.+)$"
  val phoneRegex:String = "^(.+)@(.+)$"
  val mapper = new ObjectMapper() with ScalaObjectMapper

  mapper.registerModule(DefaultScalaModule)

  def matchEncryption(x:Int) : String = x match {
    case 1=> "MD5"
    case 2=> "AES"
    case _=>"MD5"
  }


  def matchRegex(matchType:String) : String = matchType match {
    case "email"=> emailRegex
    case "phone"=> phoneRegex
    case _=>""
  }



  def encrypt(s:String): String =  MessageDigest.getInstance(matchEncryption(0)).digest(s.getBytes) .map(_.toChar).mkString

  def fromJson[T](json: String)(implicit m: Manifest[T]): T = mapper.readValue[T](json)

  def validateRegexString(matchString: String, matchType:String):Boolean= Pattern.compile(matchRegex(matchType)).matcher(matchString).matches()


}
