import scala.annotation.tailrec

def factorial(n:Int): Int = {

  @tailrec
  def go(n:Int, acc:Int):Int={
    if(n <= 0) acc else go(n-1,n*acc)
  }
  go(n,1)

}

factorial(4)
//accumulator ::
4*1
3*4
2*12
1*24

//todoloop thorugh the numbeers 10

//writing loops withouut mutating the loop vriable
//todo: do recursion to quickly sort an array .

def sumArrayusingRecursion(n:Array[Int]): Int ={

  @annotation.tailrec
  def go(n:Array[Int],index:Int,acc:Int): Int ={

    if(index == n.length-1) {
      acc
    }else{
      go(n,index +1,acc+n(index))
    }

  }
  go(n,0,0)
}
val x = Array(1,2,4,5,6,7,123,434,3443,34564,1245,100)
val pop = sumArrayusingRecursion(x)
