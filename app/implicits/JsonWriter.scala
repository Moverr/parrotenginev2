package implicits

trait JsonWriter[A] {
    def  write(value:A):Json
}
