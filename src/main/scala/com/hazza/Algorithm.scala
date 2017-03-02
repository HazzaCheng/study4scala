package com.hazza

/**
  * Created by hazzacheng on 17-2-1.
  */
object Algorithm {

  def orderedMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    val n = xs.length / 2
    if (n ==0) xs
    else {
      val (zs, ys) = xs splitAt n
      merge(orderedMergeSort(ys), orderedMergeSort(zs))
    }
  }

  def main(args: Array[String]): Unit = {
    val persons = List(
      new Person("Cheng", "Mulei"),
      new Person("Hazza", "Feng"),
      new Person("Cheng", "Feng"),
      new Person("A", "Man")
    )

    println(Algorithm.orderedMergeSort(persons))
  }

}

class Person(val firstName: String, val laseName: String) extends Ordered[Person] {
  override def compare(that: Person): Int = {
    val lastNameComparison = laseName.compareToIgnoreCase(that.laseName)
    val firstNameComparison = firstName.compareToIgnoreCase(that.firstName)
    if (firstNameComparison != 0)
      firstNameComparison
    else
      lastNameComparison
  }

  override def toString: String = firstName + " " + laseName
}
