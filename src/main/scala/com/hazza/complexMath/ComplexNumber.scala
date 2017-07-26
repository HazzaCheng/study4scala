package com.hazza.complexMath

/**
  * Created by hazza on 7/26/17.
  */
case class ComplexNumber(real: Double, imaginary: Double) {
  def *(other: ComplexNumber) =
    ComplexNumber(real * other.real + imaginary * other.imaginary,
      real * other.imaginary + imaginary * other.real)

  def +(other: ComplexNumber) =
    ComplexNumber(real + other.real, imaginary + other.imaginary)
}

