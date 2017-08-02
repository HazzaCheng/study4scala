package com.hazza.observable

/**
  * Created by hazza on 8/1/17.
  */
trait DefaultHandles extends Observable {
  override type Handle = (this.type => Unit)

  override protected def createHandle(callback: this.type => Unit): Handle = callback
}
