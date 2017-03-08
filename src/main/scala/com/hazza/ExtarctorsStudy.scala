package com.hazza

import scala.util.matching.Regex

/**
  * Created by hazzacheng on 17-3-8.
  */
object ExtarctorsStudy {

  def userTwiceUpper(s: String) = s match {
    case Email(Twice(x @ UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ =>
      "no match"
  }

  def isHazzaInDotCom(target: String, s: String): Boolean = s match {
    case Email("Hazza", Domain("com", _*)) => true
    case _ => false
  }

  def main(args: Array[String]): Unit = {
    val x: Any = "hazzacheng@gmail.com"
    x match {
      case Email(user, domain) => println("extract successfully!")
      case _ => println("extract failed")
    }

    val obj = "hazzacheng@gmail.com"
    Email.unapply(obj) match {
      case Some((u, d)) => println(Email.apply(u, d))
    }

    val str = "HAHA"
    str match {
      case Twice(s) => println("extract successfully!")
      case _ => println("extract failed")
    }

    val s = "HA"
    s match {
      case UpperCase() => println("extract successfully!")
      case _ => println("extract failed")
    }

    println(userTwiceUpper("HAHA@gmail.com"))
    println(userTwiceUpper("HAV@gmail.com"))
    println(userTwiceUpper("haha@gmail.com"))

    println(isHazzaInDotCom("Hazza", "Hazza@gmail.com"))
    println(isHazzaInDotCom("Hazza", "Hazza@suda.edu.cn"))
    println(isHazzaInDotCom("Hazza", "Nike@gmail.com"))

    val email = "hazza@suda.edu.cn"
    val ExpandedEMail(name, topdom, subdoms @ _*) = email
    println(name + " " + topdom + " " + subdoms)

    //正则表达式
    val Decimal = new Regex("""(-)?(\d+)(\.\d*)?""")
    //或者是　val Decimal = """(-)?(\d+)(\.\.d*)?""".r
    val input = "for -1.0 to 99 by 3"
    for (Decimal(sign, integer, decimal) <- Decimal findAllIn input)
      println("sign: " + sign + " integer: " + integer + " decimal: " + decimal)


  }
}

class Email(user: String, domainL: String) {}
class Twice(s: String)
class UpperCase()
//class ExpandedEMail(name: String, domain: String*)

//利用元祖绑定２-N个对象
object Email {
  def apply(user: String, domain: String) = user + '@' + domain

  def unapply(arg: String): Option[(String, String)] = {
    val parts = arg split '@'
    if (parts.length == 2)
      Some(parts(0), parts(1))
    else
      None
  }
}

//绑定一个对象
object Twice {
  def apply(s: String): String = s + s
  def unapply(arg: String): Option[String] = {
    val length = arg.length / 2
    val half = arg.substring(0, length)
    if (half == arg.substring(length))
      Some(half)
    else
      None
  }
}

//利用布尔值绑定一个０个对象
object UpperCase {
  def unapply(arg: String): Boolean =
    arg.toUpperCase == arg
}

//变参抽取器
object Domain {
  def apply(parts: String*): String =
    parts.reverse.mkString(".")

  def unapplySeq(whole: String): Option[Seq[String]] =
    Some(whole.split("\\.").reverse)
}


object ExpandedEMail {
  def unapplySeq(email: String): Option[(String, Seq[String])] = {
    val parts = email split "@"
    if (parts.length == 2)
      Some(parts(0), parts(1).split("\\.").reverse.toSeq)
    else
      None
  }
}

