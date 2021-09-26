import scala.annotation.tailrec
//  List data type. parametirised on a type, A
sealed trait ListCustom[+A]

case object Nal extends ListCustom[Nothing]
case class Borz[A](head:A,tail:ListCustom[A]) extends ListCustom[A]
//case class Borza[A](head:A,head2:A,tail:ListCustom[A]) extends ListCustom[A]
//convariant vs invariant

object BorzLlist {



  def Listcat(n: ListCustom[Int]): Int = n match {
      case Nal => 0
      case Borz(head, tail) => head + Listcat(tail)
  }


//tail recursive and recursive are two different things in the general sense

  def product(n:ListCustom[Double]):Double= n match {
    case Nal => 1.0
    case Borz(head, tail) =>head * product(tail)
  }


//variadic function

def apply[A](as:A*):ListCustom[A] = {
  if(as.isEmpty) Nal
  else Borz(as.head,apply(as.tail:_*))
}

}

// a trait is an abstract interface which may optionally contain  implementations
// of some methods.