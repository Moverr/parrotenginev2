object Greeting{
  def english = "Hi"
  def espanol ="Hola"
}

val x = Greeting
val y = x
x eq y

val z = Greeting
x eq z
