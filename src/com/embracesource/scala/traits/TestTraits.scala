package com.embracesource.scala.traits

import sun.reflect.generics.tree.BaseType

object TestTraits extends App {
  //8.1Using a trait as an interface
  //You can use a trait just like a Java interface. As with interfaces, just declare the methods in your trait that you want extending classes to implement:
  trait BaseSoundPlayer {
    def play
    def close
    def pause
    def stop
    def resume
  }
  //If a method should require parameters, list them as usual:
  trait Dog {
    def speak(whatToSay: String)
    def wagTail(enabled: Boolean)
  }
  //When extending a class and one or more traits, use extends for the class, and with for subsequent traits:
  //class Foo extends BaseClass with Trait1 with Trait2 {}
  //class Foo extends Trait1 with Trait2 with Trait3 with Trait4 {}
  //Unless the class implementing a trait is abstract, it must implement all of the abstract trait methods:
  class Mp3SoundPlayer extends BaseSoundPlayer {
    def play { }// code here ... }
    def close { }// code here ... }
    def pause { }// code here ... }
    def stop { }// code here ... }
    def resume { }// code here ... }
  }
  //If a class extends a trait but does not implement the abstract methods defined in that trait, it must be declared abstract:
  abstract class SimpleSoundPlayer extends BaseSoundPlayer {
    def play {  }
    def close {  }
  }
  //In other uses, one trait can extend another trait:
  trait Mp3BaseSoundFilePlayer extends BaseSoundPlayer {
    def getBasicPlayer: Mp3SoundPlayer
    def getBasicController: Mp3SoundPlayer
    def setGain(volume: Double)
  }
  //Classes extend your trait using either the extends or with keywords, according to these simple rules:
  //If a class extends one trait, use the extends keyword.
  //If a class extends multiple traits, use extends for the first trait and with to extend (mix in) the other traits.
  //If a class extends a class (or abstract class) and a trait, always use extends before the class name, and use with before the trait name(s).
  
  //As shown in the WaggingTail trait in the following example, not only can a trait be used
  //like a Java interface, but it can also provide method implementations, like an abstract class in Java:
  abstract class Animal {
  def speak
  }
  trait WaggingTail {
    def startTail { println("tail started") }
    def stopTail { println("tail stopped") }
  }
  trait FourLeggedAnimal {
    def walk
    def run
  }
  class Dog1 extends Animal with WaggingTail with FourLeggedAnimal {
    // implementation code here ...
    def speak { println("Dog says 'woof'") }
    def walk { println("Dog is walking") }
    def run { println("Dog is running") }
  }
  
  //8.2Using Abstract and Concrete Fields in Traits
  //You want to put abstract or concrete fields in your traits so they are declared in one place and available to all types that implement the trait.
  //Define a field with an initial value to make it concrete; otherwise, don¡¯t assign it an initial value to make it abstract.
  trait PizzaTrait {
    var numToppings: Int // abstract
    var size = 14 // concrete
    val maxNumToppings = 10 // concrete
  }
  //In the class that extends the trait, you¡¯ll need to define the values for the abstract fields, or make the class abstract. 
  class Pizza extends PizzaTrait {
    var numToppings = 0 // 'override' not needed
    size = 16 // 'var' and 'override' not needed
  }
  //As shown in the example, fields of a trait can be declared as either var or val. You don¡¯t
  //need to use the override keyword to override a var field in a subclass (or trait), but you
  //do need to use it to override a val field:
  trait PizzaTrait1 {
    val maxNumToppings: Int
  }
  class Pizza1 extends PizzaTrait1 {
    override val maxNumToppings = 10 // 'override' is required
  }
  
  //8.3Using a Trait Like an Abstract Class
  //Define methods in your trait just like regular Scala methods. In the class that extends
  //the trait, you can override those methods or use them as they are defined in the trait.
  trait Pet {
    def speak { println("Yo") } // concrete implementation
    def comeToMaster // abstract method
  }
  class Dog2 extends Pet {
    // don't need to implement 'speak' if you don't need to
    def comeToMaster { println("I'm coming!") }
  }
  class Cat extends Pet {
    // override the speak method
    override def speak { println("meow") }
    def comeToMaster { println("That's not gonna happen.") }
  }
  //If a class extends a trait without implementing its abstract methods, it must be defined as abstract. 
  //Discussion:Although Scala has abstract classes, it¡¯s much more common to use traits than abstract
  //classes to implement base behavior. A class can extend only one abstract class, but it can
  //implement multiple traits, so using traits is more flexible.
  
  //8.4Using Traits as Simple Mixins
  //You want to design a solution where multiple traits can be mixed into a class to provide a robust design.
  trait Tail {
    def wagTail { println("tail is wagging") }
    def stopTail { println("tail is stopped") }
  }
  abstract class Pet1 (var name: String) {
    def speak // abstract
    def ownerIsHome { println("excited") }
    def jumpForJoy { println("jumping for joy") }
  }
  class Dog3 (name: String) extends Pet1 (name) with Tail {
    def speak { println("woof") }
    override def ownerIsHome {
      wagTail
      speak
    }
  }
  
  //8.5Limiting Which Classes Can Use a Trait by Inheritance
  //You want to limit a trait so it can only be added to classes that extend a superclass or another trait.
  //Use the following syntax to declare a trait named TraitName, where TraitName can only be mixed into classes that extend a type named SuperThing,
  //where SuperThing may be a trait, class, or abstract class:
  //trait [TraitName] extends [SuperThing]
  
  //For instance, in the following example, Starship and StarfleetWarpCore both extend the common superclass StarfleetComponent,
  //so the StarfleetWarpCore trait can be mixed into the Starship class:
  class StarfleetComponent
  trait StarfleetWarpCore extends StarfleetComponent
  class Starship extends StarfleetComponent with StarfleetWarpCore
  
  //However, in the following example, the Warbird class can¡¯t extend the
  //StarfleetWarpCore trait, because Warbird and StarfleetWarpCore don¡¯t share the
  //same superclass:
  class StarfleetComponent1
  trait StarfleetWarpCore1 extends StarfleetComponent1
  class RomulanStuff
  // won't compile
  //class Warbird extends RomulanStuff with StarfleetWarpCore1
  
  //Discussion
  //It seems rare that a trait and a class the trait will be mixed into should both have the same superclass,
  //so I suspect the need for this recipe is also rare. When you want to limitthe classes a trait can be mixed into,
  //don¡¯t create an artificial inheritance tree to use this recipe; use one of the following recipes instead.
  
  //8.6Marking Traits So They Can Only Be Used by Subclasses of a Certain Type
  //You want to mark your trait so it can only be used by types that extend a given base type
  //To make sure a trait named MyTrait can only be mixed into a class that is a subclass of
  //a type named BaseType, begin your trait with a this: BaseType => declaration, as shown here:
  trait myTrait {
    this: BaseType =>
  }
  //For instance, to make sure a StarfleetWarpCore can only be used in a Starship, mark the StarfleetWarpCore trait like this:
  trait StarfleetWarpCore2 {
    this: Starship1 =>
    // more code here ...
  }
  //Given that declaration, this code will work:
  class Starship1
  class Enterprise extends Starship1 with StarfleetWarpCore2
  //But other attempts like this will fail:
  class RomulanShip
  // this won't compile
  //class Warbird extends RomulanShip with StarfleetWarpCore2
  
  //A trait can also require that any type that wishes to extend it must extend multiple other types
  trait WarpCore {
    this: Starship2 with WarpCoreEjector with FireExtinguisher =>
  }
  class Starship2
  trait WarpCoreEjector
  trait FireExtinguisher
  // this works
  class Enterprise2 extends Starship2 with WarpCore with WarpCoreEjector with FireExtinguisher
  
  //8.7Ensuring a Trait Can Only Be Added to a Type That Has a Specific Method
  //Use a variation of the self-type syntax that lets you declare that any class that attempts to mix in the trait must implement the method you specify.
  
  //In the following example, the WarpCore trait requires that any classes that attempt to mix it in must have an ejectWarpCore method:
  trait WarpCore3 {
    this: { def ejectWarpCore(password: String): Boolean } =>
  }
  //The following definition of the Enterprise class meets these requirements, and will therefore compile:
  class Starship3 {
    // code here ...
  }
  class Enterprise3 extends Starship3 with WarpCore3 {
    def ejectWarpCore(password: String): Boolean = {
      if (password == "password") {
        println("ejecting core")
        true
      } else {
        false
      }
    }
  }
  
  //A trait can also require that a class have multiple methods. To require more than one method, just add the additional method signatures inside the block:
  trait WarpCore4 {
    this: {
      def ejectWarpCore(password: String): Boolean
      def startWarpCore: Unit
    } =>
  }
  class Starship4
  class Enterprise4 extends Starship4 with WarpCore4 {
    def ejectWarpCore(password: String): Boolean = {                            //if any method of ejectWarpCore or startWarpCore is not defined, then it won't be compiled
      if (password == "password") { println("core ejected"); true } else false
    }
    def startWarpCore { println("core started") }
  }
  
  //8.8Adding a Trait to an Object Instance
  //Rather than add a trait to an entire class, you just want to add a trait to an object instance when the object is created.
  //solution:Add the trait to the object when you construct it.
  class DavidBanner
  trait Angry {
    println("You won't like me ...")
  }
  val hulk = new DavidBanner with Angry
  //As a more practical matter, you might mix in something like a debugger or logging trait when constructing an object to help debug that object:
  trait Debugger {
    def log(message: String) {
      // do something with message
    }
  }
  class Child
  class ProblemChild
  // no debugger
  val child = new Child
  // debugger added as the object is created
  val problemChild = new ProblemChild with Debugger  //This makes the log method available to the problemChild instance.
  
  //8.9Extending a Java Interface Like a Trait
  //Solution
  //In your Scala application, use the extends and with keywords to implement your Java interfaces, just as though they were Scala traits.
  //you can create a Dog class in Scala with the usual extends and with keywords, just as though you were using traits:
  class ScalaDog extends JavaAnimal {
    def speak { println("Hello World") }  //if this method is not defined, it won't compile
    def run { println("I'm running!") }
  }
  //The difference is that Java interfaces don¡¯t implement behavior, so if you¡¯re defining a class that extends a Java interface,
  //you¡¯ll need to implement the methods, or declare the class abstract.
}