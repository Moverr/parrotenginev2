// Define a very simple JSON AST
sealed trait Json

final case class JsObject(get: Map[String,Json]) extends Json
final case class JsString(get:String) extends Json
final case class JsNumber[T](get:T) extends Json
final case object JsNull extends Json
