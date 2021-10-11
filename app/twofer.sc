import scala.collection.mutable.ListBuffer

def twofer(name: String): String = {
  val wordArray:Array[String] = name.split(" ")
  var newItem:ListBuffer[String] = new ListBuffer[String]()
  for(item<-wordArray){

    item.toLowerCase match {
      case "one" => newItem += "1"
      case "two" => newItem += "2"
      case "three" => newItem += "3"
      case "four" => newItem += "4"
      case "five" => newItem += "5"
      case "six" => newItem += "6"
      case "seven" => newItem += "7"
      case "eight" => newItem += "8"
      case "nine" => newItem += "9"
      case "ten" => newItem += "10"
      case x => newItem += x

    }
    println(item);
  }
 newItem.mkString(" ")
}

twofer("two for me one for you ")