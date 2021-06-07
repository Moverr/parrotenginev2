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


def fa(delim:Int,arr:List[Int]):Int= {
  var ff = new ListBuffer[Int]()

 arr.filter{
   x=>  x%2 == 1
 }.sum

}


val response = fa(3,List(11,25,18,-1,26,-20,-19,23,-24,-8))
println(response)