

 def aRecursiveFunction(aString:String,n:Int): String = {
   if(n == 0) aString
   else aString +"\n" +n+"\n"+ aRecursiveFunction(aString,n-1)
 }

 aRecursiveFunction("movers",4)


 //for x = 0; x <= 10 , x ++

 def forlooper(count:Int): String ={
   if(count * 9 == 90) s"\n$count"
   else s"$count\n" + forlooper(count+1)
 }

 //when u need loops use recursion :: 
 forlooper(0)