


 def add(x:Int)=(y:Int)=>x*y

//todo; work with apply what is apply and move on
 add(90)(2)



 def makeUpper(xs: List[String]) =
   xs map {
   x=>x.toUpperCase()
   }

 def makeWhatEverYouLike(xs: List[String], sideEffect: String => String) ={
   xs map sideEffect
 }

 makeUpper(List("abc", "xyz", "123"))

 makeWhatEverYouLike(List("abc", "xyz", "123"),x=>x.concat("er"))

 val myName = (name: String) => s"My name is $name"
 makeWhatEverYouLike(List("John", "Mark"), myName)

 val xt = List("Scala", "Erlang", "Clojure") map (_.length)
 xt.reduceLeft((a,b)=>{
   if(a %3 == 0 ){
     a+b+89
   }else{
     a+b+2
   }
 })

 val nums = List(1,2,3,5,6,7,8,9)
 nums.reduceLeft(_ min(_))

 def blond(that: Int)(thisa:Int=0): Int = math.min(thisa, that)
 val rp = blond(89)(434)

 val ko =  blond(45)(7)


 makeWhatEverYouLike(List("ABC", "XYZ", "123"), x => x.toLowerCase)
