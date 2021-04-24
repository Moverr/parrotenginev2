import scala.util.parsing.json.JSONObject

// Define a very simple JSON AST
sealed trait Json

final case class JsObject(get: Map[String,Json]) extends Json
final case class JsString(get:String) extends Json
final case class JsNumber[T](get:T) extends Json
final case object JsNull extends Json

// the serializer to JSON beavieor is encoded in this trait
trait JSonWriter[A]{

  def write(value: A) : Json
}

final case class Person(name:String,email:String)

object  JSonWriterInstance{
  implicit  val stringWriter:JSonWriter[String] =
    new JSonWriter[String] {
      override def write(value: String) =
        JsString(value)
    }


  implicit  val stringWriter3:JSonWriter[Integer] =
    new JSonWriter[Integer] {
      override def write(value: Integer) = JsNumber[Integer](value)

    }



  implicit  val stringWriter4:JSonWriter[Float] =
    (value: Float) => JsNumber(value)



  implicit  val stringWriters:JSonWriter[String] =
    (value: String) => JsString(value)

  implicit  val personWriter:JSonWriter[Person] = new JSonWriter[Person] {
    override def write(value: Person) :Json = {
      JsObject(
        Map{
          "name"->JsString(value.name)
          "email"->JsString(value.email)
        }
      )
    }

  }


}

val x = new Person("Muyinda Rogers","moverr@gmail.com")
  JSonWriterInstance.personWriter(x)
