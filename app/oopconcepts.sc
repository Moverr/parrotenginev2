

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

 def greeting(name:String,age:Int):String= s"Hi,my name is $name and I am $age years old"

 greeting("Muyinda Rogers",33)

 val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan") withDefaultValue "Iit"
 myMap.size
 myMap.head
 myMap("ew")

 val anewMap = myMap + ("MaI"->"Blo der")

 val anno = anewMap -- List("yuio")