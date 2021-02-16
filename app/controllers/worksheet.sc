import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
val x: Future[Int] = Future{
  34
}
x
x.map(y=>y + 0.34 )


def getTable(num: Int): Future[List[Int]] = Future{
  if(num == 0){
    List.empty[Int]
  } else {
    (1 to 10).toList.map(_ * num)
  }
}
//flatMap call on future
val flatMapMagic: Future[List[Int]] = getTable(0) flatMap {
  case Nil => Future.failed(throw new RuntimeException("argument shall not be zero"))
  case table => Future.successful(table)
}

