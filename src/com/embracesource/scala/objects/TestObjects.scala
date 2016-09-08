package com.embracesource.scala.objects

object TestObjects extends App {
  //6.1Ojbect casting
  //Use the asInstanceOf method to cast an instance to the desired type.
  //The asInstanceOf method is defined in the Scala Any class and is therefore available on all objects.
  val a = 10
  println(a.isInstanceOf[Int])  //true
  val b = a.asInstanceOf[Long]
  println(b.isInstanceOf[Int])  //false
  println(b.isInstanceOf[Long]) //true
  
  //6.2The scala equivalent of Java's .class
  //Solution
  //Use the Scala classOf method instead of Java¡¯s .class. The following example shows
  //how to pass a class of type TargetDataLine to a method named DataLine.Info:
  //The classOf method is defined in the Scala Predef object and is therefore available in all classes without requiring an import
  import javax.sound.sampled.DataLine
  class TargetDataLine {}
  val info = new DataLine.Info(classOf[TargetDataLine], null)
  
  //This approach also lets you begin with simple reflection techniques. The following example demonstrates how to access the methods of the String class:
  val stringClass = classOf[String]
  val stringMethods = stringClass.getMethods
  println(stringMethods.toList.size)
  
  //6.3Determining the class of an object
  //Because you don¡¯t have to explicitly declare types with Scala, you may occasionally want
  //to print the class/type of an object to understand how Scala works, or to debug code.
  //When you want to learn about the types Scala is automatically assigning on your behalf, call the getClass method on the object.
  def printAll(numbers: Int*) {
    println("classes:" + numbers.getClass)
  }
  printAll(1,2,3)  //classes:class scala.collection.mutable.WrappedArray$ofInt
  printAll()       //classes:class scala.collection.immutable.Nil$
  //When I can¡¯t see object types in an IDE, I write little tests like this in the REPL.
  //The usual pattern is to call getClass on the object of interest, passing in different parameters to see how things work:
  def printClass(c: Any) { println(c.getClass) }
  printClass(info)
  
  //6.4Launching an Application with an Object
  //There are two ways to create a launching point for your application: define an object that extends the App trait, or define an object with a properly defined main method.
  //When using the first approach, any command-line arguments to your application are implicitly available through an args object, which is inherited from the App trait
  if (args.length == 1)
    println(s"Hello, ${args(0)}")
  else
    println("I didn't get your name.")
  //the second approach, define your own main function
  object Hello2 {
    def main(args: Array[String]) {
      println("Hello, world")
    }
  }
  
  //6.5Creating singletons with object
  //You want to create a Singleton object to ensure that only one instance of a class exists.
  //Create Singleton objects in Scala with the object keyword. 
  //@see DateUtils.scala
  //Because these methods are defined in an object instead of a class, they can be called in the same way as a static method in Java:
  println(DateUtils.getCurrentDate)
  println(DateUtils.getCurrentTime)
  
  //6.6Creating Static Members with Companion Objects
  //You want to create a class that has instance methods and static methods, but unlike Java, Scala does not have a static keyword
  //Solution
  //Define nonstatic (instance) members in your class, and define members that you want
  //to appear as ¡°static¡± members in an object that has the same name as the class, and is in
  //the same file as the class. This object is known as a companion object.
  //@see Pizza.scala
  //With the Pizza class and Pizza object defined in the same file (presumably named Pizza.scala), 
  //members of the Pizza object can be accessed just as static members of aJava class:
  println(Pizza.CRUST_TYPE_THIN)
  println(Pizza.getFoo)
  //You can also create a new Pizza instance and use it as usual:
  var p = new Pizza(Pizza.CRUST_TYPE_THICK)
  println(p)
  //It¡¯s also important to know that a class and its companion object can access each other¡¯s private members.
  
  //6.7Putting common code in package objects
  //You want to make functions, fields, and other code available at a package level, without requiring a class or object.
  
  //Put the code you want to make available to all classes within a package in a package object.
  //For instance, if you want your code to be available to all
  //classes in the com.alvinalexander.myapp.model package, create a file named
  //package.scala in the com/alvinalexander/myapp/model directory of your project.
  //
  //In the package.scala source code, remove the word model from the end of the package
  //statement, and use that name to declare the name of the package object. Including a
  //blank line, the first three lines of your file will look like this:
  //package com.alvinalexander.myapp
  //
  //package object model {
  
  //You can now access this code directly from within other classes, traits, and objects in the package com.alvinalexander.myapp.model 
  // access our method, constant, and enumeration
  //@see package.scala
  echo("Hello, world")
  echo(MAGIC_NUM)
  echo(Margin.LEFT)
  // use our MutableMap type (scala.collection.mutable.Map)
  val mm = MutableMap("name" -> "Al")
  mm += ("password" -> "123")
  for ((k,v) <- mm) printf("key: %s, value: %s\n", k, v)
  
  //6.8Creating object instances without using the new keyword
  //There are two ways to do this:
  //Create a companion object for your class, and define an apply method in the companion object with the desired constructor signature.
  //Define your class as a case class.
  //@see Person.scala
  val dawn = Person("Dawn")
  val twoPerson = Array(Person("Dan"), Person("Elijah"))
  
  //Declare your class as a case class
  case class Person1 (var name: String)
  val p1 = Person1("Fred Flinstone")
  //With case classes, this works because the case class generates an apply method in a companion object for you.
  
  //it¡¯s important to know that when a case class is created, it writes the accessor and (optional) mutator methods only for the default constructor.
  //As a result, (a) it¡¯s best to define all class parameters in the default constructor, and (b) write apply methods for the auxiliary constructors you want.
  // want accessor and mutator methods for the name and age fields
  case class Person2 (var name: String, var age: Int)
  // define two auxiliary constructors
  object Person2 {
    def apply() = new Person2("<no name>", 0)
    def apply(name: String) = new Person2(name, 0)
  }
  //As a result, you can create instances of your class in three different ways, as demonstrated in the following code:
  val a2 = Person2()
  val b2 = Person2("Al")
  val c2 = Person2("William Shatner", 82)
  
  //6.9Implementing the factory method in scala with apply
  //One approach to this problem is to take advantage of how a Scala companion object¡¯s
  //apply method works. Rather than creating a ¡°get¡± method for your factory, you can
  //place the factory¡¯s decision-making algorithm in the apply method
  //@see animal.scala
  val cat = Animal("cat") // returns a Cat
  val dog = Animal("dog") // returns a Dog
  cat.speak
  dog.speak
  val cat1 = Animal.getAnimal("cat") // returns a Cat
  val dog1 = Animal.getAnimal("dog") // returns a Dog
  cat1.speak
  dog1.speak
}