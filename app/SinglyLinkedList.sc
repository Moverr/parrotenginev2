import scala.annotation.tailrec

sealed trait ListCustom[A]

case object Nal extends ListCustom[Nothing]
case class Borz[A](head:A,tail:ListCustom[A]) extends ListCustom[A]
//case class Borza[A](head:A,head2:A,tail:ListCustom[A]) extends ListCustom[A]
//convariant vs invariant

def object BorzLlist {


  def sum(n: ListCustom[Int]): Int = {


    def go(n: ListCustom[Int]): Int = n match {
      case Nal => 0
      case Borz(head, tail) => head + go(tail)
    }

    go(n)
  }
//tail recursive and recursive are two different things in the general sense

  def product(n:ListCustom[Double]):Double= n match {
    case Nal => 1.0
    case Borz(head, tail) =>head * product(tail)
  }


//variadic function
  def apply[A](as:A*):ListCustom[A] =
    if(as.isEmpty) BorzLlist[A]
    else Borz(as.head,apply(as.tail:_*))
}

//val op:ListCustom[Int] = ListCustom(Nal)

//val result = sum(op)