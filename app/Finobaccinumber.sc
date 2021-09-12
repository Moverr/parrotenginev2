import scala.annotation.tailrec

def fib(n:Int):Int={

  def go(n:Int):Int={
    if(n<= 1) n else {
      println(n-1)
      println(n-2)

      go(n-1)
      +go(n-2)
    }

  }
go(n)
}


def fib2(n:Int) : Int = {

  var  previous:Int =0;
  var  current:Int=1;

  //val initial = 0;

  for(  initial<-0 to n-1) {
    val newFib = previous + current
    previous = current
    current = newFib
  }
  current
}



fib2(5)