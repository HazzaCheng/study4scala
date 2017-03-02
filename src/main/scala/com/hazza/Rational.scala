package com.hazza

class Rational(n: Int, d: Int) extends Ordered[Rational] {
  require(d != 0)

  override def compare(that: Rational): Int = {
    return (this.numer * that.denom) - (this.denom * that.numer)
  }


  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def + (i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def - (that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def - (i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def * (i: Int): Rational =
    new Rational(numer * i, denom)

  def / (that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def / (i: Int): Rational =
    new Rational(numer, denom * i)

  override def toString = numer +"/"+ denom

  private def gcd(a: Int, b: Int): Int = 
    if (b == 0) a else gcd(b, a % b)

  override def equals(other: Any): Boolean =
    other match {
      case that: Rational =>
        (that canEqual this) && numer == that.numer && denom == that.denom
      case _ => false
    }

  def canEqual(other: Any): Boolean =
    other.isInstanceOf[Rational]

  override def hashCode: Int =
    41 * (41 + numer) + denom
}

object Main {
  def main(args: Array[String]) {
    val x = new Rational(2, 3)
    println("x [" + x + "]")
    println("x * x [" + (x * x) + "]")
    println("x * 2 [" + (x * 2) + "]")

    implicit def intToRational(x: Int) = new Rational(x)
    val r = new Rational(24,9)
    println("x [" + r + "]")
    println("2 * r [" + (2 * r) + "]")
    println(r < x)
  }
}

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int

  lazy val numer = numerArg / g
  lazy val denom = denomArg / g

  override def toString: String = numer + "/" + denom

  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}