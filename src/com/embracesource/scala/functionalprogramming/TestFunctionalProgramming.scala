package com.embracesource.scala.functionalprogramming

object TestFunctionalProgramming extends App {
  //Introduction
  //Scala is both an object-oriented programming (OOP) and a functional programming(FP) language.
  //This chapter demonstrates functional programming techniques, including the ability to define functions and pass them around as instances.
  //Just like you create a String instance in Java and pass it around, you can define a function as a variable and pass it around.
  
  //As a language that supports functional programming, Scala encourages an expressionoriented programming (EOP) model.
  //Simply put, in EOP, every statement (expression) yields a value. 
  
  //9.1Using Function Literals (Anonymous Functions)
  //You want to use an anonymous function¡ªalso known as a function literal¡ªso you can pass it into a method that takes a function, or to assign it to a variable.
  //given this list
  val x = List.range(1, 6)
  //you can pass an anonymous function to the List¡¯s filter method to create a new List that contains only even numbers:
  val evens = x.filter((i: Int) => i % 2 == 0)  //(i: Int) => i % 2 == 0 is a function literal (also known as an anonymous function):
  
  //Because the Scala compiler can infer from the expression that i is an Int, the Int declaration can be dropped off:
  val evens1 = x.filter(i => i % 2 == 0)
  
  //Because Scala lets you use the _ wildcard instead of a variable name when the parameter appears only once in your function, this code can be simplified even more:
  val evens2 = x.filter(_ % 2 == 0)
  
  
  //In other examples, you can simplify your anonymous functions further. For instance, beginning with the most explicit form,
  //you can print each element in the list using this anonymous function with the foreach method:
  x.foreach((i:Int) => println(i))
  //As before, the Int declaration isn¡¯t required:
  x.foreach((i) => println(i))
  //Because there is only one argument, the parentheses around the i parameter aren¡¯t needed:
  x.foreach(i => println(i))
  //Because i is used only once in the body of the function, the expression can be further simplified with the _ wildcard:
  x.foreach(println(_))
  //Finally, if a function literal consists of one statement that takes a single argument, you need not explicitly name and specify the argument, so the statement can finally be reduced to this:
  x.foreach(println)  //this method is used widely
  
  
  //9.2Using Functions As Variables
  //Use the syntax shown in Recipe 9.1 to define a function literal, and then assign that literal to a variable.
  //The following code defines a function literal that takes an Int parameter and returns a value that is twice the amount of the Int that is passed in:
  (i: Int) => { i * 2 }
  //You can now assign that function literal to a variable:
  val double = (i: Int) => { i * 2 }
  //The variable double is an instance, just like an instance of a String, Int, or other type,
  //but in this case, it¡¯s an instance of a function, known as a function value. You can now invoke double just like you¡¯d call a method:
  val res = double(5)  //10
  println(res)
  
  //Beyond just invoking double like this, you can also pass it to any method (or function)
  //that takes a function parameter with its signature. For instance, because the map method
  //of a sequence is a generic method that takes an input parameter of type A and returns a
  //type B, you can pass the double method into the map method of an Int sequence:
  val list = List.range(1, 5)
  list.map(double)  //use function double as variable
  
  //It's easy for compiler and a human to see that it returns a Boolean, so we can leave out the explicit Boolean return type.
  val f = (i: Int) => { i % 2 == 0 }
  
  //However, if you prefer to explicitly declare the return type of a function literal, or want to do so because your function is more complex,
  //the following examples show different forms you can use to explicitly declare that your function returns a Boolean:
  val f1: (Int) => Boolean = i => { i % 2 == 0 }
  val f2: Int => Boolean = i => { i % 2 == 0 }
  val f3: Int => Boolean = i => i % 2 == 0
  val f4: Int => Boolean = _ % 2 == 0
  
  // implicit approach
  val add1 = (x: Int, y: Int) => { x + y }
  val add2 = (x: Int, y: Int) => x + y
  // explicit approach
  val add3: (Int, Int) => Int = (x,y) => { x + y }
  val add4: (Int, Int) => Int = (x,y) => x + y
  
  //As shown, the curly braces around the body of the function in these simple examples are optional, but they are required when the function body grows to more than one expression:
  val addThenDouble: (Int, Int) => Int = (x,y) => {
    val a = x + y
    2 * a
  }
  
  //Using a method like an anonymous function
  //Scala is very flexible, and just like you can define an anonymous function and assign it to a variable,
  //you can also define a method and then pass it around like an instance  variable.
  //Again using a modulus example, you can define a method in any of these ways:
  def modMethod1(i: Int) = i % 2 == 0
  def modMethod2(i: Int) = { i % 2 == 0 }
  def modMethod3(i: Int): Boolean = i % 2 == 0
  def modMethod4(i: Int): Boolean = { i % 2 == 0 }
  
  //Any of these methods can be passed into collection methods that expect a function that has one Int parameter and returns a Boolean,
  //such as the filter method of a List[Int]:
  list.filter(modMethod1)
  
  val modFunction = (i: Int) => i % 2 == 0
  list.filter(modFunction)
  //Under the covers, modFunction is an instance of the Function1 trait, which defines a function that takes
  //one argument. (The scala package defines other similar traits, including Function0, Function2, and so on, up to Function22.)
  
  //Assigning an existing function/method to a function variable
  val c1 = scala.math.cos _
  val c2 = scala.math.cos(_)
  //Now that you have c, you can use it just like you would have used cos:
  println(c1(0))  //1.0
  
  //Summary notes:
  //Think of the => symbol as a transformer. It transforms the input data on its left side to some new output data, using the algorithm on its right side.
  //Use def to define a method, val, to create a function.
  //When assigning a function to a variable, a function literal is the code on the right side of the expression.
  //A function value is an object, and extends the FunctionN traits in the main scala package, such as Function0 for a function that takes no parameters.
  
  //9.3Defining a Method That Accepts a Simple Function Parameter
  //This solution follows a three-step process:
  //1. Define your method, including the signature for the function you want to take as a method parameter.
  //2. Define one or more functions that match this signature.
  //3. Sometime later, pass the function(s) as a parameter to your method.
  
  //To demonstrate this, define a method named executeFunction, which takes a function as a parameter.
  def executeFunction(callBack:() => Unit) {
    callBack()
  }
  //Two quick notes:
  //The callback:() syntax defines a function that has no parameters. If the function had parameters, the types would be listed inside the parentheses.
  //The => Unit portion of the code indicates that this method returns nothing.
  
  //The following function named sayHello takes no input parameters and returns nothing:
  val sayHello = () => {println("hello")}
  executeFunction(sayHello)
  
  //9.4More complex functions
  //You want to define a method that takes a function as a parameter, and that function may have one or more input parameters, and may also return a value.
  def exec(callback: Int => Unit) {
    // invoke the function we were given, giving it an Int parameter
    callback(5)
  }
  val plusOne = (i: Int) => { println(i+1) }
  exec(plusOne)  //6
  
  //Any function that matches this signature can be passed into the exec method.
  //The general syntax for describing a function as a method parameter is this:
  //parameterName: (parameterType(s)) => returnType
  //To define a function that takes two Ints and returns a Boolean, use this signature:
  //executeFunction(f:(Int, Int) => Boolean)
  
  //Passing in a function with other parameters
  def executeAndPrint(f:(Int, Int) => Int, x: Int, y: Int) {
    val result = f(x, y)
    println(result)
  }
  val sum = (x: Int, y: Int) => x + y
  val multiply = (x: Int, y: Int) => x * y
  executeAndPrint(sum, 2, 9) // prints 11
  executeAndPrint(multiply, 3, 9) // prints 27
  
  // 1 - define the method
  def exec(callback: (Any, Any) => Unit, x: Any, y: Any) {
    callback(x, y)
  }
  // 2 - define a function to pass in
  val printTwoThings =(a: Any, b: Any) => {
    println(a)
    println(b)
  }
  // 3 - pass the function and some parameters to the method
  case class Person(name: String)
  exec(printTwoThings, "Hello", Person("Dave"))
  
  //9.5Using closures
  //You want to pass a function around like a variable, and while doing so, you want that function to
  //be able to refer to one or more fields that were in the same scope as the function when it was declared.
  //To demonstrate a closure in Scala, use the following simple (but complete) example:
  class Foo {
    def exec(f:(String) => Unit, name: String){
      f(name)
    }
  }
  
  var hello = "Hello"
  def sayHello(name: String){println(s"$hello,$name")}
  
  val foo = new Foo
  foo.exec(sayHello, "Jimmy")
  
  //change the value of hello
  hello = "Good Morning"
  foo.exec(sayHello, "Jimmy")
  
  //a closure is a block of code which meets three criteria.
  //1. The block of code can be passed around as a value, and
  //2. It can be executed on demand by anyone who has that value, at which time
  //3. It can refer to variables from the context in which it was created (i.e., it is closed with respect to variable access, in the mathematical sense of the word ¡°closed¡±)
  
  //A second example
  var votingAge = 18
  val isOfVotingAge = (age: Int) => age >= votingAge
  isOfVotingAge(16) // false
  isOfVotingAge(20) // true
  //You can now pass isOfVotingAge around to other methods and functions:
  def printResult(f: Int => Boolean, x: Int) {
    println(f(x))
  }
  printResult(isOfVotingAge, 20) // true
  
  //Because you defined votingAge as a var, you can reassign it. How does this affect printResult? Let¡¯s see:
  // change votingAge in one scope
  votingAge = 21
  // the change to votingAge affects the result
  printResult(isOfVotingAge, 20) // now false
  
  //Using closures with other data types
  //In the two examples shown so far, you¡¯ve worked with simple String and Int fields, but closures can work with any data type, including collections. 
  import scala.collection.mutable.ArrayBuffer
  val fruits = ArrayBuffer("banana")
  // the function addToBasket has a reference to fruits
  val addToBasket = (s: String) => {
    fruits += s
    println(fruits.mkString(", "))
  }
  //As with the previous example, the addToBasket function can now be passed around as desired,
  //and will always have a reference to the fruits field. To demonstrate this, define a method that accepts a function with addToBasket¡¯s signature:
  def buyStuff(f: String => Unit, s: String) {
    f(s)
  }
  //Then pass addToBasket and a String parameter to the method:
  buyStuff(addToBasket, "apple")  //banana, apple
  buyStuff(addToBasket, "orange")   //banana, apple, orange
  
  //9.6Using Partially Applied Functions
  //You want to eliminate repetitively passing variables into a function by (a) passing common variables into the function to 
  //(b) create a new function that is preloaded with those values, and then (c) use the new function, passing it only the unique variables it needs.
  val sum1 = (a: Int, b: Int, c: Int) => a + b + c
  val fun = sum1(1, 2, _: Int)
  val res1 = fun(3)
  println(res1)  //6
  
  //This technique has many advantages, including the ability to make life easier for the consumers of a library you create.
  //For instance, when working with HTML, you may want a function that adds a prefix and a suffix to an HTML snippet:
  def wrap(prefix: String, html: String, suffix: String) = {
    prefix + html + suffix
  }
  val wrapWithDiv = wrap("<div>", _: String, "</div>")
  println(wrapWithDiv("<p>Hello, world</p>"))  //<div><p>Hello, world</p></div>
  println(wrapWithDiv("<img src=\"/images/foo.png\" />"))  //<div><img src="/images/foo.png" /></div>
  
  //As a nice benefit, you can still call the original wrap function if you want:
  println(wrap("<pre>", "val x = 1", "</pre>"))
  
  //9.7Creating a function that returns a function
  //You want to return a function (algorithm) from a function or method.
  
  //Define a function that returns an algorithm (an anonymous function), assign that to a
  //new function, and then call that new function.
  def saySomething(prefix: String) = (s: String) => {
    prefix + " " + s
  }
  val sayHello1 = saySomething("Hello")
  val sayHello1Person = sayHello1("Jimmy")
  println(sayHello1Person)
  
  //As you can imagine, you can use this approach any time you want to encapsulate an algorithm inside a function.
  def greeting(language: String) = (name: String) => {
    language match {
      case "english" => "Hello, " + name
      case "spanish" => "Buenos dias, " + name
    }
  }
  val hello1 = greeting("english")
  val buenosDias = greeting("spanish")
  println(hello1("Al"))
  println(buenosDias("Lorenzo"))
  
  //9.8Creating partial functions
  //You want to define a function that will only work for a subset of possible input values,
  //or you want to define a series of functions that only work for a subset of input values,
  //and combine those functions to completely solve a problem.
  
  //A partial function is a function that does not provide an answer for every possible input value it can be given.
  //It provides an answer only for a subset of possible data, and defines the data it can handle. 
  val divide = (x: Int) => 42 / x
  //As defined, this function blows up when the input parameter is zero:
  //divide(0)
  //Although you can handle this particular situation by catching and throwing an exception,
  //Scala lets you define the divide function as a PartialFunction. When doing so,
  //you also explicitly state that the function is defined when the input parameter is not zero:
  val divide1 = new PartialFunction[Int, Int] {
    def apply(x: Int) = 42 / x
    def isDefinedAt(x: Int) = x != 0
  }
  //With this approach, you can do several nice things. One thing you can do is test the function before you attempt to use it:
  if (divide1.isDefinedAt(1)) divide1(1)
  if (divide1.isDefinedAt(0)) divide1(0)
  
  //Whereas that divide function is explicit about what data it handles, partial functions are often written using case statements:
  val divide2: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }
  //Although this code doesn¡¯t explicitly implement the isDefinedAt method, it works exactly the same as the previous divide function definition:
  divide2.isDefinedAt(0)
  
  // converts 1 to "one", etc., up to 5
  val convertLowNumToString = new PartialFunction[Int, String] {
    val nums = Array("one", "two", "three", "four", "five")
    def apply(i: Int) = nums(i-1)
    def isDefinedAt(i: Int) = i > 0 && i < 6
  }
  
  //orElse and andThen
  //In the following example, two functions are defined that can each handle a small number of Int inputs, and convert them to String results:
  // converts 1 to "one", etc., up to 5
  val convert1to5 = new PartialFunction[Int, String] {
    val nums = Array("one", "two", "three", "four", "five")
    def apply(i: Int) = nums(i-1)
    def isDefinedAt(i: Int) = i > 0 && i < 6
  }
  // converts 6 to "six", etc., up to 10
  val convert6to10 = new PartialFunction[Int, String] {
    val nums = Array("six", "seven", "eight", "nine", "ten")
    def apply(i: Int) = nums(i-6)
    def isDefinedAt(i: Int) = i > 5 && i < 11
  }
  //Taken separately, they can each handle only five numbers. But combined with orElse, they can handle ten:
  val handle1to10 = convert1to5 orElse convert6to10
  
  println(handle1to10(3))
  println(handle1to10(8))
  
  //One example of where you¡¯ll run into partial functions is with the collect method on collections¡¯ classes.
  //The collect method takes a partial function as input, and as its Scaladoc describes,
  //collect ¡°Builds a new collection by applying a partial function to all elements of this list on which the function is defined.¡±
  
  val divide3: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }
  //If you attempt to use this function with the map method, it will explode with a MatchError:
  //List(0,1,2) map { divide3 }
  //However, if you use the same function with the collect method, it works fine:
  List(0,1,2) collect { divide3 }
  //This is because the collect method is written to test the isDefinedAt method for each element it¡¯s given.
  //As a result, it doesn¡¯t run the divide algorithm when the input value is 0 (but does run it for every other element).
  
  //The PartialFunction Scaladoc demonstrates this same technique in a slightly different way.
  //In the first example, it shows how to create a list of even numbers by defining a PartialFunction named isEven,
  //and using that function with the collect method:
  
  val sample = 1 to 5
  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + " is even"
  }
  val evenNumbers = sample collect isEven
  println(evenNumbers)
  
  val isOdd: PartialFunction[Int, String] = {
    case x if x % 2 == 1 => x + " is odd"
  }
  val numbers = sample map (isEven orElse isOdd)
  println(numbers)
  
  //9.9A Real World Example
  //@see NetonsMethod.scala
  //To demonstrate some of the techniques introduced in this chapter, NetonsMethod.scala shows one way to implement Newton¡¯s Method,
  //a mathematical method that can be used to solve the roots of equations.
  println("You have Finished Learning Functional Programmming")
}