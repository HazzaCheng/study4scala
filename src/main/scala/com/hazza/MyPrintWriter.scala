package com.hazza

import java.io.{File, PrintWriter}


/**
  * 一个使用柯里化的例子
  */
class MyPrintWriter {
  def withPrintWriter(file: File)(op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
}

object MyPrintWriter {
  def main(args: Array[String]): Unit = {
    val myPrintWriter = new MyPrintWriter
    val file = new File("date.txt")
    myPrintWriter.withPrintWriter(file){
      writer => writer.println(new java.util.Date)
    }
  }
}
