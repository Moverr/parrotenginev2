package tuts.concurrency

class Intro extends App {

  val aThread = new Thread(
    new Runnable {
      override def run(): Unit = println("A Thread in SCala")
    }
  )

  aThread.start()

}
