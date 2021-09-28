import org.graalvm.compiler.nodes.NodeView.Default

sealed  trait  Obote[+A]

case class Some[+A](get:A) extends Obote[A]
case object None extends Obote[Nothing]

//investigate the item Nothing ad move on

def mean[A](xs:Seq[A]):Obote[A] = {
  if(xs.length == 0) None
  else Some[A](xs(8))
}

val p = Seq(1,2,3,4,5,5,56,43,45,34,23,43,34)
mean(p)


sealed trait  Option[+A]{
  def map[B](f:A=>B):Option[B]
  def flatMap[B](f:A=>Option[B]):Option[B]
  def getOrElse[B>:A](default: => B):B
  def orElse[B >: A](ob : => Option[B]): Option[A]
  def filter(f:A => Boolean) : Option[A]
}

// need to learn about traits...
//