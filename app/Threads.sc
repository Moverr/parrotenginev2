val ap = new Thread(new Runnable {
   override def run(): Unit = println("Gracious Good")
 })


ap.start()
val xko = ap.getName()
