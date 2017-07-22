package com.hazza

import scala.language.implicitConversions
/**
  * Created by hazza on 7/21/17.
  */
class FileWrapper(val file: java.io.File) {
  def /(next: String) = new FileWrapper(new java.io.File(file, next))
  override def toString: String = file.getCanonicalPath()
}

object FileWrapper {
  implicit def wrap(file: java.io.File) = new FileWrapper(file)
  implicit def unwrap(wrapper: FileWrapper) = wrapper.file
}
