package com.embracesource.scala.classesAndProperties

class Person(var firstName: String, var lastName: String) {
  println("the constructor begins")
  //some class fields
  private val Home = "/home/jimmy"
  var age = 25
  //some methods
  override def toString = s"$firstName $lastName is $age years old"
  def printHome {println(s"Home = $Home")}
  def printFullName {println(this)}
  
  printHome
  printFullName
  println("still in the constructor")
}