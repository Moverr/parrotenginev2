import scala.collection.View.Empty

def find[A](xs: List[A], xs1:Option[List[A]], predicate: A => Boolean): Option[List[A]] = {
  xs match {
    case Nil => None
    case head :: tail =>
      if (predicate(head)){
        xs1
      }  else {
        find(tail,xs1, predicate)
      }
  }
}