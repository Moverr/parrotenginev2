import scala.annotation.tailrec

def findEqualityBetween[T](b:T) = {
  (x: T) => x==b
}


//function within a function
def moe(b:Int) ={
  (x:Int) => x==b
}

moe(2){
  (3)
}

//polymorphic functions are called generic functions
def findFirst[T](ss:Array[T],P:T => Boolean ):Int={

  @tailrec
  def go(n:Int):Int={
  if(n>=ss.length) -1
  else if (P(ss(n))) n
  else  go(n+1)
  }
  go(0)
}


//find first existence of an element in an array
val x = Array("mi","c","polse","lsoee","below")

// using literal functions or anonymous functions
// high order function and polymorphic/generic functions
val b = findFirst(x,(x:String)=> x == "below")

//val b = findFirst(x,findAEqualityB(x))



//def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
//  @annotation.tailrec
//  def iter(i: Int): Boolean = {
//    if (i >= as.length - 1) true
//    else !gt(as(i), as(i + 1)) && iter(i + 1)
//  }
//  iter(0)
//}
//
//

//isSorted(x)