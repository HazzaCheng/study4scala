package com.hazza.test

import java.io.File

/**
  * Created by hazza on 7/12/17.
  */
object handler {
  trait Foo
  trait Bar
  object Foo {
    implicit def fooToBar(foo: Foo) = new Bar {}
  }
  object Bar {
    implicit def fooToString(foo: Foo) = "Foo"
  }
}

object test {
  import handler._

  def bar(x: Bar) = println("Bar")
  def str(x: String) = println(x)

  def main(args: Array[String]): Unit = {
    val x = new Foo {}
    bar(x)
  }
new File()
}
