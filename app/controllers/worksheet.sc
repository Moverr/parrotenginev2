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