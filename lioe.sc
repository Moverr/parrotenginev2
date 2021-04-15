
val x = Array[Int](1,2,1,3,4,6)
val b = x.toSet.toList.sorted

var p = 1;
var c = b(b.length-1)
for(x <-0 until(b(b.length-1))){

if(!b.contains(p)) {
  println("1111111")
 println(p)

}
  if(!b.contains(c)) {
    println("2222222")
    println(c)

  }

  p += 1
  c -= 1
}
println(p)