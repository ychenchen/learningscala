package com.embracesource.scala.packagingandimports

//Introduction
//The Scala approach to importing members is also similar to Java, and more flexible With Scala you can:
//Place import statements anywhere
//Import classes, packages, or objects
//Hide and rename members when you import them

//It¡¯s helpful to know that in Scala, two packages are implicitly imported for you:
//java.lang._
//scala._
//In addition to those packages, all members from the scala.Predef object are imported into your applications implicitly.

//Many implicit conversions are brought into scope by the Predef object, as well as methods like println, readLine, assert, and require.

//example1
package com.acme.store {
	class Foo { override def toString = "I am com.acme.store.Foo" }
}

//example2
// a package containing a class named Foo
package orderentry {
  class Foo { override def toString = "I am orderentry.Foo" }
}
// one package nested inside the other
package customers {
  class Foo { override def toString = "I am customers.Foo" }
  package database {
    // this Foo is different than customers.Foo or orderentry.Foo
    class Foo { override def toString = "I am customers.database.Foo" }
  }
}

//package foo.bar.baz    this type of package must be written at the top of the file, if this line is uncommented, it won't compile for next line's class 
class Foo {
  override def toString = "I'm foo.bar.baz.Foo"
}

object TestPackagingAndImports extends App {
  //7.1Packaging with the Curly Braces Style Notation
  //Solution:Wrap one or more classes in a set of curly braces with a package name, as shown in example1:
  
  //With this approach, you can place multiple packages in one file. You can also nest packages using this ¡°curly braces¡± style. Just as shown in example2
  println(new orderentry.Foo)
  println(new customers.Foo)
  println(new customers.database.Foo)
  //package names don¡¯t have to be limited to just one level. You can define multiple levels of depth at one time:
  
  //You can create Scala packages with the usual Java practice of declaring a package name ***at the top of the file***:
  //In most cases, I use this packaging approach, but because Scala code can be much more concise than Java, 
  //the alternative curly brace packaging syntax can be very convenient when you want to declare multiple classes and packages in one file
  
  //7.2Importing One or More Members
  //You can import multiple classes the Java way:
  import java.io.File
  import java.io.IOException
  import java.io.FileNotFoundException
  
  //Or you can import several classes the Scala way:
  import java.io.{File, IOException, FileNotFoundException}
  //Use the following syntax to import everything from the java.io package:
  import java.io._    //The _ character in this example is similar to the * wildcard character in Java. 
  //The concept of importing code into the current scope is similar between Java and Scala, but Scala is more flexible. Scala lets you:
  //A.Place import statements anywhere, including the top of a class, within a class or object, within a method, or within a block of code
  //B.Import classes, packages, or objects
  //C.Hide and rename members when you import them
  
  //7.3Renaming members on import
  //You want to rename members when you import them to help avoid namespace collisions or confusion.
  
  //You want to rename members when you import them to help avoid namespace collisions or confusion
  import java.util.{ArrayList => JavaList}
  //Then, within your code, refer to the class by the alias you¡¯ve given it:
  val list = new JavaList[String]
  //You can also rename multiple classes at one time during the import process:
  import java.util.{Date => JDate, HashMap => JHashMap}
  //Because you¡¯ve created these aliases during the import process, the original (real) name of the class can¡¯t be used in your code. 
  
  //As an interesting combination of several recipes, not only can you rename classes on import,
  //but you can even rename class members. As an example of this, in shell scripts I tend to rename the println method to a shorter name
  import System.out.{println => p}
  println("hello")  //in this case, the original method name also useful
  p("world")
  
  //7.4Hiding a Class During the Import Process
  //To hide a class during the import process, use the renaming syntax shown in Recipe 7.3, ¡°Renaming Members on Import¡±, 
  //but point the class name to the _ wildcard character.
  //The following example hides the Random class, while importing everything else from the java.util package:
  import java.util.{Random => _, _}
  //val r = new Random  //this line won't compile
  val a = new ArrayList  //other object in java.util._ can be compiled
  //The second _ character inside the curly braces is the same as stating that you want to import everything else in the package
  //To hide multiple members, list them before using the final wildcard import:
  import java.util.{List => _, Map => _, Set => _, _}
  
  //7.5Using static import
  //You want to import members in a way similar to the Java static import approach, so you can refer to the member names directly,
  //without having to prefix them with their class name.
  //Use this syntax to import all members of the Java Math class:
  import java.lang.Math._
  //You can now access these members without having to precede them with the class name:
  val sinx = sin(30)
  
  //Enumerations are another great candidate for this technique. Given a Java enum Day.java
  //you can import and use this enumeration in a Scala program like this:
  import Day._
  import com.acme.store.Foo
  val date = Day.SUNDAY
  if (date == SUNDAY || date == SATURDAY) println("It's the weekend.")
  
  //7.6Using Import Statements Anywhere
  //You want to use an import statement anywhere, generally to limit the scope of the import, to make the code more clear, or to organize your code.
  //You can place an import statement almost anywhere inside a program.
  //You can limit the scope of an import to a method:
  //You can even place an import statement inside a block, limiting the scope of the import to only the code that follows the statement, inside that block. 
  
}