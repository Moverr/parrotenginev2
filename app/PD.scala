import scala.collection.mutable
import scala.util.Random

object PD extends App {
  prodConsLargeBuffer()

  def prodConsLargeBuffer(): Unit ={
    val buffer:mutable.Queue[Int] = new mutable.Queue[Int]()
    val capacity:Int = 3

    val consumer = new Thread(()=>{
      val random = new Random()
      while(true){
        buffer.synchronized{
          if(buffer.isEmpty){
            println("[Consumer] buffer empty, waiting...")
            buffer.wait()
          }
          val x = buffer.dequeue()
          println("[cosumer] consumed "+x)
          buffer.notify()

        }


        Thread.sleep(random.nextInt(2))
      }
    })


    val producer = new Thread(()=>{
      val random = new Random()
      var i:Int = 0;
      while(true){
        buffer.synchronized{

          if(buffer.size == capacity){
            println("[Producer] Buffer is full,waiting...  ")
            buffer.wait()
          }

//          there must be ateleast one empty space  in the buffer
          println("[Producer] Producing  ")
          buffer.enqueue(i)
          buffer.notify()

          i = i +1;


        }


        Thread.sleep(random.nextInt(500))
      }
    })

    consumer.start()
    producer.start()

  }
}
