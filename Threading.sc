


 val aThread = new Thread(new Runnable {
   override def run(): Unit = println("Velocity")
 })




 val threadable = new Thread(() => (1 to 5).foreach(_ => println("Hello ")));



 val threadable2 = new Thread(() => (1 to 5).foreach(_ => println("Good Bye ")));

 threadable2.start()


 threadable.start()