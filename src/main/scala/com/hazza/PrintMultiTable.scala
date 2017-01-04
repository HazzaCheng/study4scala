package com.hazza

/**
  *一个内建控制结构，将指令式风格转变为函数式风格的例子。
  */
class PrintMultiTable {
  def makeRowSeq(row: Int) =
    for (col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }

  def makeRow(row: Int) = makeRowSeq(row).mkString

  def multiTable() = {
    val tableSeq =
      for (row <- 1 to 10)
        yield makeRow(row)

    tableSeq.mkString("\n")
  }
}

object PrintMultiTable {
  def main(args: Array[String]): Unit = {
    val printMultiTable = new PrintMultiTable()
    println(printMultiTable.multiTable())
  }
}
