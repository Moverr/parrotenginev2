val myNumbers = Array(1,2,3,4,5,6,7)
myNumbers.map(_ * 45 )

myNumbers.foreach{
  x=>println
}

// scalability
// concurrency
// distribution
myNumbers.reverse
myNumbers.fold(1)( (a,b)=>a*b )

myNumbers.foldLeft(1)( (a,b)=>a+b )
myNumbers.foldRight(1)( (a,b)=>a*b/2 )

myNumbers.sorted

myNumbers.exists(x=> x == 13)

myNumbers.findLast(x=>x%2==0)