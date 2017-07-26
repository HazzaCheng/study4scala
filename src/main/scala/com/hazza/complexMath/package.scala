package com.hazza

/**
  * Created by hazza on 7/26/17.
  */
package object complexMath {
  val i = ComplexNumber(0.0, 1.0);

  implicit def double2ComplexNumber(d: Double): ComplexNumber =
    new ComplexNumber(d, 0.0)
}
