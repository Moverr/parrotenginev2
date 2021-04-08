
val x = List("juice","mango")
val y = List("apples", "oranges", "pears")
val z = List("apples", "oranges", "pears")
val fruit: List[List[String]] = List(x,z,y)
// List(-1, 1, 0),
val p = List(List(-1,  -1,2),  List(-1, 0, 1), List(-1, 0, 1),  List(0, 1, -1),  List(1, -1, 0))

val xpp = List(2, -1, -1)
val aex = p.find(x=>x.sorted == xpp.sorted)
aex

p.foreach{
  x=> if(x.reverse.contains(xpp.reverse)){  true}
}

val xe = p.distinct


val xpp = List(1,-1,-1,0).toSet.toList.length

