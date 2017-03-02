/*
 * Copyright (C) 2007-2008 Artima, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Example code from:
 *
 * Programming in Scala (First Edition, Version 6)
 * by Martin Odersky, Lex Spoon, Bill Venners
 *
 * http://booksites.artima.com/programming_in_scala
 */

//compile this along with ../compo-inherit/LayoutElement.scala


  package com.hazza

import com.hazza.Element.elem
  
  sealed abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, 
      left: Expr, right: Expr) extends Expr
  
  class ExprFormatter {
  
    // Contains operators in groups of increasing precedence
    private val opGroups =
      Array(
        Set("|", "||"),
        Set("&", "&&"),
        Set("^"),
        Set("==", "!="),
        Set("<", "<=", ">", ">="),
        Set("+", "-"),
        Set("*", "%")
      )
  
    // A mapping from operators to their precedence
    private val precedence = {
      val assocs =
        for {
          i <- 0 until opGroups.length
          op <- opGroups(i)
        } yield op -> i
      Map() ++ assocs
    }
  
    private val unaryPrecedence = opGroups.length
    private val fractionPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element =
  
    e match {
  
      case Var(name) => 
        elem(name)
  
      case Number(num) => 
        def stripDot(s: String) = 
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s
        elem(stripDot(num.toString))
  
      case UnOp(op, arg) => 
        elem(op) beside format(arg, unaryPrecedence)
  
      case BinOp("/", left, right) => 
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if (enclPrec != fractionPrecedence) frac
        else elem(" ") beside frac beside elem(" ")
  
      case BinOp(op, left, right) => 
        val opPrec = precedence(op)
        val l = format(left, opPrec) 
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" "+ op +" ") beside r 
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }
  
    def format(e: Expr): Element = format(e, 0)
  }

  object Express {

    def main(args: Array[String]): Unit = {
      val f = new ExprFormatter

      val e1 = BinOp("*", BinOp("/", Number(1), Number(2)),
        BinOp("+", Var("x"), Number(1)))
      val e2 = BinOp("+", BinOp("/", Var("x"), Number(2)),
        BinOp("/", Number(1.5), Var("x")))
      val e3 = BinOp("/", e1, e2)

      def show(e: Expr) = println(f.format(e)+ "\n\n")

      for (e <- Array(e1, e2, e3)) show(e)
    }

  }