package com.embracesource.scala.objects
//6.9Implementing the factory method in scala with apply

//For instance, suppose you want to create an Animal factory that returns instances of Cat
//and Dog classes, based on what you ask for. By writing an apply method in the com©\
//panion object of an Animal class, users of your factory can create new Cat and Dog
//instances like this:
//val cat = Animal("cat") // creates a Cat
//val dog = Animal("dog") // creates a Dog
//To implement this behavior, create a parent Animal trait:

trait Animal {
  def speak
}

//In the same file, create (a) a companion object, (b) the classes that extend the base trait, and (c) a suitable apply method:
object Animal {
  private class Dog extends Animal {
    override def speak { println("woof") }
  }
  private class Cat extends Animal {
    override def speak { println("meow") }
  }
  // the factory method
  def apply(s: String): Animal = {
    if (s == "dog") new Dog
    else new Cat
  }
  //If you don¡¯t like using the apply method as the factory interface, you can create the usual ¡°get¡± method in the companion object
  def getAnimal(s: String): Animal = {
    if (s == "dog") return new Dog
    else return new Cat
  }
}
//This lets you run the desired code:
//val cat = Animal("cat") // returns a Cat
//val dog = Animal("dog") // returns a Dog
