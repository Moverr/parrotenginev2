case class User(name: String, age: Int)

val userBase = List(
  User("Travis", 28),
  User("Kelly", 33),
  User("Jennifer", 44),
  User("Dennis", 23))

val twentySomethings:List[Int] =
  for (user <- userBase if user.age >=20 && user.age < 30)
    yield user.age  // i.e. add this to a list

twentySomethings.foreach(name => println(name))


def square(x:Int):Int = x * x

val collection = List(1, 3, 2, 5, 4, 7, 6)


val new_collection =collection.map(x=>{
  if(x % 2 == 0)  x*x*x
  else 0
}).toSet


val name = Seq("Nidhi", "Singh")
val result1 = name.map(_.toLowerCase)
val result2 = result1.flatten


val lol = List(List(1,2), List(3,4))
 lol.flatten
 lol.flatten
  .map(x => x + 1)
//converting from muplipe lists to a single list.
// then looping through the list.