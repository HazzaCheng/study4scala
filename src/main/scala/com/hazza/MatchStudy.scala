package com.hazza

/**
  * Created by hazzacheng on 17-2-28.
  */
object MatchStudy {
  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  def main(args: Array[String]): Unit = {

  }
}
