package com.hazza.observable

import scala.collection.mutable

/**
  * Created by hazza on 7/31/17.
  */
trait Observable {
  type Handle
  val callbacks = mutable.Map.empty[Handle, this.type => Unit]

  def observe(callback: this.type  => Unit): Handle = {
    val handle = createHandle(callback)
    callbacks += (handle -> callback)
    handle
  }

  def unobserve(handle: Handle): Unit = {
    callbacks -= handle
  }

  def createHandle(callback: this.type => Unit): Handle

  def notifyListeners(): Unit = {
    for (callback <- callbacks.values) callback(this)
  }
}
