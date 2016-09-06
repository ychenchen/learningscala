package com.embracesource.scala

import com.sun.xml.internal.ws.wsdl.writer.document.Import

import java.lang.Byte

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF

//2.1Parsing a Number from String
object ParsingANumberFromString{
  //Option/Some/None approach
  def toInt(s: String):Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }
}

//2.2Converting between numeric types(casting)
object ConvertingBetweenNumericTypes{
  val a = 19.45.toInt
  val b = 19.toFloat
}

//2.5Comparing floating-point numbers
object ComparingFloatingPointNumbers {
  def ~= (x: Double, y: Double, precision: Double) = {
    if ((x - y).abs < precision) true else false 
  }
}

object TestNumbers extends App {
  //2.1Parsing a Number from String
  import com.embracesource.scala.ComparingFloatingPointNumbers;
  import com.embracesource.scala.ParsingANumberFromString._
  //call the toInt method with getOrElse
  println(toInt("1").getOrElse("0"))
  println(toInt("a").getOrElse("0"))
  
  //call the toInt method with a match expression
  toInt("a") match {
    case Some(n) => println(n)
    case None => println("Boom, that isn't a number")
  }
  println("*****************")
  
  //2.2Converting between numeric types(casting)
  val x = 1000l
  println(x.isValidByte + ";" + x.isValidShort + ";" + x.isValidLong)
  println("*****************")
  
  //2.3Overriding the Default Numeric Type
  val a = 1
  val b = 1d
  val c = 1f
  val d = 1000L
  println(a + ";" + b + ";" + c + ";" + d)
  val a1 = 1: Float
  val b1 = 1: Int
  val c1 = 1: Short
  val d1 = 1: Double
  println(a1 + ";" + b1 + ";" + c1 + ";" + d1)
  val a2: Float  = 1
  val b2: Int    = 1
  val c2: Short  = 1
  val d2: Double = 1
  println(a2 + ";" + b2 + ";" + c2 + ";" + d2)
  println("*****************")
  
  //2.4Replacement for ++ and --
  //Notice that the symbols +=, -=, *=, -= aren't operators, they're methods.
  var e = 1
  e += 1
  println(e)
  e *= 3
  println(e)
  println("*****************")
  
  //2.5Comparing floating-point numbers
  /*
  scala> val a = 0.3
  a: Double = 0.3
  
  scala> val b = 0.1+0.2
  b: Double = 0.30000000000000004
  
  scala> a == b
  res0: Boolean = false
  */
  val f = 0.3
  val g = 0.1 + 0.2
  
  import com.embracesource.scala.ComparingFloatingPointNumbers._
  println(~=(a, b, 0.0001))
  println(~=(a, b, 0.00000000000000001))  //I'm wondering why the result of this is still true?
  println("*****************")
  
  //2.6Handling very large numbers
  var h = BigInt(1234567890)
  var i = BigDecimal(123456.789)
  println(h + h)
  println(h * h)
  h += 1
  println(h)
  //we can change the big* types to numeric types
  println(h.toInt)
  println(i.toDouble)
  //to avoid errors, we can test first to see if they can be converted to numeric types
  if (h.isValidInt) println(h.toInt)
  println("*****************")
  
  //2.7Generating random numbers
  var j = scala.util.Random
  println(j.nextInt())
  println(j.nextInt(100))  //limit the random number to a maximum value
  println(j.nextDouble())
  
  j.setSeed(1000L)  //we can set the seed value after a random object has been created
  
  val k = new scala.util.Random(1000)  //also, we can set the seed value while creating the object
  println("*****************")
  
  //2.8Creating a range, list, or array of numbers
  val l = 1 to 10
  println(l)
  val m = 1 to 10 by 2
  println(m)
  for(n <- 1 until 6) println(n)
  //convert the range to array or list
  val m1 = 1 to 10 toArray
  val m2 = 1 to 10 toList
  
  val n2 = for(n <- 1 to 5) yield n * 2  //the content after yield is the return value
  for(e <- n2) print(e + "**")
  println()
  
  println("*****************")
  
  //2.9Formatting numbers and currency
  val pi = scala.math.Pi
  println(pi + ">>" + f"$pi%1.5f" + ">>" + "%06.2f".format(pi))
  
  val formatter = java.text.NumberFormat.getIntegerInstance
  println(formatter.format(10000000))
  
  val locale = new java.util.Locale("de", "DE")  //the abbreviation of China is ch
  val formatter1 = java.text.NumberFormat.getIntegerInstance(locale)
  println(formatter1.format(10000000))
}

