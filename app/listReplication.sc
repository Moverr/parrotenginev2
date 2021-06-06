import scala.collection.mutable.ListBuffer
def f(num:Int,arr:List[Int]):List[Int]={
  var repeater = 0
  var ff = new ListBuffer[Int]()


  for(x <- 0 to  num){

      arr.foreach{
        x=> ff +=x
      }

  }

  /*

  val r = ff.toList
  repeater = repeater +1
  r */
  val r = ff.toList
  r.sorted
}


def fa(delim:Int,arr:List[Int]):List[Int] = {
  arr.filter{
    x=> x < delim
  }

}


val response = fa(3,List(1,2,3,5,5,6,7))
println(response)