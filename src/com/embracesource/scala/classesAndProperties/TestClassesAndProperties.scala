package com.embracesource.scala.classesAndProperties

object TestClassesAndProperties extends App {
  //4.1Creating a primary constructor
  //The primary constructor of a scala class is a combination of:
  //A.the constructor parameters
  //B.methods that are called in the body of the class
  //C.statements and expressions that are executed in the body of the class
  val p = new Person("jimmy", "yang")
  
  //output:
  //the constructor begins
  //Home = /home/jimmy
  //jimmy yang is 25 years old
  //still in the constructor
  
  //As we can see, the two constructor arguments firstName and lastName are
  //defined as var fields, which means that they¡¯re variable, or mutable; they can be changed
  //after they¡¯re initially set. Because the fields are mutable, Scala generates both accessor
  //and mutator methods for them
  p.firstName = "tina"  //setter
  p.lastName = "liu"
  
  println(p.firstName)  //getter
  println(p.lastName)  //getter
  
  //Because the age field is declared as a var, it¡¯s also visible, and can be mutated and accessed:
  p.age = 27
  p.age_$eq(28)  //scala translate = into _$eq
  println(p.age)
  
  //The field HOME is declared as a private val, which is like making it private and final
  //in a Java class. As a result, it can¡¯t be accessed directly by other objects, and its value can¡¯t
  //be changed.
  
  //4.2Controlling the visibility of constructor fields
  // If a field is declared as a var, Scala generates both getter and setter methods for that field.
  // If the field is a val, Scala generates only a getter method for it.
  // If a field doesn¡¯t have a var or val modifier, Scala gets conservative, and doesn¡¯t generate a getter or setter method for the field.
  // Additionally, var and val fields can be modified with the private keyword, which prevents getters and setters from being generated.
  // class Person(private var name: String) { def getName {println(name)} }  -> we can access name by p.getName rather than p.name
  
  //case classes
  //Parameters in the constructor of a case class differ from these rules in one way. Case
  //class constructor parameters are val by default. So if you define a case class field without adding val or var, like this:
  //  case class Person(name: String)
  //you can still access the field, just as if it were defined as a val:
  //  val p = new Person("jimmy")
  //  p.name
  
  //4.3Defining auxiliary constructors
  //Define the auxiliary constructors as methods in the class with the name this. You can
  //define multiple auxiliary constructors, but they must have different signatures (param©\eter lists).
  //Also, each constructor must call one of the previously defined constructors.
  //pizza can be created in the following ways:
  val p1 = new Pizza(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  val p2 = new Pizza(Pizza.DEFAULT_CRUST_SIZE)
  val p3 = new Pizza(Pizza.DEFAULT_CRUST_TYPE)
  val p4 = new Pizza
  
  //Generating auxiliary constructors for case classes
  //A case class is a special type of class that generates a lot of boilerplate code for you.
  //Because of the way they work, adding what appears to be an auxiliary constructor to a
  //case class is different than adding an auxiliary constructor to a ¡°regular¡± class. This is
  //because they¡¯re not really constructors: they¡¯re apply methods in the companion object of the class.
  //@see CasePerson.scala
  
  //4.4Defining a private primary constructor
  //To make the primary constructor private, insert the private keyword in between the
  //class name and any parameters the constructor accepts:
  //@see Brain.scala
  
  //utility classes
  //@see FileUtils
  val contents = FileUtils.readFile("input.txt")
  FileUtils.writeToFile("output.txt", "Hello World")
  
  //4.5Providing Default Values for Constructor Parameters
  //class Socket (val timeout: Int = 10000)
  //we can get an instance of socket like this:
  //val s = new Socket
  //or like this:
  //val s = new Socket(5000)
  //If we prefer the approach of using named parameters when calling a constructor (or method), we can also use this approach to construct a new Socket:
  //val s = new Socket(timeout=5000)
  
  //4.6Overriding default accessors and mutators
  class Persons(private var _name: String) {
    def name = _name // accessor
    def name_=(aName: String) { _name = aName } // mutator  //notice that we use the symbol '_=' to create a mutator 
  }
  //Notice the constructor parameter is declared private and var. The private keyword
  //keeps Scala from exposing that field to other classes, and the var lets the value of the
  //field be changed.
  val j = new Persons("Jonathan")
  j.name = "Jony" // setter
  println(j.name) // getter
  
  //As shown in the Solution, the recipe for overriding default getter and setter methods is:
  //1. Create a private var constructor parameter with a name you want to reference
  //from within your class. In the example in the Solution, the field is named _name.
  //2. Define getter and setter names that you want other classes to use. In the Solution
  //the getter name is name, and the setter name is name_= (which, combined with Scala¡¯s
  //syntactic sugar, lets users write p.name = "Jony").
  //3. Modify the body of the getter and setter methods as desired
  
  //4.7Preventing Getter and Setter Methods from Being Generated
  //Define the field with the private or private[this] access modifiers, as shown with the currentPrice field in this example:
  class Stock {
    //getter and setter methods are generated
    var delayedPrice: Double = _
    //keep this field hidden from other classes
    //getter and setter methods are defined for the delayedPrice field, and there are no getter or setter methods for the currentPrice field, as desired
    private var currentPrice: Double = _
  }
  
  //As an example, the following code yields true when the Driver object is run, because
  //the isHigher method in the Stock class can access the price field both (a) in its object,
  //and (b) in the other Stock object it¡¯s being compared to:
  class Stock1 {
    // a private field can be seen by any Stock instance
    private var price: Double = _
    def setPrice(p: Double) { price = p }
    def isHigher(that: Stock1): Boolean = this.price > that.price
  }
  object Driver extends App {
    val s1 = new Stock1
    s1.setPrice(20)
    val s2 = new Stock1
    s2.setPrice(100)
    println(s2.isHigher(s1))
  }
  
  //Object-private fields
  //Defining a field as private[this] takes this privacy a step further, and makes the field
  //object-private, which means that it can only be accessed from the object that contains
  //it. Unlike private, the field can¡¯t also be accessed by other instances of the same type,
  //making it more private than the plain private setting.
  
  class Stock2 {
    // a private[this] var is object-private, and can only be seen
    // by the current instance
    private[this] var price: Double = _
    def setPrice(p: Double) { price = p }
    // error: this method won't compile because price is now object-private
    //def isHigher(that: Stock2): Boolean = this.price > that.price
  }
  
  //4.8Assigning a field to a block or function
  class Foo {
    // set 'text' equal to the result of the block of code
    val text = {
      var lines = ""
      try {
        lines = io.Source.fromFile("/etc/passwd").getLines.mkString
      } catch {
        case e: Exception => lines = "Error happened"
      }
      lines
    }
    println(text)
  }
  val f = new Foo
  println("***")
  
  //When it makes sense, define a field like this to be lazy, meaning it won¡¯t be evaluated until it is accessed. 
  class Foo1 {
    // set 'text' equal to the result of the block of code
    lazy val text = "Lazy Hello World".foreach(println)
  }
  val f1 = new Foo1
  
  //When this code is compiled and run, there is no output, because the text field isn¡¯t
  //initialized until it¡¯s accessed. That¡¯s how a lazy field works.
  //Defining a field as lazy is a useful approach when the field might not be accessed in the  //normal processing of your algorithms, or if running the algorithm will take a long time,  //and you want to defer that to a later time.
  
  //4.9Setting uninitialized var field types
  //You want to set the type for an uninitialized var field in a class, so you begin to write code like this:
  //var x =
  //and then wonder how to finish writing the expression.
  //In general, define the field as an Option. For certain types, such as String and numeric fields, you can specify default initial values.
  
  //Option Represents optional values. Instances of `Option` are either an instance of $some or the object $none.
  case class Person(var username: String, var password: String) {
    var age = 0
    var firstName = ""
    var lastName = ""
    var address = None: Option[Address]  //define the address field as an option
  }
  case class Address(city: String, state: String, zip: String)
  
  //Later, when a user provides an address, you can assign it using a Some[Address], like this:
  val p5 = Person("alvinalexander", "secret")
  p.address = Some(Address("Talkeetna", "AK", "99676"))
  
  //4.10Handling constructor parameters when extending a class
  //Declare your base class as usual with val or var constructor parameters. When defining
  //a subclass constructor, leave the val or var declaration off of the fields that are common
  //to both classes. Then define new constructor parameters in the subclass as val or var fields, as usual.
  class Person5 (var name: String, var address: Address) {
    override def toString = if (address == null) name else s"$name @ $address"
  }
  class Employee (name: String, address: Address, var age: Int) extends Person5 (name, address) {
    // rest of the class
  }
  
  //4.11Calling a superclass constructor
  //This is a bit of a trick question, because you can control the superclass constructor that¡¯s
  //called by the primary constructor in a subclass, but you can¡¯t control the superclass
  //constructor that¡¯s called by an auxiliary constructor in the subclass.
  
  // (1) primary constructor
  class Animal (var name: String, var age: Int) {
    // (2) auxiliary constructor
    def this (name: String) {
      this(name, 0)
    }
    override def toString = s"$name is $age years old"
  }
  // calls the Animal one-arg constructor
  class Dog (name: String) extends Animal (name) {
    println("Dog constructor called")
  }
  // Alternatively, it could call the two-arg primary constructor of the Animal class:
  // call the two-arg constructor
  class Dog1 (name: String) extends Animal (name, 0) {
    println("Dog constructor called")
  }
  //Regarding auxiliary constructors, because the first line of an auxiliary constructor must
  //be a call to another constructor of the current class, there is no way for auxiliary con©\
  //structors to call a superclass constructor.
  case class Address3 (city: String, state: String)
  case class Role (role: String)
  class Person3 (var name: String, var address: Address3) {
    // no way for Employee auxiliary constructors to call this constructor
    def this (name: String) {
      this(name, null)
      address = null
    }
    override def toString = if (address == null) name else s"$name @ $address"
  }
  class Employee3 (name: String, role: Role, address: Address3) extends Person3 (name, address) {
    def this (name: String) {
      this(name, null, null)
    }
    def this (name: String, role: Role) {
      this(name, role, null)
    }
    def this (name: String, address: Address3) {
      this(name, null, address)
    }
  }
  
  //4.12When to use an abstract class
  //Scala has traits, and a trait is more flexible than an abstract class, so you wonder, "When should I use an abstract class?"
  //There are two main reasons to use an abstract class in Scala:
  //A.You want to create a base class that requires constructor arguments
  //B.The code will be called from java code
  //Regarding the first reason, traits don¡¯t allow constructor parameters:
  // this won't compile
  //trait Animal(name: String)
  //So, use an abstract class whenever a base behavior must have constructor parameters:
  //abstract class Animal(name: String)
  //Regarding the second reason, if you¡¯re writing code that needs to be accessed from Java,
  //you¡¯ll find that Scala traits with implemented methods can¡¯t be called from Java code. 
  
  //To declare that a method is abstract, just leave the body of the method undefined:
  //def speak // no body makes the method abstract
  
  //There is no need for an abstract keyword; simply leaving the body of the method
  //undefined makes it abstract. This is consistent with how abstract methods in traits are defined
  abstract class BaseController(db: Database) {
    def save { db.save }
    def update { db.update }
    def delete { db.delete }
    // abstract
    def connect
    // an abstract method that returns a String
    def getStatus: String
    // an abstract method that takes a parameter
    def setServerName(serverName: String)
  }
  class Database {
    def save {}
    def update {}
    def delete {}
  }
  //When a class extends the BaseController class, it must implement the connect,
  //getStatus, and setServerName methods, or be declared abstract. Attempting to extend
  //BaseController without implementing those methods yields a ¡°class needs to be ab©\
  //stract¡± error
  
  //Because a class can extend only one abstract class, when you¡¯re trying to decide whether
  //to use a trait or abstract class, always use a trait, unless you have this specific need to
  //have constructor arguments in your base implementation.
  
  //4.13Defining properties in an abstract base class(or trait)
  //You can declare both val and var fields in an abstract class (or trait), and those fields
  //can be abstract or have concrete implementations.
  //Abstract val and var fields
  abstract class Pet (name: String) {
    val greeting: String
    var age: Int
    def sayHello { println(greeting) }
    override def toString = s"I say $greeting, and I'm $age"
  }
  //The following Dog and Cat classes extend the Animal class and provide values for the greeting and age fields.
  //Notice that the fields are again specified as val or var:
  class Dog2 (name: String) extends Pet (name) {
    val greeting = "Woof"  //val or var should be added again here
    var age = 2
  }
  class Cat (name: String) extends Pet (name) {
    val greeting = "Meow"
    var age = 5
  }
  //when you provide concrete values for these fields in your concrete
  //classes, you must again define your fields to be val or var. Because the fields don¡¯t
  //actually exist in the abstract base class (or trait), the override keyword is not necessary.
  
  //as you can see below, both constructors in abstract class and concrete class are invoked.
  abstract class Animal6 {
    val greeting = { println("Animal"); "Hello" }
  }
  class Dog6 extends Animal6 {
    override val greeting = { println("Dog"); "Woof" }
  }
  new Dog6  //output: Animal Dog
  
  //To prevent a concrete val field in an abstract base class from being overridden in a subclass, declare the field as a final val:
  abstract class Animal7 {
    final val greeting = "Hello" // made the field 'final'
  }
  class Dog7 extends Animal7 {
    //val greeting = "Woof" // this line won't compile
  }
  
  //Concrete var fields in abstract classes
  //You can also give var fields an initial value in your trait or abstract class, and then refer to them in your concrete subclasses, like this:
  abstract class Animal8 {
    var greeting = "Hello"
    var age = 0
    override def toString = s"I say $greeting, and I'm $age years old."
  }
  class Dog8 extends Animal8 {
    greeting = "Woof"
    age = 2
  }
  //Because the fields are declared and initialized in the abstract Animal base class, there¡¯s
  //no need to redeclare the fields as val or var in the concrete Dog subclass.
  
  //Don't use null
  //If you¡¯re tempted to use a null, instead initialize the fields using the Option/Some/None pattern.
  trait Animal9 {
    val greeting: Option[String]
    var age: Option[Int] = None
    override def toString = s"I say $greeting, and I'm $age years old."
  }
  
  class Dog9 extends Animal9 {
    val greeting = Some("Woof")
    age = Some(2)
  }
  val dog9 = new Dog9
  println(dog9)
  
  //4.15Generate boilerplate code with case class
  // name and relation are 'val' by default
  case class Person1(name: String, relation: String)
  //Defining a class as a case class results in a lot of boilerplate code being generated, with the following benefits:
  //An apply method is generated, so you don¡¯t need to use the new keyword to create a new instance of the class.
  //Accessor methods are generated for the constructor parameters because case class constructor parameters are val by default. Mutator methods are also generated for parameters declared as var.
  //A good, default toString method is generated.
  //An unapply method is generated, making it easy to use case classes in match expressions.
  //equals and hashCode methods are generated.
  //A copy method is generated
  
  //when creating a new instance, "new" not needed before Person
  val emily = Person1("Emily", "niece")
  //Case class constructor parameters are val by default, so accessor methods are generated for the parameters, but mutator methods are not generated:
  println(emily.name)
  //By defining a case class constructor parameter as a var, both accessor and mutator methods are generated:
  case class Company (var name: String)
  val c = Company("Mat-Su Valley Programming")
  println(c.name)
  c.name = "Valley Programming"
  //Case classes also have a good default toString method implementation:
  println(emily)
  //Because an unapply method is automatically created for a case class, it works well when
  //you need to extract information in match expressions, as shown here:
  emily match { case Person1(n, r) => println(n, r) }
  //Case classes also have generated equals and hashCode methods, so instances can be compared:
  val hannah = Person("Hannah", "niece")
  //println(emily == hannah)
  //A case class even creates a copy method that is helpful when you need to clone an object, and change some of the fields during the process:
  case class Employee1(name: String, loc: String, role: String)
  val fred = Employee1("Fred", "Anchorage", "Salesman")
  val joe = fred.copy(name="Joe", role="Mechanic")
  
  //4.15Defining an equals method(Object Equality)
  //Like Java, you define an equals method (and hashCode method) in your class to compare two instances,
  //but unlike Java, you then use the == method to compare the equalityof two instances.
  class Person2 (name: String, age: Int) {
    def canEqual(a: Any) = a.isInstanceOf[Person2]
    override def equals(that: Any): Boolean =
    that match {
      case that: Person2 => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
    override def hashCode:Int = {
      val prime = 31
      var result = 1
      result = prime * result + age;
      result = prime * result + (if (name == null) 0 else name.hashCode)
      return result
    }
  }
  //With the equals method defined, you can compare instances of a Person with ==, as demonstrated in the following tests:
  // these first two instances should be equal
  val nimoy = new Person2("Leonard Nimoy", 82)
  val nimoy2 = new Person2("Leonard Nimoy", 82)
  val shatner = new Person2("William Shatner", 82)
  val ed = new Person2("Ed Chigliak", 20)
  // all tests pass
  println(nimoy == nimoy)
  println(nimoy == nimoy2)
  println(nimoy2 == nimoy)
  println(nimoy != shatner)
  println(shatner != nimoy)
  println(nimoy != null)
  println(nimoy != ed)
  
  //The first thing to know about Scala and the equals method is that, unlike Java, you
  //compare the equality of two objects with ==. In Java, the == operator compares ¡°reference
  //equality,¡± but in Scala, == is a method you use on each class to compare the equality of
  //two instances, calling your equals method under the covers.
  
  //Theory
  //The Scaladoc for the equals method of the Any class states, ¡°any implementation of this
  //method should be an equivalence relation.¡± The documentation states that an equiva©\
  //lence relation should have these three properties:
  
  //It is reflexive: for any instance x of type Any, x.equals(x) should return true.
  //It is symmetric: for any instances x and y of type Any, x.equals(y) should return true if and only if y.equals(x) returns true.
  //It is transitive: for any instances x, y, and z of type AnyRef, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
  
  //Therefore, if you override the equals method, you should verify that your implementation remains an equivalence relation.
  
  //4.16Creating inner classes
  //The concept of a ¡°class within a class¡± is different in Scala than in Java. As described on
  //the official Scala website, ¡°Opposed to Java-like languages where such inner classes are
  //members of the enclosing class, in Scala, such inner classes are bound to the outer
  //object.¡± The following code demonstrates this:
  class OuterClass {
    class InnerClass {
      var x = 1
    }
  }
  val oc1 = new OuterClass
  val oc2 = new OuterClass
  val ic1 = new oc1.InnerClass
  val ic2 = new oc2.InnerClass
  ic1.x = 10
  ic2.x = 20
  println(s"ic1.x = ${ic1.x}")
  println(s"ic2.x = ${ic2.x}")
  
  //There are many other things you can do with inner classes, such as include a class inside an object or an object inside a class:
  object OuterObject {
    class InnerClass {
      var x = 1
    }
  }
  class OuterClass1 {
    object InnerObject {
      val y = 2
    }
  }
  // class inside object
  println(new OuterObject.InnerClass().x)
  // object inside class
  println(new OuterClass1().InnerObject.y)
  
}