//sealed trait  Tree[+A]
//case class Leaf[A](value:A) extends Tree[A]
//case class Branch[A](left: Tree[A],right:Tree[A]) extends Tree[A]

//algebraic data types  ADTs



//refrerential transparency

sealed trait Option[+A]
case Some[+A]()(get:A) extends Option[A]
