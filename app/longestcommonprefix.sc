import scala.collection.mutable
import scala.collection.mutable.ListBuffer

var xt = Array("flower","flow","flight")
var lop = new ListBuffer[String]()

var mk:mutable.HashMap[String,Int] = new mutable.HashMap[String,Int]()

for(c<-0 until xt.length){
  var cb = xt(c)
  var bn = 0


  for(b <- 0 until xt.length){

    if(b != c){
      var wordArray = xt(b).split("")
      for(bn <- 0 until wordArray.length ){
        val substr = xt(b).substring(0,bn+1)
          println(substr)


        if(xt(c).contains(substr)){
          lop += substr
          val resp = mk.getOrElse(substr,0)

          mk.put(substr,resp+1)
          println(mk)
          //println(xt(c).contains(substr))
        }



      }
    }



  }



}

println("............")
mk.maxBy(_._2)

println(mk.values.max)
println(lop)

