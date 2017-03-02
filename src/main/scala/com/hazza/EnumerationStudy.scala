package com.hazza

/**
  * Created by hazzacheng on 17-2-28.
  */

object Direction extends Enumeration {
  val North = Value("N")
  val East = Value("E")
  val West = Value
  val South = Value
}

object EnumerationStudy {

  def main(args: Array[String]): Unit = {
    println(Direction(0))
    println(Direction.East)
    println(Direction(2))
    println(Direction(3))
  }
}
