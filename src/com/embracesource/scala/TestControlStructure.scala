package com.embracesource.scala

import org.omg.CORBA.Object

object TestControlStructure extends App {
  //3.1Looping with for and foreach
  val a = Array("apple", "banana", "orange")
  for(e <- a) println(e)
  println("***")
  for(e <- a){
    val s = e.toUpperCase()
    println(s)
  }
  println("***")
  
  //returns values from a for loop(use the for/yield combination)
  val newArray = for(e <- a) yield e.toUpperCase()
  for(e <- newArray) println(e)
  println("***")
  
  //for loop counters  -> method1
  for(i <- 0 until a.length){
    println(s"$i is ${a(i)}")
  }
  println("***")
  
  //scala also offer a zipWithIndex method to create a loop counter
  for((e, count) <- a.zipWithIndex){
	  println(s"$count is $e")
  }
  println("***")
  
  //generators
  for(i <- 1 to 3) println(i)  //the 1 to 3 portion of the loop generates a range
  println("***")
  
  //guard(if statements in for loops)
  for(i <- 1 to 10 if i < 3) println(i)
  println("***")
  
  //looping over a map
  val names = Map("jimmy" -> "yang", "tina" -> "liu")
  for((k, v) <- names) println(s"key: $k, value: $v")
  println("***")
  
  //foreach
  a.foreach(println)
  println("***")
  a.foreach(e => println(e.toUpperCase()))
  println("***")
  
  //3.2Using for loops with multiple counters(often used in multidimensional arrays)
  for(i <- 1 to 2; j <- 1 to 2) println(s"i = $i, j = $j")
  println("***")
  //When doing this, the preferred style for multiline for loops is to use curly brackets:
  for {
    i <- 1 to 2
    j <- 1 to 2
  } println(s"i = $i, j = $j")
  println("***")
  
  //3.3Using a for loop with embedded if statement(Guards)
  for(i <- 1 to 10 if i % 2 == 0) println(i)
  println("***")
  //using the curly brackets style is preferred
  for{
    i <- 1 to 10
    if i % 2 == 0
  } println(i)
  println("***")
  
  //3.4Creating a for comprehension(for/yield combination)
  val namesArray = Array("jimmy", "tina", "elax")
  val capNames = for(e <- namesArray) yield e.capitalize  //Using a for loop with a yield statement is known as a for comprehension
  for(e <- capNames) println(e)
  println("***")
  val lengths = for(e <- namesArray) yield {
    println(e.length())
  }
  println("***")
  //Notice that the collection type returned by a for comprehension is the same type we begin with
  //If your input collection is a List, the for/yield loop will return a List:
  val fruits = "bananas" :: "apples" :: "orange" :: Nil
  val newFruits = for(e <- fruits) yield e.toUpperCase()
  for(e <- newFruits) println(e)
  println("***")
  //Notice that Writing a basic for/yield expression without a guard is just like calling the map method on a collection. 
  val newFruitss = fruits.map(_.toUpperCase())
  for(e <- newFruitss) println(e)
  println("***")
  
  //3.5Implementing break and continue
  //It¡¯s true that Scala doesn¡¯t have break and continue keywords, but it does offer similar
  //functionality through scala.util.control.Breaks.
  
  //the break example
  import util.control.Breaks._
  breakable {
    for(i <- 1 to 10){
      println(i)
      if (i>4) {
        break;  //break out of for loop
      }
    }
  }
  println("***")
  //we should notice that break and breakable are not keywords, they're methods in scala.util.control.Breaks
  
  //the continue example
  val searchMe = "peter piper picked a peck of pickled peppers"
  var numPs = 0
  
  for(i <- 1 until searchMe.length()){
    breakable {
      if (searchMe.charAt(i) != 'p') {
        break;
      } else {
        numPs += 1;  //break out of the 'breakable', continue the outside loop
      }
    }
  }
  println("Found " + numPs + " p's in the string.")
  println("***")
  
  //3.6Using the if construct like a ternary operator
  def abs(x : Int) = if(x < 0) -x else x
  println(abs(-6))
  println("***")
  
  //3.7Using a Match Expression Like a switch Statement
  val p = 9
  val month = p match {
    case 1 => "January"
    case 2 => "February"
    case 3 => "March"
    case 4 => "April"
    case 5 => "May"
    case 6 => "June"
    case 7 => "July"
    case 8 => "August"
    case 9 => "September"
    case 10 => "October"
    case 11 => "November"
    case 12 => "December"
    case _ => "Invalid month" // the default, catch-all
  }
  println(month)
  println("***")
  //the @switch annotation
  //This annotation provides a warning at compile time if the switch can¡¯t be
  //compiled to a tableswitch or lookupswitch.
  import scala.annotation.switch
  val i = 1
  //val i = "One"  //show the error case
  val g = (i: @switch) match {
    case 1 => "One"
    case 2 => "Two"
    case _ => "Other"
  }
  println(g)
  println("***")
  //3.8Matching multiple conditions with one case statement
  i match {
    case 1 | 3 | 5 | 7 | 9 => println("odd")
    case 2 | 4 | 6 | 8 | 10 => println("even")
  }
  println("***")
  
  //3.8Assigning the Result of a Match Expression to a Variable
  def isTrue(a: Any) : Boolean = a match {
    case 0 | "" => false
    case _ => true
  }
  isTrue(0)
  println("***")
  //3.10Accessing the Value of the Default Case in a Match Expression
  i match {
    case 0 => println("1")
    case 1 => println("2")
    case default => println("You gave me: " + default)
  }
  println("***")
  
  //3.11Using Pattern Matching in Match Expressions  
  //Patterns:
  //A:Constant patterns
  i match {
    case 0 => "zero"
    case default => println("You gave me: " + default)
  }
  //B:Variable patterns
  val ii = "Hello"
  ii match {
    case _ => s"Hmm, you gave me something ..."
  }
  ii match {
    case foo => s"Hmm, you gave me a $foo"
  }
  //C:Constructor patterns
  //case Person(first, "Alexander") => s"found an Alexander, first name = $first"
  //case Dog("Suka") => "found a dog named Suka"
  //D:Sequence patterns
  val j = List(0, "Hello", "world")
  j match {
    case List(0, _, _) => "a three-element list with 0 as the first element"
    case List(1, _*) => "a list beginning with 1, having any number of elements"
  }
  //E:Tuple patterns
  val pair = ("a", "b", "c", "d")
  println(pair)
  pair match {
    case (a, b, c, _) => s"4-elem tuple: got $a, $b, and $c"
  }
  println("***")
  
  //3.12Using case classes in match expresses
  trait Animal
  case class Dog(name: String) extends Animal
  case class Cat(name: String) extends Animal
  case object Woodpecker extends Animal
  
  def determineType(x: Animal): String = x match {
    case Dog(moniker) => "Got a Dog, name = " + moniker
    case _:Cat => "Got a Cat (ignoring the name)"
    case Woodpecker => "That was a Woodpecker"
    case _ => "That was something else"
  }
  
  println(determineType(new Dog("Rocky")))
  println(determineType(new Cat("Rusty the Cat")))
  println(determineType(Woodpecker))
  println("***")
  
  //3.13Adding if expressions(guard) on case statement
  val number = 3
  number match {
    case x if x == 1 => println("1")
    case x if (x == 2 || x == 3) => println("2")
    case default => println("You gave me: " + default)
  }
  
  //3.14Using a match expression instead of isInstanceOf
  //if (x.isInstanceOf[Foo]) { do something ...
  trait SentientBeing
  trait Animals extends SentientBeing
  case class Dogg(name: String) extends Animals
  case class Person(name: String, age: Int) extends SentientBeing
  // later in the code ...
  def printInfo(x: SentientBeing): Unit = x match {
    case Person(name, age) => println("person")
    case Dogg(name) => println(name)
  }
  printInfo(Dogg("tom"))
  
  //3.15Working with a list in a match expression
  //You know that a List data structure is a little different than other collection data struc©\
  //tures. It¡¯s built from cons cells and ends in a Nil element. You want to use this to your
  //advantage when working with a match expression, such as when writing a recursive
  //function.
  //we can create a list like this
  val list = List(1, 2, 3)
  //or like this, using cons cells and a Nil element:
  val list1 = 1 :: 2 :: 3 :: Nil
  
  //define a listToString function using recursive
  def listToString(list: List[String]): String = list match {
    case s :: rest => printf(s + " ") + listToString(rest)
    case Nil => ""
  }
  listToString(fruits)
  println("***")
  
  //calculate sum and multiply of the elements in a list
  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case n :: rest => n + sum(rest)
  }
  def multiply(list: List[Int]): Int = list match {
    case Nil => 1
    case n :: rest => n * multiply(rest)
  }
  val nums = List(1,2,3,4,5)
  println(sum(nums))
  println(multiply(nums))
  
  //3.16Matching one or more exceptions with try/catch
  val s = "5"
  try {
    val i = s.toInt
  } catch {
    case e: Exception => e.printStackTrace
  }
  //handle multiple exceptions
  try {
    val i = s.toInt
  } catch {
    case e: NumberFormatException => println("Couldn't find that file.")
    case e: IllegalArgumentException => println("Had an IOException trying to read that file")
  }
  //If you prefer to declare the exceptions that your method throws, or you need to interact
  //with Java, add the @throws annotation to your method definition:
  @throws(classOf[NumberFormatException])
  def toInt(s: String): Option[Int] = 
    try{
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => throw e
    }
  
  //3.17Declaring a Variable Before Using It in a try/catch finally Block
  import java.io._
  var in = None: Option[FileInputStream]
  var out = None: Option[FileOutputStream]
  try {
//    in = Some(new FileInputStream("/tmp/Test.class"))
//    out = Some(new FileOutputStream("/tmp/Test.class.copy"))
//    var c = 0
//    while ({c = in.get.read; c != -1}) {
//      out.get.write(c)
//    }
  } catch {
    case e: IOException => e.printStackTrace
  } finally {
    println("entered finally ...")
    if (in.isDefined) in.get.close
    if (out.isDefined) out.get.close
  }
  
  //3.18Create your own control structures
  import com.embracesource.scala.Whilst._
  var y = 0
  whilst(y < 5) {
    println(y)
    y += 1
  }
  
  doubleif(2>1)(3>1)(println("ControlStructureFinished"))
}

//3.18Create your own control structures
import scala.annotation.tailrec
object Whilst {
  @tailrec
  def whilst(testCondition: => Boolean)(codeBlock: => Unit) {
    if (testCondition) {
      codeBlock
      whilst(testCondition)(codeBlock)
    }
  }
  // two 'if' condition tests
  def doubleif(test1: => Boolean)(test2: => Boolean)(codeBlock: => Unit) {
    if (test1 && test2) {
      codeBlock
    }
  }
}