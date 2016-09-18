package com.embracesource.scala.interactingwithjava

import scala.App

object TestInteractingWithJava extends App {
  //17.1Going to and from Java Collections
  //You¡¯re using Java classes in a Scala application, and those classes either return Java collections,
  //or require Java collections in their method calls.
  //Use the methods of Scala¡¯s JavaConversions object to make the conversions work.
  //For instance, the java.util.ArrayList class is commonly used in Java applications,
  //and you can simulate receiving an ArrayList from a method in the REPL, like this:
  def nums = {
    var list = new java.util.ArrayList[Int]()
    list.add(1)
    list.add(2)
    list
  }
  //Even though this method is written in Scala, when it¡¯s called, it acts just as though it was
  //returning an ArrayList from a Java method:
  val list = nums
  //However, because it¡¯s a Java collection, I can¡¯t call the foreach method on it that I¡¯ve
  //come to know and love in Scala, because it isn¡¯t there:
  //list.foreach(println)  //this won't compile
  //But by importing the methods from the JavaConversions object, the ArrayList magically acquires a foreach method:
  import scala.collection.JavaConversions._
  list.foreach(println)
  println("***")
  //discussion
  //When you get a reference to a Java collections object, you can either use that object as a Java collection(such as using its Iterator),
  //or you can convert that collection to a Scala collection. Once you become comfortable with Scala collection methods like foreach,
  //map, etc., you¡¯ll definitely want to treat it as a Scala collection, and the way to do that is to use the methods of the JavaConversions object.

  //As a more thorough example of how the JavaConversions methods work, assume you have a Java class named JavaExamples with a getNumbers method:
  //You can attempt to call that method from Scala code, as shown in this example:
  val numbers1 = JavaExamples.getNumbers
  numbers1.foreach(println) // this won't work if line 25 import ... is annotated
  println("***")
  //To solve this problem, you need to import the JavaConversions.asScalaBuffer method.
  //When you do this, you can either explicitly call the asScalaBuffer method, or let it be used implicitly.

  //The explicit call looks like this:
  import scala.collection.JavaConversions.asScalaBuffer
  val numbers2 = asScalaBuffer(JavaExamples.getNumbers)
  numbers2.foreach(println)
  //prints 'scala.collection.convert.Wrappers$JListWrapper'
  println(numbers2.getClass) //class scala.collection.convert.Wrappers$JListWrapper
  println("***")

  //The implicit use looks like this:
  import scala.collection.JavaConversions.asScalaBuffer
  val numbers3 = JavaExamples.getNumbers
  numbers3.foreach(println)
  // prints 'java.util.ArrayList'
  println(numbers3.getClass) //class java.util.ArrayList
  println("***")

  //The println(numbers.getClass) calls show that there¡¯s a slight difference in the result
  //between the explicit and implicit uses. 
  //You can repeat the same example using a Java Map and HashMap. First, create this method in the JavaExamples class:
  //Then, before calling this method from your Scala code, import the appropriate JavaConversions method:
  import scala.collection.JavaConversions.mapAsScalaMap
  //You can then call the mapAsScalaMap method explicitly, or allow it to be called implicitly:
  // explicit call
  val peeps1 = mapAsScalaMap(JavaExamples.getPeeps)
  // implicit conversion
  val peeps2 = JavaExamples.getPeeps
  
  //Going from Scala collections to Java collections
  //So far you¡¯ve looked primarily at converting Java collections to Scala collections. You
  //may also need to go in the other direction, from a Scala collection to a Java collection.
  
  //Putting the conversion tables to work, the following examples show how to pass a Seq, ArrayBuffer, and ListBuffer to the sum method:
  import scala.collection.JavaConversions._
  import scala.collection.mutable._
  val sum1 = JavaExamples.sum(seqAsJavaList(Seq(1, 2, 3)))
  val sum2 = JavaExamples.sum(bufferAsJavaList(ArrayBuffer(1,2,3)))
  val sum3 = JavaExamples.sum(bufferAsJavaList(ListBuffer(1,2,3)))
  
  //17.2Add Exception Annotations to Scala Methods to Work with Java
  //Add the @throws annotation to your Scala methods so Java consumers will know
  //which methods can throw exceptions and what exceptions they throw.
  //For example, the following Scala code shows how to add an @throws annotation to let
  //callers know that the exceptionThrower method can throw an Exception:
  // scala
  class Thrower {
    @throws(classOf[Exception])
    def exceptionThrower {
      throw new Exception("Exception!")
    }
  }
  //In your Java code, you¡¯ll write a try/catch block as usual to handle the exception:
  //@see JavaExamples main()

  //17.3Using @SerialVersionUID and Other Annotations
  //Use the Scala @SerialVersionUID annotation while also having your class extend the Serializable trait:
  @SerialVersionUID(1000L)
  class Foo extends Serializable {
    // class code here
  }
  //Note that Scala has a serializable annotation, but it has been deprecated since version 2.9.0.
  //The serializable annotation Scaladoc includes the following note:
  //instead of @serializable class C, use class C extends Serializable
  
  //17.4Using the Spring Framework
  //please forgive me to omit this section for a short time.
  
  //17.5Annotating varargs Methods
  //You¡¯ve created a Scala method with a varargs field, and would like to be able to call that method from Java code.
  //When a Scala method has a field that takes a variable number of arguments, mark it with the @varargs annotation.
  import scala.annotation.varargs
  class Printer {
    @varargs def printAll(args: String*) {
      args.foreach(print)
      println
    }
  }
  //I have tested this in JavaExamples, but this won't work because the method printAll can't be invoked.
  
  //17.6When Java Code Requires JavaBeans
  //Use the @BeanProperty annotation on your fields, also making sure you declare each field as a var.
  //The @BeanProperty annotation can be used on fields in a Scala class constructor:
  //import scala.reflect.BeanProperty
  //class Person(@BeanProperty var firstName: String,
  //  @BeanProperty var lastName: String) {
  //  override def toString = s"Person: $firstName $lastName"
  // }
  ////It can also be used on the fields in a Scala class:
  //import scala.reflect.BeanProperty
  //class EmailAccount {
  //  @BeanProperty var username: String = ""
  //  @BeanProperty var password: String = ""
  //  override def toString = s"Email Account: ($username, $password)"
  //}
  //This also won't work because it throws an error of "object BeanProperty is not a member of package reflect"
  
  //17.7Wrapping Traits with Implementations
  //please forgive me to omit this section for a short time.
}