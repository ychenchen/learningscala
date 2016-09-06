package com.embracesource.scala.classesAndProperties
//4.4Defining a private primary constructor

//A simple way to enforce the Singleton pattern in Scala is to make the primary constructor
//private, then put a getInstance method in the companion object of the class:

class Brain private {
  override def toString = "This is the brain."
}

object Brain {
  val brain = new Brain
  def getInstance = brain
}

object SingletonTest extends App {
  //this won't compile
  //val brain = new Brain
  //this works
  val brain = Brain.getInstance
  println(brain)
}