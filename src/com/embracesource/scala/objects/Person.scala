package com.embracesource.scala.objects

class Person {
  var name: String = _
  var age = 0
}

object Person {
  // a one-arg constructor
  def apply(name: String): Person = {
    var p = new Person
    p.name = name
    p
  }
  // a two-arg constructor
  def apply(name: String, age: Int): Person = {
    var p = new Person
    p.name = name
    p.age = age
    p
  }
}

//Given this definition, you can create new Person instances without using the new keyword, as shown in these examples:
//val dawn = Person("Dawn")
//val a = Array(Person("Dan"), Person("Elijah"))