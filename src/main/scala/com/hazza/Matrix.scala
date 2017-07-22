package com.hazza

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
  * Created by hazza on 7/22/17.
  */
class Matrix(private val repr: Array[Array[Double]]) {
  def row(idx: Int): Seq[Double] = {
    repr(idx)
  }

  def col(idx: Int): Seq[Double] = {
    repr.foldLeft(ArrayBuffer[Double]()) {
      (buffer, currentRow) =>
        buffer.append(currentRow(idx))
        buffer
    }.toArray
  }

  lazy val rowRank = repr.length
  lazy val colRank = if (rowRank > 0) repr(0).length else 0

  override def toString: String = "Matrix" + repr.foldLeft("") {
    (msg, row) => msg + row.mkString("\n|", " | ", "|")
  }
}

trait ThreadStrategy {
  def execute[A](func: Function0[A]): Function0[A]
}

trait SameThreadStrategy extends ThreadStrategy {
  override def execute[A](func: () => A) = func
}

object MatrixUtils {
  def multiply(a: Matrix, b: Matrix)(implicit threading: ThreadStrategy): Matrix = {
    assert(a.colRank == b.rowRank)
    val buffer = new Array[Array[Double]](a.rowRank)
    for (i <- 0 until a.rowRank) buffer(i) = new Array[Double](b.colRank)

    def computeVal(row: Int, col: Int): Unit = {
      val pairElements = a.row(row).zip(b.col(col))
      val products = for ((x, y) <- pairElements) yield x * y
      val result = products.sum
      buffer(row)(col) = result
    }

    val computions = for {
      row <- 0 until a.rowRank
      col <- 0 until b.colRank
    } yield threading.execute(() => computeVal(row, col))
    computions.foreach(_())

    new Matrix(buffer)
  }
}