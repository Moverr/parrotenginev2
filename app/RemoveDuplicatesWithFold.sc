def solution(unCleantext:List[Int]): String ={

  //fold uses a loop which is not a good functional approach
  val cleaned = unCleantext.foldLeft("")((res,char)=> if(res.contains(char)) res else res+char )
  cleaned
}
val teststring  = List(1,5,2,3,4,5,6,7)
val result:String = solution(teststring)


//polygomous functions and move on
//poly functions  ..
//mono functions ..