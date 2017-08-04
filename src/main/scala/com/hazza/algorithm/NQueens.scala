package com.hazza.algorithm

/**
  * Created by hazzacheng on 17-3-2.
  */
object NQueens {
  def solveNQueens(n: Int): List[List[String]] = {
    generateBoard(queens(n), n)
  }

  def queens(n: Int): List[List[(Int, Int)]] = {
    def placeQueens(k: Int): List[List[(Int, Int)]] = {
      if (k == 0) List(List())
      else
        for {
          queens <- placeQueens(k - 1)
          column <- 1 to n
          queen = (k, column)
          if isSafe(queen, queens)
        } yield queen :: queens
    }

    placeQueens(n)
  }

  def isSafe(queen: (Int, Int), queens: List[(Int, Int)]) =
    queens forall(q => !inCheck(queen, q))

  def inCheck(queen1: (Int, Int), queen2: (Int, Int)) =
    queen1._1 == queen2._1  ||  //same row
      queen1._2 == queen2._2  ||  //same line
      (queen1._1 - queen2._1).abs == (queen1._2 - queen2._2).abs

  def generateBoard(lists: List[List[(Int, Int)]], n: Int): List[List[String]] = {
    var res: List[List[String]] = List.empty[List[String]]
    for (queens <- lists) {
      val board = new Array[Array[Char]](n)
      for (i <- 0 until  n) {
        board(i) = new Array[Char](n)
        for (j <- 0 until n)
          board(i)(j) = '.'
      }
      queens.foreach(q => board(q._1 - 1)(q._2 - 1)='Q')
      val queensStr: List[String] = board.map(row => String.valueOf(row)).toList
      res = queensStr :: res
    }

    res
  }

  def main(args: Array[String]): Unit = {
    val num = 80
    val queens = solveNQueens(num)
    println(num + "Queens has " + queens.size + " solutions.")
    queens.foreach(q => {q.foreach(println);println()})
  }

}
