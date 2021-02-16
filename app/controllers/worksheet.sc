import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
val x: Future[Int] = Future{
  34
}
