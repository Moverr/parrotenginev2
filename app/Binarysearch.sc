import scala.annotation.tailrec

def binarySearch(x:Array[Int],y:Int): Int ={

  var start = 0
  var end = x.length - 1;
  while (start <= end){
    val mid:Int = ((start + end) /2)
    if(y == x(mid)) {
      return mid
    }else if(y<x(mid)){
      end = mid - 1
    }
    else{
    start = mid + 1
    }
  }

  -1
}

def recursiveBinarySearch(x:Array[Int],y:Int):Option[Int]={

  @tailrec
  def go(x:Array[Int],y:Int,start:Int,end:Int): Option[Int] ={

     val mid = (start + end) /2
     if(y == x(mid)){
        Some(mid)
     }else if(y < x(mid)){
       go(x,y,start,mid-1)
     }else  if(y > x(mid)){
       go(x,y,mid+1,end)
     }
     else{
       None
     }




   }
  go(x,y,0,x.length)
}
//reducing usingn recursively.
//exit point. if we find the element in the array

////linear search
//
val x = Array(1,2,3,4,34,232,223,443,1,334,678,54)
// find if element exists in the array
val result = recursiveBinarySearch(x.sorted,232).get

//
//for(y<-0 until (x.length,1)  ){
//
//  println(y)
//}
//
//
// var xt = 0;
//do{
//  println(xt)
//  xt = xt +1
//}while (xt < x.length)
//
////todo : binary search
///*
//  take a scenario A = 2,6,13,21,36,47,63,81,97
//  if x= 13
//
//  case 1 : x == A[mid]
//  case 2 : x <  A[mid]
//  case 3 : x >  A[mid]
//  // search space.
//  mid = start +  end / 2 will be 1
// */