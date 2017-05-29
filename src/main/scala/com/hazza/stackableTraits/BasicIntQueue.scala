package com.hazza.stackableTraits

/**
  * 展示了如何使用特质的可堆叠的特性
  */


import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) { buf += x }
}

object Main extends {
  def main(args: Array[String]): Unit = {
    class MyQueue extends BasicIntQueue with Doubling
    val queue1 = new MyQueue
    queue1.put(10)
    println(queue1.get())
    println()

    val queue2 = new BasicIntQueue with Doubling with Incrementing with Filtering
    queue2.put(-1)
    queue2.put(0)
    queue2.put(1)
    println(queue2.get())
    println(queue2.get())
    println()

    val queue3 = new BasicIntQueue with Doubling with Filtering with Incrementing
    queue3.put(-1)
    queue3.put(0)
    queue3.put(1)
    println(queue3.get())
    println(queue3.get())
    println(queue3.get())
    println()
  }
}
