package com.hazza

/**
  * Created by hazzacheng on 17-2-28.
  */

class PreferredPrompt(val preference: String)
class PreferredDrink(val preference: String)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink): Unit ={
    println("Welcome, " + name + ". The System is ready.")
    print("But while you work, ")
    println("Why not enjou a cup of " + drink.preference + "?")
    println(prompt.preference)
  }
}

object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master-> ")
  implicit val drink = new PreferredDrink("tea")
}

object ImplicitStudy {
  implicit def intToString(i: Int) = i.toString

  implicit def doubleToInt(d: Double) = d.toInt

  def maxListUpBound[T <: Ordered[T]](elements: List[T]): T =
    elements match {
      case List() => throw new IllegalArgumentException("Empty List!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListUpBound(rest)
        if (x > maxRest) x
        else maxRest
    }

  def maxListImpParm[T](elements: List[T])(implicit orderer: T => Ordered[T]): T =
    elements match {
      case List() => throw new IllegalArgumentException("Empty List!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListImpParm(rest)(orderer)
        if (orderer(x) > maxRest) x
        else maxRest
    }

  def maxList[T <% Ordered[T]](elements: List[T]): T =  //视界
    elements match {
      case List() => throw new IllegalArgumentException("Empty List!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest)  //隐式转换出(orderer)
        if (x > maxRest) x  //隐式转换出orderer(x)
        else maxRest
    }

  def main(args: Array[String]): Unit = {
    val num: Int = 123
    println(num replace("2", "4"))

    val i: Int = 3.5
    println(i)

    import JoesPrefs._
    Greeter.greet("HazzaCheng")

    val nums: List[Int] = List(132, 213, 21231, 22, 21, 2, 1)
    val strs: List[String] = List("sad", "aa", "vd", "klj")
//    println(maxListUpBound(nums))
//    println(maxListUpBound(strs))
    println(maxListImpParm(nums))
    println(maxListImpParm(strs))
  }
}
