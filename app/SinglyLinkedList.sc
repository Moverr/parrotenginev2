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

//  def ++[A](n:ListCustom[A],b:ListCustom[A])[A] = n+b

//variadic function

def apply[A](as:A*):ListCustom[A] = {
  if(as.isEmpty) Nal
  else Borz(as.head,apply(as.tail:_*))
}

}



def dropWhile[A](l:List[A],f:A=>Boolean): List[A]

val xst:List[Int] = List(1,2,3,4,5)


val kio = dropWhile(xst,(x:Int)=>x<4)

kio

// a trait is an abstract interface which may optionally contain  implementations
// of some methods.

//out of a class we can make companion objects. and we can gegt use of them depending on pattern matching.



//Types of pattern matching
// variable pattern.
// uing data constructor patter..
// Match error runtime exeception

//we dont copy data, we reusue it.

