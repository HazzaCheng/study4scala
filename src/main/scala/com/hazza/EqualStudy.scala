package com.hazza

import scala.collection.immutable.HashSet

/**
  * Created by hazzacheng on 17-3-12.
  */

class Point(val x: Int, val y: Int){
  //完全错误的equals定义
  //因为Point类的equals方法以point而不是Any作为参数，它并没有重写Any类中的equals方法。
  //目前，Scala和Java中的重载都是根据参数的静态类型，而不是运行期的类型来解析，因此只要是Point类型，则调用Point的equals的方法
  //一旦静态的参数是Any类型的,则调用的就是Any的equals方法
  //所以HashSet的contains方法返回false，由于这个方法操作的是泛型的集合，它调用的是Object类的equals方法而不是Point中的重载的变种
  def equals1(other: Point): Boolean =
    this.x == other.x && this.y == other.y

  //更好的一些定义，但并不完美,HashSet的contains依然返回false
  override def equals(other: scala.Any): Boolean = other match {
    case that: Point => this.x == that.x && this.y == that.y
    case _ => false
  }

}

object EqualStudy {

  def main(args: Array[String]): Unit = {
    val p1, p2 = new Point(1, 2)
    val q = new Point(2, 3)
    
    println(p1 equals p2)
    println(p1 equals q)
    val coll = HashSet(p1)
    println(coll contains p2)
    val p2a: Any = p2
    println(p2a equals p1)





  }

}
