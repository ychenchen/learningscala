package com.embracesource.scala

//5.1Package scope
package com.acme.coolapp.model {
  class Foo {
    private[model] def doX {}
    private def doY {}
  }
  class Bar {
    val f = new Foo
    f.doX // compiles
    //f.doY // won't compile
  }
}

//5.1Public scope
package com.acme.coolapp.model {
  class Foo1 {
    def doX {}
  }
}
package org.xyz.bar {
  class Bar {
    val f = new com.acme.coolapp.model.Foo1
    f.doX
  }
}

object TestMethods extends App {
  //5.1Controlling method scope
  //Scala methods are public by default, and you want to control their scope in ways similar to Java.
  //In order from ¡°most restrictive¡± to ¡°most open,¡± Scala provides these scope options:
  //Object-private scope//Private//Package//Package-specific//Public
  
  //Object-private scope
  //The most restrictive access is to mark a method as object-private. When you do this, the
  //method is available only to the current instance of the current object. Other instances of the same class cannot access the method.
  //You mark a method as object-private by placing the access modifier private[this] before the method declaration:
  class Foo {
    private[this] def isFoo = true
      def doFoo(other: Foo) {
        //if (other.isFoo) { // this line won't compile
          // ...
        //}
      }
   }
  //Private scope
  //A slightly less restrictive access is to mark a method private, which makes the method
  //available to (a) the current class and (b) other instances of the current class. This is the same as marking a method private in Java.
  class Foo1 {
    private def isFoo1 = true  //change the scope to private
      def doFoo(other: Foo1) {
        if (other.isFoo1) { // this now compiles
          // ...
        }
      }
  }
  //By making a method private, it is not available to subclasses. 
  class Animal {
    private def heartBeat {}
  }
  class Dog extends Animal {
    //heartBeat // won't compile
  }
  
  //Protected scope
  //Marking a method protected makes the method available to subclasses, so the following code will compile:
  class Animal1 {
    protected def heartBeat {}
  }
  class Dog1 extends Animal1 {
    heartBeat
  }
  
  //The meaning of protected is slightly different in Scala than in Java. In Java, protected
  //methods can be accessed by other classes in the same package, but this isn¡¯t true in Scala.
  //The following code won¡¯t compile because the Jungle class can¡¯t access the breathe
  //method of the Animal class, even though they¡¯re in the same package:
  
  //Package scope
  //To make a method available to all members of the current package¡ªwhat would be
  //called ¡°package scope¡± in Java¡ªmark the method as being private to the current package
  //with the private[packageName] syntax.
  //@see line 3
  
  //Public scope
  //If no access modifier is added to the method declaration, the method is public. 
  //@see line 16
  
  //Table 5-1. Descriptions of Scala¡¯s access control modifiers
  //Access modifier       Description
  //private[this]         The method is available only to the current instance of the class it¡¯s declared in.
  //private               The method is available to the current instance and other instances of the class it¡¯s declared in.
  //protected             The method is available only to instances of the current class and subclasses of the current class.
  //private[model]        The method is available to all classes beneath the com.acme.coolapp.model package.
  //private[coolapp]      The method is available to all classes beneath the com.acme.coolapp package.
  //private[acme]         The method is available to all classes beneath the com.acme package.
  //(no modifier)         The method is p

  //5.2Calling a method on a superClass
  //To keep your code DRY (¡°Don¡¯t Repeat Yourself ¡±), you want to invoke a method that¡¯s already defined in a parent class or trait.
  class FourLeggedAnimal {
    def walk { println("I'm walking") }
    def run { println("I'm running") }
  }
  class Dog2 extends FourLeggedAnimal {
    def walkThenRun {
      super.walk
      super.run
    }
  }
  val dog2 = new Dog2
  dog2.walkThenRun
  //Controlling which trait you call a method from
  //If your class inherits from multiple traits, and those traits implement the same method,
  //you can select not only a method name, but also a trait name when invoking a method
  //using super. For instance, given this class hierarchy:
  
  trait Human {
    def hello = "the Human trait"
  }
  trait Mother extends Human {
    override def hello = "Mother"
  }
  trait Father extends Human {
    override def hello = "Father"
  }
  
  class Child extends Human with Mother with Father {
    def printSuper = super.hello  //this will call the trait which was with/extends latest
    def printMother = super[Mother].hello
    def printFather = super[Father].hello
    def printHuman = super[Human].hello
  }
  val c = new Child
  println(s"c.printSuper = ${c.printSuper}")
  println(s"c.printMother = ${c.printMother}")
  println(s"c.printFather = ${c.printFather}")
  println(s"c.printHuman = ${c.printHuman}")
  
  //5.3Setting Default Values for Method Parameters
  class Connection {
    def makeConnection(timeout: Int = 5000, protocol: String = "http") {
      println("timeout = %d, protocol = %s".format(timeout, protocol))
      // more code here
    }
  }
  
  //This method can now be called in the following ways:
  val con = new Connection
  con.makeConnection()
  con.makeConnection(2000)
  con.makeConnection(3000, "https")
  //Notice that If your method provides a mix of some fields that offer default values and others that don¡¯t, list the fields that have default values last. 
  
  //5.4Using Parameter Names When Calling a Method
  //The general syntax for calling a method with named parameters is this: methodName(param1=value1, param2=value2, ...)
  class Pizza {
    var crustSize = 12
    var crustType = "Thin"
    def update(crustSize: Int, crustType: String) {
      this.crustSize = crustSize
      this.crustType = crustType
    }
    override def toString = {
      "A %d inch %s crust pizza.".format(crustSize, crustType)
    }
  }
  val p = new Pizza
  //You can then update the Pizza, specifying the field names and corresponding values when you call the update method:
  p.update(crustSize = 16, crustType = "Thick")
  //This approach has the added benefit that you can place the fields in any order:
  p.update(crustType = "Pan", crustSize = 14)
  //5.5Defining a Method That Returns Multiple Items(Tuples)
  //You want to return multiple values from a method, but don¡¯t want to wrap those values in a makeshift class.
  def getStockInfo = {
    // other code here ...
    ("NFLX", 100.00, 101.00) // this is a Tuple3
  }
  val (symbol, currentPrice, bidPrice) = getStockInfo
  println((symbol, currentPrice, bidPrice))
  val result = getStockInfo
  println(result._1)  //tuple values can be accessed by position as result._1, result._2, and so on.
  
  //Working with tuples
  //In the example shown in the Solution, the getStockInfo method returned a tuple with three elements, so it is a Tuple3.
  //Tuples can contain up to 22 variables and are implemented as Tuple1 through Tuple22 classes. 
  //As a practical matter, you don¡¯t have to think about those specific classes; just create a new tuple by enclosing elements inside paretheses, as shown.
  
  
  //5.6Forcing Callers to Leave Parentheses off Accessor Methods
  //Define your getter/accessor method without parentheses after the method name:
  class Pizza1 {
    // no parentheses after crustSize
    def crustSize = 12
  }
  //This forces consumers of your class to call crustSize without parentheses:
  val p1 = new Pizza1
  //p1.crustSize()  //this fails because of the parentheses
  println(p.crustSize)  //this works
  
  //5.7. Creating Methods That Take Variable-Argument Fields
  //Solution:Define a varargs field in your method declaration by adding a * character after the field type:
  def printAll(strings: String*) {
    strings.foreach(println)
  }
  // these all work
  printAll()
  printAll("foo")
  printAll("foo", "bar")
  printAll("foo", "bar", "baz")
  //Use _* to adapt a sequence
  //As shown in the following example, you can use Scala¡¯s _* operator to adapt a sequence
  //(Array, List, Seq, Vector, etc.) so it can be used as an argument for a varargs field:
  // a sequence of strings
  val fruits = List("apple", "banana", "cherry")
  // pass the sequence to the varargs field
  printAll(fruits: _*)
  
  //Notice that When declaring that a method has a field that can contain a variable number of arguments,
  //the varargs field must be the last field in the method signature. 
  
  //5.8Declaring That a Method Can Throw an Exception
  //You want to declare that a method can throw an exception, either to alert callers to this fact or because your method will be called from Java code.
  //Use the @throws annotation to declare the exception(s) that can be thrown. To declare
  //that one exception can be thrown, place the annotation just before the method signature:
  import java.io._
  import javax.sound.sampled._
  @throws(classOf[IOException])
  @throws(classOf[LineUnavailableException])
  @throws(classOf[UnsupportedAudioFileException])
  def playSoundFileWithJavaAudio {
    // exception throwing code here ...
  }
  
  //5.9Supporting a Fluent Style of Programming
  //To support this style of programming:
  //If your class can be extended, specify this.type as the return type of fluent style methods.
  //If you¡¯re sure that your class won¡¯t be extended, you can optionally return this from your fluent style methods.
  class Person {
    protected var fname = ""
    protected var lname = ""
    def setFirstName(firstName: String): this.type = {    //person will be extended by employee, so return type is this.type
      fname = firstName
      this
    }
    def setLastName(lastName: String): this.type = {
      lname = lastName
      this
    }
  }
  class Employee extends Person {
    protected var role = ""
      def setRole(role: String): this.type = {
      this.role = role
      this
    }
    override def toString = {
      "%s, %s, %s".format(fname, lname, role)
    }
  }
  val employee = new Employee
  // use the fluent methods
  employee.setFirstName("Al")
  .setLastName("Alexander")
  .setRole("Developer")
  println(employee)
  
  //If you¡¯re sure your class won¡¯t be extended, specifying this.type as the return type of
  //your set* methods isn¡¯t necessary; you can just return the this reference at the end of
  //each fluent style method. This is shown in the addTopping, setCrustSize, and
  //setCrustType methods of the following Pizza class, which is declared to be final:
  final class Pizza2 {
    import scala.collection.mutable.ArrayBuffer
    private val toppings = ArrayBuffer[String]()
    private var crustSize = 0
    private var crustType = ""
    def addTopping(topping: String) = {
      toppings += topping
      this
    }
    def setCrustSize(crustSize: Int) = {
      this.crustSize = crustSize
      this
    }
    def setCrustType(crustType: String) = {
      this.crustType = crustType
      this
    }
    def print() {
      println(s"crust size: $crustSize")
      println(s"crust type: $crustType")
      println(s"toppings: $toppings")
    }
  }
  val p2 = new Pizza2
  p2.setCrustSize(14)
  .setCrustType("thin")
  .addTopping("cheese")
  .addTopping("green olives")
  .print()
  
  //Returning this in your methods works fine if you¡¯re sure your class won¡¯t be extended,
  //but if your class can be extended¡ªas in the first example where the Employee class
  //extended the Person class¡ªexplicitly setting this.type as the return type of your
  //set* methods ensures that the fluent style will continue to work in your subclasses. In
  //this example, this makes sure that methods like setFirstName on an Employee object
  //return an Employee reference and not a Person reference.
  
}