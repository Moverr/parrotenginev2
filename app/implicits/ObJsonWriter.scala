package implicits

object ObJsonWriter {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)
}

