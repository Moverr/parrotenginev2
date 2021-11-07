

 val aThread = new Thread(new Runnable {
   override def run(): Unit = println("Running Parralel")

 })

 aThread.start()
 // create a JVMthread => OS thread