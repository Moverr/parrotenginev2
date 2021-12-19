import scala.collection.mutable
import scala.util.Random

object EnhancedPD extends  App {



  class Consumer(id:Int,buffer:mutable.Queue[Int]) extends Thread{

    override def run(): Unit = {
      val random = new Random()
      while(true){
        buffer.synchronized{
          /*
          SCENARIO: Imagine a scenario , producers producces a value, annd two consumers are waiting
          notifies one consumer.
           */

          while (buffer.isEmpty){
            println(s"[Consumer $id] buffer empty, waiting...")
            buffer.wait()
          }
          val x = buffer.dequeue()
          println(s"[cosumer $id] consumed "+x)
          buffer.notify()
        }


        Thread.sleep(random.nextInt(500))
      }
    }
  }

  class Producer(id:Int,buffer:mutable.Queue[Int],capacity:Int) extends Thread{

    override def run(): Unit = {
      val random = new Random()
      var i:Int = 0;
      while(true){
        buffer.synchronized{

          while (buffer.size == capacity){
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
    }
  }


  def multiProdCons(nConsumers:Int,nProducers:Int): Unit ={
    val buffer:mutable.Queue[Int] = new mutable.Queue[Int]()
    val capacity:Int = 5;
    (1 to nConsumers).foreach((i)=>{
      new Consumer(i,buffer).start()
    })


    (1 to nProducers).foreach((i)=>{
      new  Producer(i,buffer,capacity).start()
    })


  }
  multiProdCons(0,2)

}
