val numbers = List(1,2)

//todo; simple pattern matching

val bools = Seq(true,false)

for(bool <- bools){
  bool match {
    case true => println("Damn true")
    case false => println("Damn false")
  }
}

for{
  x <- Seq(1,2,3,4,3.4,"One","Two")
}{
  val str = x match {
    case Seq(1,2,3)=> "Interesting Scenario"
    case 1 => "Int One"
    case _:Int|_:Double =>"Other fgf  : "+x
    case set:Double => "Un expected value : "+set
    case _ => "Anything else "+x
  }
  println(str)
}

//val description = numbers match {
//  case ::(head, next) => ???
//  case Nil => ???
//}



def seqToString[T](seq: Seq[T]): String = seq.foreach{
  x => x




  case head +: tail => {

   // println(head)
    println(tail)
    println("----------------------")
    s"$head +: " + seqToString(tail)
  }
  case Nil => "Nil"
}


seqToString(Seq(1,2,3,4))
