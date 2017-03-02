package com.hazza

/**
  * Created by hazzacheng on 17-2-1.
  */
trait FunQueue[T] {
  def head: T
  def tail: FunQueue[T]
  def append(x: T): FunQueue[T]
}


object FunQueue {

  private class QueueTmp[T] (private val leading: List[T], private val trailing: List[T])
  extends FunQueue[T] {
    def mirror =
      if (leading.isEmpty)
        new QueueTmp[T](trailing.reverse, Nil)
      else
        this

    override def head: T = mirror.leading.head

    override def tail: QueueTmp[T] = {
      val q = mirror
      new QueueTmp[T](q.leading.tail, Nil)
    }

    override def append(x: T): FunQueue[T] =
      new QueueTmp[T](leading, x :: trailing)
  }
}