package com.embracesource.scala.classesAndProperties
//4.3Defining auxiliary constructors for a case class

//the case class
case class CasePerson(var name: String, var age: Int) {
  
}

//the companion object
object CasePerson {
  def apply() = new CasePerson("jimmy", 26)
  def apply(name: String) = new CasePerson(name, 26)
}

object CaseClassTest extends App {
  val a = CasePerson() // corresponds to apply()
  val b = CasePerson("jimmy") // corresponds to apply(name: String)
  val c = CasePerson("jimmy", 26)
  println(a)
  println(b)
  println(c)
  // verify the setter methods work
  a.name = "tina"
  a.age = 27
  println(a)
}