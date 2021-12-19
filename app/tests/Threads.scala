import java.util.concurrent.Executors

import tables.Account

object Two extends App{

  /*
  The producer comsumer problem
  two threads running
  producer --> setting a value
  consumer --> extract the value from the container
  the consumer needs to wait the producer to finish its Job ...
  // in parralel applicatiton. we need to wait.,,,
   */

  class SimpleContainer{
    private  var value:Int  = 0
    def isEmpty:Boolean=value == 0

    def set(newValue:Int): Unit ={
      value = newValue
    }

    def get: Int ={
      val result = value
      value = 0
      result
    }
  }

  def  naiveProdCons(): Unit ={
    val container = new SimpleContainer

    val comsumer = new Thread(()=>{
       println("[Consuumer] waiting ....")
      while(container.isEmpty == true){
        println("[Consumer] actively waiting")
      }
      println("[consumer] I have consumed"+container.get)
    })

    val  producer = new Thread(()=>{
      println("[producer] computing...")
      Thread.sleep(500)
      val value = 42
      println("[Producer] I have produced, after a long work, the value "+value)
      container.set(value)

    })

    comsumer.start()
    producer.start()
  }

//  naiveProdCons()

  def smartProdCons(): Unit ={
    val container = new SimpleContainer
    val consumer = new Thread(()=>{
      println("[Consumer] waiting...")
      container.synchronized{
        container.wait()
      }

      println("[Consumer]  I have consumed "+container.get)
    })

    val producer = new Thread(()=>{
      println("[Producer] Hard at work...")
      Thread.sleep(2000)
      val  value = 42
      container.synchronized{
        println("[Producer] I'm producing "+value)
        container.set(value)
        container.notify()
      }
    })


    consumer.start()
    producer.start()


  }
  smartProdCons()
}


//sleep is a wrong fallacy.. set time out practice

//linked list to improve the time out andn joining of threads 