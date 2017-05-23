package com.hazza

import scala.io.Source

/**
  * Created by hazza on 5/23/17.
  */
object LengthAdapter {


  def getLinesWithLength(filePath: String) = {
    def widthOfLength(s: String) = s.length.toString.length
    def getFileLines(filePath: String) = Source.fromFile(filePath).getLines().toList

    val lines = getFileLines(filePath)
    val longestLine = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)
    val maxWidth = widthOfLength(longestLine)
    val res = for (line <- lines) yield {
      val numSpace = maxWidth - widthOfLength(line)
      val padding = " " * numSpace
      padding + line.length + " | " + line
    }
    res
  }
  def main(args: Array[String]): Unit = {
    getLinesWithLength("/home/hazza/derby.log").foreach(println)
  }
}
