package com.hazza.algorithm

import scala.util.Random

/**
  * Created by hazza on 5/29/17.
  */
object InsertSort {
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xs1 => insert(x, isort(xs1))
  }

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
  }

  def main(args: Array[String]): Unit = {
    var list: List[Int] = List()

    while (list.length < 20)
      list = list ::: List((new Random()).nextInt(100))

    isort(list).foreach(println)
  }


}
