import scala.collection.mutable.ListBuffer
import scala.io.StdIn

def solution(): String ={
  val item:String = "pprrqq";
  val stringArray = item.split("")

  val pt = StdIn.readLine();


  def go(input:String,index:Int=0,cleanedArray:ListBuffer[String]): String ={


    var xt:ListBuffer[String] = cleanedArray
    if(xt == null){
      xt = new ListBuffer[String]();
    }

    if(index == input.length){
      xt.mkString("")
    }else{
      val charItem = input(index)
      if(!xt.mkString("").contains(charItem)) {
        xt  += charItem.toString
      }

      go(input,index+1,xt)
    }

  }
  go(item,0,null)
}

solution()