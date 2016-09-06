package com.embracesource.scala

class TestStrings() {
  
}

object TestStrings extends App {
  //1.1Testing string equality
  val s1 = "Hello"
	val s2 = "hello"
	val s3 = "H" + "ello"
	println(s1 == s2)
	println(s1 == s3)
	//A pleasant benefit of the == method is that it doesn¡¯t throw a NullPointerException
  //on a basic test if a String is null:
	val s4: String = null;
  println(s1 == s4)
  //However, be aware that calling a method on a null string can throw a NullPointerException:
  //s4.toUpperCase()
  println(s1.equalsIgnoreCase(s2))
  //In Scala, you test object equality with the == method. This is different than Java, where
  //you use the equals method to compare two objects.
  
  //1.2Creating multiline strings
  //A cleaner approach is to add the stripMargin method to the end of your multiline string
  //and begin all lines after the first line with the pipe symbol (|):
  
  //1.3Spliting Strings
  val s5 = "Hello World and Jimmy"
  s5.split(" ").foreach(println)
  
  //1.4Substituting Variables into Strings
  val name = "Fred"
  val age = 33
  val weight = 200.00
  println(s"$name is $age years old, and weighs $weight pounds.")
  println(s"Age next year: ${age + 1}")
  
  //print object fields
  case class Student(name: String, score: Int)
  val hannah = Student("Hannah", 95)
  println(s"${hannah.name} has a score of ${hannah.score}")  //object fields should be wrapped in curly braces
  
  //s is a method
  //The s that¡¯s placed before each string literal is actually a method.
  println(f"$name is $age years old, and weighs $weight%.2f pounds.")
  //The raw interpolator
  val s6 = s"foo\nbar"
  printf("s:" + s6 + "**")
  val s7 = raw"foo\nbar"
  println(s7)
  
  //Table 1-1 Common printf style format specifiers
  //Format specifier  Description
  //%c                Character
  //%d                Decimal number (integer, base 10)
  //%e                Exponential floating-point number
  //%f                Floating-point number
  //%i                Integer (base 10)
  //%o                Octal number (base 8)
  //%s                A string of characters
  //%u                Unsigned decimal (integer) number
  //%x                Hexadecimal number (base 16)
  //%%                Print a ¡°percent¡± character
  //\%                Print a ¡°percent¡± character
  
  //1.5Processing a String One Character at a Time
  val upper = "hello, world".map(c => c.toUpper)
  val upper1 = "hello, world".map(_.toUpper)
  val upper2 = "hello, world".filter(_ != 'l').map(_.toUpper)
  val upper3 = for (c <- "hello, world") yield c.toUpper
  
  val upper4 = for {
    c <- "hello, world"
    if c != 'l'
  } yield c.toUpper
  
  //1.6Finding patterns in string
  //You need to determine whether a String contains a regular expression pattern.
  val numberPattern = "[0-9]+".r
  val address = "123Main Hotpot Street5899"
  val match1 = numberPattern.findFirstIn(address)
  val match2 = numberPattern.findFirstMatchIn(address)
  val match3 = numberPattern.findAllIn(address)
  println(match1 + ":" + match2 + ":" + match3)
  match3.foreach(println)
  val match4 = numberPattern.findAllIn(address).toList  //convert iterator to array or list
  for(e <- match4) printf(e)
  println("***")
  
  //1.7Replacing patterns in strings
  val address1 = address.replaceAll("[0-9]", "*")
  println(address1)
  val regex = "[0-9]".r
  val address2 = regex.replaceAllIn(address, "x")
  println(address2)
  val address3 = "Hello World".replaceFirst("[a-zA-Z]", "*")
  println(address3)
  
  //1.8Exacting part of a string that match patterns
  val pattern = "([0-9]+)([a-zA-Z]+)".r
  val splits = pattern.split("100 apples 1a0 bananas")
  for(e <- splits) printf(e)
  println("***")
  
  //1.9Accessing a character in a String
  println("Hello".charAt(0))
  println("Hello"(1))
  
  //1.10Add your own methods to the String class
  //ATTENTIION implicit class
  //an implicit class must be define in a scope where methods definitions are allowed(not in the top level)
  //this means that our implicit class must be defined inside a class, object, or package object
  //Note that we can define as many methods as we can in our implicit class
  implicit class StringImprovement(s: String){
    def increment = s.map(c => (c+1).toChar)
    def decrement = s.map(c => (c-1).toChar)
    def hideAll = s.replaceAll(".", "*")
  }
  val result = "HAL".increment
  println(result)  //we will get "IBM" here
  //returning other types
  implicit class StringImprovements(val s: String) {
    def plusOne = s.toInt + 1
    def asBoolean = s match {
    case "0" | "zero" | "" | " " => false
    case _ => true
    }
  }
  println("5")
  println("0".asBoolean)
}