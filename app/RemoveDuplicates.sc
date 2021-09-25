import scala.annotation.tailrec


def solution(unCleantext:String): String ={
  def fingo(string: String,character: String): Boolean ={
    string.contains(character)

  }
   //todo; create a list bugger. and keep adding as long as there not exist item in it
  @tailrec
   def go(stringarray:Array[String],cleanedString:String="",index:Int = 0):String={
      if(index == stringarray.length)  cleanedString
      else{

         if(!cleanedString.contains(stringarray(index))){
           val newString:String = cleanedString+stringarray(index)
            go(stringarray,newString,index+1)
         }  else{
            go(stringarray,cleanedString,index+1)
         }


      }
   }
   go(unCleantext.split(""))
}
val teststring  = "ccbabacc"
val result:String = solution(teststring)
