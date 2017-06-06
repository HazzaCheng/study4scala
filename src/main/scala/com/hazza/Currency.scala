package com.hazza

/**
  * 使用抽象类型的例子
  */

/**
  * 货币的抽象类
  */
abstract class CurrencyZone {
  type Currency <: AbstractCurrency
  def make(x: Long): Currency
  val CurrentUnit: Currency

  abstract class AbstractCurrency {

    val amount: Long
    def designation: String

    def +(that: Currency): Currency =
      make(this.amount + that.amount)
    def *(x: Double): Currency =
      make((this.amount * x).toLong)
    def -(that: Currency): Currency =
      make(this.amount- that.amount)
    def /(that: Double): Currency =
      make((this.amount / that).toLong)
    def /(that: Currency): Double =
      this.amount.toDouble / that.amount

    def from(other: CurrencyZone#AbstractCurrency): Currency =
      make(Math.round(
        other.amount.toDouble * Converter.exchangeRate
        (other.designation)(this.designation)))

    private def decimals(n: Long): Int =
      if (n == 1) 0 else 1 + decimals(n / 10)

    override def toString =
      ((amount.toDouble / CurrentUnit.amount.toDouble)
        formatted ("%." +  decimals(CurrentUnit.amount) + "f")
        + " " + designation)
  }

}

/**
  * 货币间汇率
  */
object Converter {
  var exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0   , "EUR" -> 0.7596,
      "JPY" -> 1.211 , "CHF" -> 1.223),
    "EUR" -> Map("USD" -> 1.316 , "EUR" -> 1.0   ,
      "JPY" -> 1.594 , "CHF" -> 1.623),
    "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272,
      "JPY" -> 1.0   , "CHF" -> 1.018),
    "CHF" -> Map("USD" -> 0.8108, "EUR" -> 0.6160,
      "JPY" -> 0.982 , "CHF" -> 1.0  )
  )
}

object US extends CurrencyZone {
  abstract class Dollar extends AbstractCurrency {
    def designation = "USD"
  }

  override type Currency = Dollar

  override def make(cents: Long) = new Dollar {
    val amount = cents
  }

  val Cent = make(1)
  val Dollar = make(100)
  val CurrentUnit = Dollar
}

object Europe extends CurrencyZone {
  abstract class Euro extends AbstractCurrency {
    def designation = "EUR"
  }

  override type Currency = Euro

  override def make(cents: Long) = new Euro {
    val amount = cents
  }

  val Cent = make(1)
  val Euro = make(100)
  val CurrentUnit = Euro
}

object Japan extends CurrencyZone {
  abstract class Yen extends AbstractCurrency {
    def designation = "JPY"
  }

  override type Currency = Yen

  override def make(cents: Long) = new Yen {
    val amount = cents
  }

  val Yen = make(1)
  val CurrentUnit = Yen
}

object testCurrency {
  def main(args: Array[String]): Unit = {
    val jpy = Japan.Yen from US.Dollar * 100
    val usd = US.Dollar from Europe.Euro * 100
    println(jpy)
    println(usd)
    println(jpy from usd)
    println(Europe.Euro from (jpy from usd))
  }
}
