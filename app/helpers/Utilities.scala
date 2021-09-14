package helpers

import java.security.MessageDigest
import java.sql.Timestamp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import java.util.regex.Pattern
import java.text.Format
import java.text.SimpleDateFormat
import java.time.Instant

import org.joda.time.DateTime



object Utilities {

  val emailRegex:String = "^(.+)@(.+)$"
  val simplePhonePatternRegex:String = "^\\d{10}$"
  val phoneWithSpacePatternRegex:String = "^(\\d{3}[- .]?){2}\\d{4}$"
  val mapper = new ObjectMapper() with ScalaObjectMapper

  mapper.registerModule(DefaultScalaModule)

  def matchEncryption(x:Int) : String = x match {
    case 1=> "MD5"
    case 2=> "AES"
    case _=>"MD5"
  }


  def matchRegex(matchType:String) : String = matchType match {
    case "email"=> emailRegex
    case "simplephonepattern"=> simplePhonePatternRegex
    case "phonewithSpacePatternRegex"=> phoneWithSpacePatternRegex
    case _=>  ""
  }



  def encrypt(s:String): String =  MessageDigest.getInstance(matchEncryption(0)).digest(s.getBytes) .map(_.toChar).mkString

  def fromJson[T](json: String)(implicit m: Manifest[T]): T = mapper.readValue[T](json)

  def validateRegexString(matchString: String, matchType:String):Boolean= Pattern.compile(matchRegex(matchType)).matcher(matchString).matches()


  def convertLongToDateTime(time: Option[Long])= time match {
      case Some(c) =>  new  DateTime(c)
      case None => new  DateTime()
    }


  def getCurrentTimeStamp(dateTimeObject:DateTime)=new Timestamp( dateTimeObject.getMillis)
  def getCurrentTimeStamp():Timestamp =  getCurrentTimeStamp( new DateTime())
  def getCurrentTimeStampLong =   Instant.now().getEpochSecond





}
