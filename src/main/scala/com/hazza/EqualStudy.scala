package com.hazza

import scala.collection.immutable.HashSet

/**
  * Created by hazzacheng on 17-3-12.
  * equals函数要满足自反射,对称的,可传递的,一致的,对任何非空值x.equals(null)返回false
  */

class ValPoint(val x: Int, val y: Int){
  //完全错误的equals定义
  //因为Point类的equals方法以point而不是Any作为参数，它并没有重写Any类中的equals方法。
  //目前，Scala和Java中的重载都是根据参数的静态类型，而不是运行期的类型来解析，因此只要是Point类型，则调用Point的equals的方法
  //一旦静态的参数是Any类型的,则调用的就是Any的equals方法
  //所以HashSet的contains方法返回false，由于这个方法操作的是泛型的集合，它调用的是Object类的equals方法而不是Point中的重载的变种
  def equals1(other: ValPoint): Boolean =
    this.x == other.x && this.y == other.y

  //更好的一些定义，但并不完美,HashSet的contains依然返回false(没有加hashcode的情况)
  //因为Point类重新定义了equals,但没有重新定义hashcode
  override def equals(other: scala.Any): Boolean = other match {
    case that: ValPoint => (that canEqual this) && (this.x == that.x) && (this.y == that.y)
    case _ => false
  }

  override def hashCode(): Int = 41 * (41 + x) + y

  def canEqual(other: Any) = other.isInstanceOf[ValPoint]

}

//由例子可以看出当对象的一个值改变后，集合是个不包含p的集合，但p却在集合的元素中
//如果equals和hashcode依赖于可变状态,对于潜在的用户会带来问题
//如果需要的比较牵扯到对象当前的状态，你通常应该取别的名字，而不是equals
//并且讲相等性判断的方法命名为equalsContent或别的不同于equals的名字
//如此以来，Point就继承了默认的equals和hashCode实现，而p就能在它的x字段被修改后继续在coll中被定位到
class VarPoint(var x: Int, var y: Int){
  def equals1(other: ValPoint): Boolean =
  this.x == other.x && this.y == other.y

  override def equals(other: scala.Any): Boolean = other match {
    case that: ValPoint => this.x == that.x && this.y == that.y
    case _ => false
  }

  override def hashCode(): Int = 41 * (41 + x) + y

}

object Color extends Enumeration {
  val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
}

class ColoredPoint(x: Int, y: Int, val color: Color.Value) extends ValPoint(x, y) {
  override def equals(other: Any): Boolean = other match {
    case that: ColoredPoint => (that canEqual that) && (this.color == that.color) && super.equals(that)
    case _ => false
  }

  override def canEqual(other: Any): Boolean = other.isInstanceOf[ColoredPoint]
}

object EqualStudy {

  def main(args: Array[String]): Unit = {
    val p1, p2 = new ValPoint(1, 2)
    val q = new ValPoint(2, 3)
    println(p1 equals p2)
    println(p1 equals q)
    val coll1 = HashSet(p1)
    println(coll1 contains p2)
    val p2a: Any = p2
    println(p2a equals p1)
    println()

    val p3 = new VarPoint(1, 2)
    val coll2 = HashSet(p3)
    p3.x += 1
    println(coll2 contains p3)
    println(coll2 contains p3)
    println(coll2.iterator contains p3)
    println()

    val redp = new ColoredPoint(1, 2, Color.Red)
    val bluep = new ColoredPoint(1, 2, Color.Blue)
    println(p1 equals redp)
    println(redp equals p1)
    println(redp == p1)
    println(p1 == bluep)
    println(redp == bluep)
    println()

    val pAnon = new ValPoint(1, 1) {override val y = 2 }
    println(p1 equals pAnon)
    val coll3 = List(p1)
    println(coll3 contains p1)
    println(coll3 contains redp)
    println(coll3 contains pAnon)





  }

}
