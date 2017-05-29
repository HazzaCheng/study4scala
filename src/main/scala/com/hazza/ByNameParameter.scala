package com.hazza

/**
  * 一个传名参数的例子
  */
object ByNameParameter {
  //断言被禁用
  var assertionEnabled = true

  //通过传名函数判断
  def byNameAssert(predicate: => Boolean) =
    if (assertionEnabled && !predicate)
      throw new AssertionError

  //通过布尔值传值判断
  def byValueAssert(predicate: Boolean) =
    if (assertionEnabled )
      throw new AssertionError

  def main(args: Array[String]): Unit = {
    assertionEnabled = true

    byNameAssert(5 / 0 == 0)  //编译不会产上异常，因为括号里的表达式不是先于byNameAssert调用而被评估的，而是
                              //代之以创建一个函数值，其apply方法评估５／０＝＝０，而这个函数值将被传递给byNameAssert
    byValueAssert(5 / 0 == 0)  //编译会产生异常，因为括号里的表达式不是先于byBoolAssert调用而被评估的
  }
}

