package com.embracesource.scala.collections

import scala.collection.immutable.Vector

object TestCollections extends App {
  //Introduction
  //A few important concepts
  //A: A predicate is simply a method, function, or anonymous function that takes one or more parameters and returns a Boolean value.
  //For instance, the following method returns true or false, so it¡¯s a predicate:
  def isEven (i: Int) = if (i % 2 == 0) true else false
  
  //B: The concept of an anonymous function is also important. They¡¯re described in depth in Recipe 9.1,
  //but here¡¯s an example of the long form for an anonymous function:
  (i: Int) => i % 2 == 0
  //Here¡¯s the short form of the same function:
  //_ % 2 == 0
  //That doesn¡¯t look like much by itself, but when it¡¯s combined with the filter method on a collection,
  //it makes for a lot of power in just a little bit of code:
  val list = List.range(1, 10)
  val events = list.filter(_ % 2 == 0)
  
  //C: This is a nice lead-in into the third topic: implied loops. As you can see from that example,
  //the filter method contains a loop that applies your function to every element in the collection and returns a new collection. 
  
  //Collection methods like filter, foreach, map, reduceLeft, and many more have loops built into their algorithms.
  //As a result, you¡¯ll write far fewer loops when writing Scala code than with another language like Java.
  
  //10.1Understanding the collections hierarchy
  //At a high level, Scala¡¯s collection classes begin with the Traversable and Iterable traits,
  //and extend into the three main categories of sequences (Seq), sets (Set), and maps
  //(Map). Sequences further branch off into indexed and linear sequences, as shown below:
  //Figure 10-2. A high-level view of the Scala collections                  
  //                              Traversable
  //                                   |
  //                                   |
  //                                Iterable
  //                                   |
  //                     ______________|________________
  //                     |             |               |
  //                    Seq           Set             Map
  //                     |
  //             ________|_________
  //             |                |
  //        IndexedSeq         LinearSeq          
  
  //The Traversable trait lets you traverse an entire collection, and its Scaladoc states that
  //it ¡°implements the behavior common to all collections in terms of a foreach method,¡±
  //which lets you traverse the collection repeatedly.
  
  //The Iterable trait defines an iterator, which lets you loop through a collection¡¯s elements one at a time,
  //but when using an iterator, the collection can be traversed only once, because each element is consumed during the iteration process.
  
  //Sequences
  //Digging a little deeper into the sequence hierarchy, Scala contains a large number of sequences, many of which are shown in Figure 10-3.
  //Figure 10-3. A portion of the Scala sequence hierarchy
  //                                               Seq
  //                                                |
  //                          ______________________|_________________________
  //                          |                     |                        |
  //                      IndexedSeq              Buffer                  LinerSeq
  //        __________________|______________  _____|       __________________|_______________
  //        |      |      |     |     |     |  |    |       |    |     |      |      |       |
  //        |      |      |     |     |     |  |    |       |    |     |      |      |       |
  //        |StringBuilder|  String   |  ArrayBuffer|      List  | LinkedList |  MutableList |
  //        |             |           |             |            |            |              |
  //        |             |           |             |            |            |              |
  //     Array          Range       Vector      ListBuffer     Queue        Stack          Stream
  //                
               
  //As shown in Figure 10-3, sequences branch off into two main categories: indexed sequences and linear sequences (linked lists).
  //An IndexedSeq indicates that random access of elements is efficient, such as accessing an Array element as arr(5000). By default,
  //specifying that you want an IndexedSeq with Scala 2.10.x creates a Vector:                
  val x = IndexedSeq(1, 2, 3)       //Vector(1, 2, 3)
  //A LinearSeq implies that the collection can be efficiently split into head and tail components,
  //and it¡¯s common to work with them using the head, tail, and isEmpty methods. Note that creating a LinearSeq creates a List,
  val seq = scala.collection.immutable.LinearSeq(1, 2, 3)  //List(1,2,3)
  
  //Maps
  //Figure 10-4. Common map classes
  //                                       Map                                       
  //                                        |
  //       _________________________________|______________________________________
  //       |            |            |              |              |              |
  //       |            |            |              |              |              |
  //    HashMap    WeakHashMap   SortedMap        TreeMap    LinkedHashMap     ListMap
  //
  //When you just need a simple, immutable map, you can create one without requiring an import:
  
  val m = Map(1 -> "a", 2 -> "b")
  //The mutable map is not in scope by default, so you must import it (or specify its full path) to use it:
  val mm = collection.mutable.Map(1 -> "a", 2 -> "b")
  
  //Sets
  //Like a Java Set, a Scala Set is a collection of unique elements. The common set classes are shown in Figure 10-5.
  //                                       Set                                      
  //                                        |
  //                    ____________________|______________________
  //                    |            |              |             |
  //                    |            |              |             |
  //                  BitSet      HashSet        ListSet      SortedSet
  //                                                              |
  //                                                              |
  //                                                           TreeSet
  //
  //if you just need an immutable set, you can create it like this, without needing an import statement:
  val set = Set(1, 2, 3)
  //Just like a map, if you want to use a mutable set, you must import it, or specify its complete path:
  val ms = collection.mutable.Set(1, 2, 3)
  //More collection classes
  //There are many additional collection traits and classes, including Stream, Queue, Stack, and Range.
  //You can also create views on collections (like a database view); use iterators; and work with the Option, Some, and None types as collections. 
  //Strict and lazy collections
  //Collections can also be thought of in terms of being strict or lazy. 
  
  //10.2Choosing a collection class
  //There are three main categories of collection classes to choose from: Sequence, Map, Set
  //A sequence is a linear collection of elements and may be indexed or linear (a linked list).
  //A map contains a collection of key/value pairs, like a Java Map, Ruby Hash, or Python dictionary.
  //A set is a collection that contains no duplicate elements.
  
  //In addition to these three main categories, there are other useful collection types, including Stack, Queue, and Range.
  //There are a few other classes that act like collections, including tuples, enumerations, and the Option/Some/None and Try/Success/Failure classes.
  
  //Choosing a sequence
  //When choosing a sequence (a sequential collection of elements), you have two main decisions:
  //1.Should the sequence be indexed (like an array), allowing rapid access to any elements, or should it be implemented as a linked list?
  //2.Do you want a mutable or immutable collection?
  //Table 10-1. Scala¡¯s general-purpose sequential collections
  //                          Immutable       Mutable
  //Indexed                   Vector          ArrayBuffer
  //Linear (Linked lists)     List            ListBuffer
  
  //Table 10-4. Traits commonly used in library APIs
  //Trait           Description
  //IndexedSeq      Implies that random access of elements is efficient.
  //LinearSeq       Implies that linear access to elements is efficient.
  //Seq Used        when it isn¡¯t important to indicate that the sequence is indexed or linear in nature.
  
  //Choosing a Map
  //Choosing a map class is easier than choosing a sequence. There are the base mutable and immutable map classes,
  //a SortedMap trait to keep elements in sorted order by key,
  //a LinkedHashMap to store elements in insertion order, and a few other maps for special purposes. 
  
  //Choosing a set
  //Choosing a set is similar to choosing a map. There are base mutable and immutable set classes,
  //a SortedSet to return elements in sorted order by key, a LinkedHashSet to store elements in insertion order, and a few other sets for special purposes. 
  
  //Types that act like collections
  //Scala offers many other collection types, and some types that act like collections.
  //Table 10-7. Other collections classes (and types that act like collections)
  //               Description
  //Enumeration    A finite collection of constant values (i.e., the days in a week or months in a year).
  //Iterator       An iterator isn¡¯t a collection; instead, it gives you a way to access the elements in a collection. It does, however,
  //               define many of the methods you¡¯ll see in a normal collection class, including foreach, map, flatMap,
  //               etc. You can also convert an iterator to a collection when needed.
  //Option         Acts as a collection that contains zero or one elements. The Some class and None object extend Option.
  //Some           is a container for one element, and None holds zero elements.
  //Tuple          Supports a heterogeneous collection of elements. There is no one ¡°Tuple¡± class; tuples are implemented as
  //               case classes ranging from Tuple1 to Tuple22, which support 1 to 22 elements.
  
  //Strict and lazy collections
  //To understand strict and lazy collections, it helps to first understand the concept of a transformer method.
  //A transformer method is a method that constructs a new collection from an existing collection.
  //This includes methods like map, filter, reverse, etc.¡ªany method that transforms the input collection to a new output collection.
  //Given that definition, collections can also be thought of in terms of being strict or lazy.
  //In a strict collection, memory for the elements is allocated immediately, and all of its elements are immediately evaluated when a transformer method is invoked.
  //In a lazy collection, memory for the elements is not allocated immediately, and transformer methods do not construct new elements until they are demanded.
  
  //All of the collection classes except Stream are strict, but the other collection classes can be converted to a lazy collection by creating a view on the collection. 
  
  //10.3Choosing a collection method to solve a problem
  //The Scala collection classes provide a wealth of methods that can be used to manipulate data.
  //Most methods take either a function or a predicate as an argument. (A predicate is just a function that returns a Boolean.)
  //The methods that are available are listed in two ways in this recipe. In the next few paragraphs,
  //the methods are grouped into categories to help you easily find what you need.
  //In the tables that follow, a brief description and method signature is provided.
  
  //Methods organized by category
  //A:Filtering methods
  //Methods that can be used to filter a collection include collect, diff, distinct,
  //drop, dropWhile, filter, filterNot, find, foldLeft, foldRight, head,
  //headOption, init, intersect, last, lastOption, reduceLeft, reduceRight,
  //remove, slice, tail, take, takeWhile, and union.
  //B:Transformer methods
  //Transformer methods take at least one input collection to create a new output collection,
  //typically using an algorithm you provide. They include +, ++, -, - -, diff,
  //distinct, collect, flatMap, map, reverse, sortWith, takeWhile, zip, and zipWithIndex.
  //C:Grouping methods
  //These methods let you take an existing collection and create multiple groups from
  //that one collection. These methods include groupBy, partition, sliding, span,
  //splitAt, and unzip.
  //D:Informational and mathematical methods
  //These methods provide information about a collection, and include canEqual,
  //contains, containsSlice, count, endsWith, exists, find, forAll, hasDefiniteSize,
  //indexOf, indexOfSlice, indexWhere, isDefinedAt, isEmpty,
  //lastIndexOf, lastIndexOfSlice, lastIndexWhere, max, min, nonEmpty, product,
  //segmentLength, size, startsWith, sum. The methods foldLeft, foldRight,
  //reduceLeft, and reduceRight can also be used with a function you supply to obtain
  //information about a collection.
    //E:Others
  //A few other methods are hard to categorize, including par, view, flatten,
  //foreach, and mkString. par creates a parallel collection from an existing collection;
  //view creates a lazy view on a collection (see Recipe 10.24); flatten converts a list
  //of lists down to one list; foreach is like a for loop, letting you iterate over the
  //elements in a collection; mkString lets you build a String from a collection.
  
    //There are even more methods than those listed here. For instance, there¡¯s a collection
  //of to* methods that let you convert the current collection (a List, for example) to other
  //collection types (Array, Buffer, Vector, etc.). Check the Scaladoc for your collection
  //class to find more built-in methods.
  
  //Common collection methods
  //The following tables list the most common collection methods.
  //Table 10-8 lists methods that are common to all collections via Traversable. The following symbols are used in the first column of the table:
  //c refers to a collection
  //f refers to a function
  //p refers to a predicate
  //n refers to a number
  //op refers to a simple operation (usually a simple function)
  //Additional methods for mutable and immutable collections are listed in Tables 10-9 and 10-10, respectively.
  
    //Table 10-8. Common methods on Traversable collections
  //
  //Method             Description
  //c collect f        Builds a new collection by applying a partial function to all elements of the collection on which the function is defined.
  //c count p          Counts the number of elements in the collection for which the predicate is satisfied.
  //c1 diff c2         Returns the difference of the elements in c1 and c2.
  //c drop n           Returns all elements in the collection except the first n elements.
  //c dropWhile p      Returns a collection that contains the ¡°longest prefix of elements that satisfy the predicate.¡±
  //c exists p         Returns true if the predicate is true for any element in the collection.
  //c filter p         Returns all elements from the collection for which the predicate is true.
  //c filterNot p      Returns all elements from the collection for which the predicate is false.
  //c find p           Returns the first element that matches the predicate as Some[A]. Returns None if no match is found.
  //c flatten          Converts a collection of collections (such as a list of lists) to a single collection (single list).
  //c flatMap f        Returns a new collection by applying a function to all elements of the collection c (like map), and then flattening the elements of the resulting collections.
    //c foldLeft(z)(op)  Applies the operation to successive elements, going from left to right, starting at element z.
  //c foldRight(z)(op) Applies the operation to successive elements, going from right to left, starting at element z.
  //c forAll p         Returns true if the predicate is true for all elements, false otherwise.
  //c foreach f        Applies the function f to all elements of the collection.
  //c groupBy f        Partitions the collection into a Map of collections according to the function.
  //c hasDefiniteSize  Tests whether the collection has a finite size. (Returns false for a Stream or Iterator, for example.)
  //c head             Returns the first element of the collection. Throws a NoSuchElementException if the collection is empty.
  //c headOption       Returns the first element of the collection as Some[A] if the element exists, or None if the collection is empty.
  //c init             Selects all elements from the collection except the last one. Throws an UnsupportedOperationException if the collection is empty.
  //c1 intersect c2    On collections that support it, it returns the intersection of the two collections (the elements common to both collections).
  //c isEmpty          Returns true if the collection is empty, false otherwise.
  //c last             Returns the last element from the collection. Throws a NoSuchElementException if the collection is empty.
  //c lastOption       Returns the last element of the collection as Some[A] if the element exists, or None if the collection is empty.
  //c map f            Creates a new collection by applying the function to all the elements of the collection.
  //c max              Returns the largest element from the collection.
  //c min              Returns the smallest element from the collection.
  //c nonEmpty         Returns true if the collection is not empty.
  //c par              Returns a parallel implementation of the collection, e.g., Array returns ParArray.
  //c partition p      Returns two collections according to the predicate algorithm.
  //c product          Returns the multiple of all elements in the collection.
  //c reduceLeft op    The same as foldLeft, but begins at the first element of the collection.
  //c reduceRight op   The same as foldRight, but begins at the last element of the collection.
  //c reverse          Returns a collection with the elements in reverse order. (Not available on Traversable, but common to most collections, from GenSeqLike.)
  //c size             Returns the size of the collection.
    //c slice(from, to)  Returns the interval of elements beginning at element from and ending at element to.
  //c sortWith f       Returns a version of the collection sorted by the comparison function f.
  //c span p           Returns a collection of two collections; the first created by c.takeWhile(p), and the second created by c.dropWhile(p).
  //c splitAt n        Returns a collection of two collections by splitting the collection c at element n.
  //c sum              Returns the sum of all elements in the collection.
  //c tail             Returns all elements from the collection except the first element.
  //c take n           Returns the first n elements of the collection.
  //c takeWhile p      Returns elements from the collection while the predicate is true. Stops when the predicate becomes false.
  //c1 union c2        Returns the union (all elements) of two collections.
  //c unzip            The opposite of zip, breaks a collection into two collections by dividing each element into two pieces, as in breaking up a collection of Tuple2 elements.
  //c view             Returns a nonstrict (lazy) view of the collection.
  //c1 zip c2          Creates a collection of pairs by matching the element 0 of c1 with element 0 of c2, element 1 of c1 with element 1 of c2, etc.
  //c zipWithIndex     Zips the collection with its indices.
  
  
  //Mutable collection methods
  //Table 10-9 shows the common methods for mutable collections. (Although these are all methods, they¡¯re often referred to as operators, because that¡¯s what they look like.)
  //Table 10-9. Common operators (methods) on mutable collections
  //Operator (method)  Description
  //c += x             Adds the element x to the collection c.
  //c += (x,y,z)       Adds the elements x, y, and z to the collection c.
  //c1 ++= c2          Adds the elements in the collection c2 to the collection c1.
  //c -= x             Removes the element x from the collection c.  
  //c -= (x,y,z)       Removes the elements x , y, and z from the collection c.
  //c1 --= c2          Removes the elements in the collection c2 from the collection c1.
  //c(n) = x           Assigns the value x to the element c(n).
  //c clear            Removes all elements from the collection.
  //c remove n         
  //c.remove(n, len)   Removes the element at position n, or the elements beginning at position n and continuing for length len.
  
  //Table 10-10 shows the common methods for working with immutable collections. Note that immutable collections can¡¯t be modified,
  //so the result of each expression in the first column must be assigned to a new variable.
  //(Also, see Recipe 10.6 for details on using a mutable variable with an immutable collection.)
  //Table 10-10. Common operators (methods) on immutable collections
  //Operator (method)    Description
  //c1 ++ c2             Creates a new collection by appending the elements in the collection c2 to the collection c1.
  //c :+ e               Returns a new collection with the element e appended to the collection c.
  //e +: c               Returns a new collection with the element e prepended to the collection c.
  //e ::                 list Returns a List with the element e prepended to the List named list. (:: works only on List.)
  //c drop n             The two methods - and -- have been deprecated, so use the filtering methods listed in Table 10-8 to
  //                     return a new collection with the desired elements removed. Examples of some of these filtering methods
  //                     are shown here.
  //c dropWhile p
  //c filter p
  //c filterNot p
  //c head
  //c tail
  //c take n
  //c takeWhile p
  
  //Maps
  //Maps have additional methods, as shown in Table 10-11. In this table, the following symbols are used in the first column:
  //m refers to a map
  //mm refers to a mutable map
  //k refers to a key
  //p refers to a predicate (a function that returns true or false)
  //v refers to a map value
  //c refers to a collection
  //Table 10-11. Common methods for immutable and mutable maps 
  //Map                         method Description
  //Methods for immutable maps
  //m - k                       Returns a map with the key k (and its corresponding value) removed.
  //m - (k1, k2, k3)            Returns a map with the keys k1, k2, and k3 removed.
  //m -- c
  //m -- List(k1, k2)           Returns a map with the keys in the collection removed. (Although List is shown, this can be any sequential collection.)
  //Methods for mutable maps
  //mm += (k -> v)
  //mm += (k1 -> v1, k2 -> v2)  Add the key/value pair(s) to the mutable map mm.
  //mm ++= c
  //mm ++= List(3 -> "c")       Add the elements in the collection c to the mutable map mm.
  //mm -= k
  //mm -= (k1, k2, k3)          Remove map entries from the mutable map mm based on the given key(s).
  //mm --= c                    Remove the map entries from the mutable map mm based on the keys in the collection c.
  //Methods for both mutable and immutable maps
  //m(k)                        Returns the value associated with the key k.
  //m contains k                Returns true if the map m contains the key k.
  //m filter p                  Returns a map whose keys and values match the condition of the predicate p.
  //m filterKeys p              Returns a map whose keys match the condition of the predicate p.
  //m get k                     Returns the value for the key k as Some[A] if the key is found, None otherwise.
  //m getOrElse(k, d)           Returns the value for the key k if the key is found, otherwise returns the default value d.
  //m isDefinedAt k             Returns true if the map contains the key k.
  //m keys                      Returns the keys from the map as an Iterable.
  //m keyIterator               Returns the keys from the map as an Iterator.
  //m keySet                    Returns the keys from the map as a Set.
  //m mapValues f               Returns a new map by applying the function f to every value in the initial map.
  //m values                    Returns the values from the map as an Iterable.
  //m valuesIterator            Returns the values from the map as an Iterator.
  
  //10.4Understanding the performance of collections
  //Please forgive me to omit this section for this time.
  
  //10.5Declaring a type when creating a collection
  //You want to create a collection of mixed types, and Scala isn¡¯t automatically assigning the type you want.
  //In the following example, if you don¡¯t specify a type, Scala automatically assigns a type of Double to the list:
  val x1 = List(1, 2.0, 33D, 400L)  //List[Double] = List(1.0, 2.0, 33.0, 400.0)
  //If you¡¯d rather have the collection be of type AnyVal or Number, specify the type in brackets before your collection declaration:
  val x2 = List[Number](1, 2.0, 33D, 400L)  //List[java.lang.Number] = List(1, 2.0, 33.0, 400)
  val x3 = List[AnyVal](1, 2.0, 33D, 400L)  //List[AnyVal] = List(1, 2.0, 33.0, 400)
  
  //Discussion
  //By manually specifying a type, in this case Number, you control the collection type.
  //This is useful any time a list contains mixed types or multiple levels of inheritance. For instance, given this type hierarchy:
  trait Animal
  trait FurryAnimal extends Animal
  case class Dog(name: String) extends Animal
  case class Cat(name: String) extends Animal
  //create a sequence with a Dog and a Cat:
  val x4 = Array(Dog("Fido"), Cat("Felix"))  //Array[Product with Serializable with Animal] = Array(Dog(Fido), Cat(Felix))
  //As shown, Scala assigns a type of Product with Serializable with Animal. If you just want an Array[Animal], manually specify the desired type:
  val x5 = Array[Animal](Dog("Fido"), Cat("Felix"))  //Array[Animal] = Array(Dog(Fido), Cat(Felix))
  
  //10.6Understanding mutable variable with immutable collections
  //Problem
  //You may have seen that mixing a mutable variable (var) with an immutable collection
  //causes surprising behavior. For instance, when you create an immutable Vector as a
  //var, it appears you can somehow add new elements to it:
  var sisters = Vector("Melinda")  //sisters: collection.immutable.Vector[String] = Vector(Melinda)
  sisters = sisters :+ "Melissa"
  sisters = sisters :+ "Marisa"
  sisters.foreach(println)
  
  //But how can this be?
  //Though it looks like you¡¯re mutating an immutable collection, what¡¯s really happening
  //is that the sisters variable points to a new collection each time you use the :+ method.
  //The sisters variable is mutable¡ªlike a non-final field in Java¡ªso it¡¯s actually being
  //reassigned to a new collection during each step. The end result is similar to these lines of code:
  var sisters1 = Vector("Melinda")
  sisters1 = Vector("Melinda", "Melissa")
  sisters1 = Vector("Melinda", "Melissa", "Marisa")
  //In the second and third lines of code, the sisters reference has been changed to point to a new collection.
  
  //10.7Make Vector Your ¡°Go To¡± Immutable Sequence
  //Problem:You want a fast, general-purpose, immutable, sequential collection type for your Scala applications.
  //Create and use a Vector just like other immutable, indexed sequences. You can create them and access elements efficiently by index:
  //Create and use a Vector just like other immutable, indexed sequences. You can create them and access elements efficiently by index:
  val v = Vector("a", "b", "c")
  v(0)
  //You can¡¯t modify a vector, so you ¡°add¡± elements to an existing vector as you assign the result to a new variable:
  val a = Vector(1, 2, 3)  //a: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3)
  val b = a ++ Vector(4, 5)
  //Use the updated method to replace one element in a Vector while assigning the result to a new variable:
  val c = v.updated(0, "x")
  //You can also use all the usual filtering methods to get just the elements you want out of a vector:
  val d = b.take(2)
  val e = b.filter(_ > 2)
  
  //As noted in Recipe 10.1, if you create an instance of an IndexedSeq, Scala returns a Vector:
  val x6 = IndexedSeq(1,2,3)
  println(x6)  //Vector(1, 2, 3)
  
  //10.8Make "Array Buffer" your "Go To" mutable sequence
  //You want to use a general-purpose, mutable sequence in your Scala applications.
  //To use an ArrayBuffer, first import it:
  import scala.collection.mutable.ArrayBuffer
  //You can then create an empty ArrayBuffer:
  var fruits = ArrayBuffer[String]()
  var ints = ArrayBuffer[Int]()
  //Or you can create an ArrayBuffer with initial elements:
  var nums = ArrayBuffer(1, 2, 3)  //scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
  //Like other mutable collection classes, you add elements using the += and ++= methods:
  // add one element
  nums += 4  //scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
  // add two or more elements (method has a varargs parameter)
  nums += (5, 6)  //scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 5, 6)
  // add elements from another collection
  nums ++= List(7, 8)  //scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8)
  //You remove elements with the -= and --= methods:
  // remove one element
  nums -= 9  //scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8)
  // remove two or more elements
  nums -= (7, 8)  //scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 5, 6)
  // remove elements specified by another sequence
  nums --= Array(5, 6)  //scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
  
  //Discussion
  val a1 = ArrayBuffer(1, 2, 3) // ArrayBuffer(1, 2, 3)
  a1.append(4) // ArrayBuffer(1, 2, 3, 4)
  a1.append(5, 6) // ArrayBuffer(1, 2, 3, 4, 5, 6)
  a1.appendAll(Seq(7,8)) // ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8)
  a1.clear // ArrayBuffer()
  val a2 = ArrayBuffer(9, 10) // ArrayBuffer(9, 10)
  a2.insert(0, 8) // ArrayBuffer(8, 9, 10)
  a2.insert(0, 6, 7) // ArrayBuffer(6, 7, 8, 9, 10)
  a2.insertAll(0, Vector(4, 5)) // ArrayBuffer(4, 5, 6, 7, 8, 9, 10)
  a2.prepend(3) // ArrayBuffer(3, 4, 5, 6, 7, 8, 9, 10)
  a2.prepend(1, 2) // ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  a2.prependAll(Array(0)) // ArrayBuffer(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val a3 = ArrayBuffer.range('a', 'h') // ArrayBuffer(a, b, c, d, e, f, g)
  a3.remove(0) // ArrayBuffer(b, c, d, e, f, g)
  a3.remove(2, 3) // ArrayBuffer(b, c, g)
  val a4 = ArrayBuffer.range('a', 'h') // ArrayBuffer(a, b, c, d, e, f, g)
  a4.trimStart(2) // ArrayBuffer(c, d, e, f, g)
  a4.trimEnd(2) // ArrayBuffer(c, d, e)
  
  //If you need a mutable sequential collection that works more like a List (i.e., a linear sequence rather than an indexed sequence), use ListBuffer instead of ArrayBuffer.
  
  //10.9Looping over a collection with foreach
  //The foreach method applies your function to each element of the collection, but it doesn¡¯t return a value.
  //Because it doesn¡¯t return anything, it¡¯s said that it¡¯s used for its ¡°side effect.¡±
  //As an example, a common use of foreach is to output information:
  val x7 = Vector(1, 2, 3)
  x7.foreach { (i: Int) => println(i) }
  x7.foreach(i => println(i))
  x7.foreach(println(_))
  x7.foreach(println)
  
  //10.10Looping over a collection with a for Loop
  //You want to loop over the elements in a collection using a for loop, possibly creating a new collection from the existing collection using the for/yield combination.
  //You can loop over any Traversable type (basically any sequence) using a for loop:
  val fruits1 = Traversable("apple", "banana", "orange")
  for(f <- fruits1) println(f)
  for(f <- fruits1) println(f.toUpperCase())
  //This example shows one approach to using a counter inside a for loop:
  for (i <- 0 until fruits1.size) println(s"element $i is ${fruits1.take(i)}")
  //You can also use the zipWithIndex method when you need a loop counter:
  val fruits2 = List("apple", "banana", "orange")
  for((elem, count) <- fruits2.zipWithIndex){
    println(s"element $count is $elem")
  }
  //Using zip with a Stream is another way to generate a counter:
  for ((elem,count) <- fruits2.zip(Stream from 1)) {
    println(s"element $count is $elem")
  }
  //If you just need to do something N times, using a Range works well:
  for(i <- 1 to 3) println(i)
  
  //The for/yield construct
  //The previous examples show how to operate on each element in a sequence, but they don¡¯t return a value.
  //As with the foreach examples in the previous recipe, they¡¯re used for their side effect.
  val fruits3 = Array("apple", "banana", "orange")
  val newArray = for (e <- fruits3) yield e.toUpperCase
  //If your algorithm is long, or you want to reuse it, first define it in a method (or function):
  def upperReverse(s: String) = {
    // imagine this is a long algorithm
    s.toUpperCase.reverse
  }
  //then use the method with the for/yield loop:
  val newArray1 = for (fruit <- fruits) yield upperReverse(fruit)
  
  //Maps
  //You can also iterate over a Map nicely using a for loop:
  val names = Map("fname" -> "Ed", "lname" -> "Chigliak")
  for ((k,v) <- names) println(s"key: $k, value: $v")
  //As demonstrated in Recipe 3.3, ¡°Using a for Loop with Embedded if Statements(Guards)¡±, you can also combine a for loop with if statements, which are known as guards:
  for {
    fruit <- fruits3
    if fruit.contains("apple")
    if fruit.endsWith("e")
  } println(fruit)
  
  //10.11Using zipWithIndex or zip to create Loop Counters
  //You want to loop over a sequential collection, and you¡¯d like to have access to a counter in the loop, without having to manually create a counter.
  val days = Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
  //you can print the elements in the collection with a counter using the zipWithIndex and foreach methods:
  days.zipWithIndex.foreach {
    case(day, count) => println(s"$count is $day")
  }
  
  //You can also use zipWithIndex with a for loop:
  for ((day, count) <- days.zipWithIndex) {
    println(s"$count is $day")
  }
  
  //When using zipWithIndex, the counter always starts at 0. You can also use the zip method with a Stream to create a counter.
  //This gives you a way to control the starting value:
  for ((day,count) <- days.zip(Stream from 1)) {
    println(s"day $count is $day")
  }
  
  //Discussion
  //When zipWithIndex is used on a sequence, it returns a sequence of Tuple2 elements, as shown in this example:
  val list1 = List("a", "b", "c")
  val zwi = list1.zipWithIndex
  println(zwi)  //List((a,0), (b,1), (c,2))
  //Because zipWithIndex creates a new sequence from the existing sequence, you may want to call view before invoking zipWithIndex, like this:
  val zwi2 = list.view.zipWithIndex
  println(zwi2)  //scala.collection.SeqView[(String, Int),Seq[_]] = SeqViewZ(...)
  //As shown, this creates a lazy view on the original list, so the tuple elements won¡¯t be created until they¡¯re needed. 
  //As mentioned, the zip and zipWithIndex methods both return a sequence of Tuple2 elements.
  //Therefore, your foreach method can also look like this:
  days.zipWithIndex.foreach { d =>
  println(s"${d._2} is ${d._1}")
  }
  
  //10.12Using iterators
  //You want (or need) to work with an iterator in a Scala application.
  //An important part of using an iterator is knowing that it¡¯s exhausted after you use it.
  //As you access each element, you mutate the iterator, and the previous element is discarded.
  val it = Iterator(1,2,3)
  it.foreach(println)
  it.foreach(println)  //no output here
  //An iterator isn¡¯t a collection; instead, it gives you a way to access the elements in a collection, one by one.
  //But an iterator does define many of the methods you¡¯ll see in a normal collection class, including foreach, map, flatMap, collect, etc.
  //You can also convert an iterator to a collection when needed:
  it.toArray
  
  //10.13Transforming one collection to another with for/yield
  //Use the for/yield construct and your algorithm to create the new collection. For instance, starting with a basic collection:
  val a5 = Array(1, 2, 3, 4, 5)
  //You can create a copy of that collection by just ¡°yielding¡± each element (with no algorithm):
  val a6 = for(e <- a5) yield e
  //You can create a new collection where each element is twice the value of the original:  
  val a7 = for(e <- a5) yield e * 2
  
  val fruits4 = Vector("apple", "banana", "lime", "orange")
  val ucFruits = for (e <- fruits4) yield e.toUpperCase
  //Your algorithm can return whatever collection is needed. This approach converts the original collection into a sequence of Tuple2 elements:
  val fruitVector = for (i <- 0 until fruits.length) yield (i, fruits(i))
  //Given a Person class and a list of friend¡¯s names like this:
  case class Person (name: String)
  val friends = Vector("Mark", "Regina", "Matt")
  //a for/yield loop can yield a collection of Person instances:
  for (f <- friends) yield Person(f)
  
  //You can include if statements (guards) in a for comprehension to filter elements:
  val x8 = for (e <- fruits if e.length < 6) yield e.toUpperCase
  //In general, the collection type that¡¯s returned by a for comprehension will be the same type that you begin with.
  //If you begin with an ArrayBuffer, you¡¯ll end up with an ArrayBuffer:
  
  //we can achieve the same result more concisely using the map method.
  //The next recipe demonstrates how to use map to create a new collection from an existing collection.
  
  //10.14Transforming one collection to another with map
  val helpers = Vector("adam", "kim", "melissa")
  val caps = helpers.map(e => e.capitalize)  //the long form
  val caps1 = helpers.map(_.capitalize)
  //The next example shows that an array of String can be converted to an array of Int:
  val names1 = Array("Fred", "Joe", "Jonathan")
  val lengths = names1.map(_.length())
  
  //When your algorithm gets longer, rather than using an anonymous function, define the function
  //(or method) first, and then pass it into map:
  def plusOne(c: Char): Char = (c.toByte+1).toChar
   "HAL".map(plusOne)
   
  //Unlike the for/yield approach shown in the previous recipe, the map method also works well when writing a chain of method calls. 
  val s = " eggs, milk, butter, Coco Puffs "
  val items = s.split(",").map(_.trim())
  //This works because split creates an Array[String], and map applies the trim method to each element in that array before returning the final array.
  //Discussion
  //For simple cases, using map is the same as using a basic for/yield loop:
  //But once you add a guard, a for/yield loop is no longer directly equivalent to just a map method call.
  //If you attempt to use an if statement in the algorithm you pass to a map method, you¡¯ll get a very different result:
  val fruits5 = List("apple", "banana", "lime", "orange", "raspberry")
  val newFruits5 = fruits5.map(f => if (f.length < 6) f.toUpperCase())  //newFruits5: List[Any] = List(APPLE, (), LIME, (), ())
  //You could filter the result after calling map to clean up the result:
  newFruits5.filter(_ != ())  //List[Any] = List(APPLE, LIME)
  //But in this situation, it helps to think of an if statement as being a filter, so the correct solution is to first filter the collection, and then call map:
  fruits5.filter(_.length < 6).map(_.toUpperCase)
  
  //10.15Flattening a list of lists with flatten
  //Use the flatten method to convert a list of lists into a single list.
  val lol = List(List(1,2), List(3,4))
  val res = lol.flatten
  println(res)  //List(1, 2, 3, 4)
  //the flatten method isn¡¯t limited to a List; it works with other sequences (Array, ArrayBuffer, Vector, etc.) as well:
  val couples = List(List("kim", "al"), List("julia", "terry"))
  val people = couples.flatten
  //If you really want to have fun, capitalize each element in the resulting list and then sort the list:
  val people1 = couples.flatten.map(_.capitalize).sorted  //List[String] = List(Al, Julia, Kim, Terry)
  //The flatten method is useful in at least two other situations. First, because a String is
  //a sequence of Char, you can flatten a list of strings into a list of characters:
  val list2 = List("Hello", "world")
  list2.flatten  //List[Char] = List(H, e, l, l, o, w, o, r, l, d)
  //Second, because an Option can be thought of as a container that holds zero or one elements,
  //flatten has a very useful effect on a sequence of Some and None elements.
  //It pulls the values out of the Some elements to create the new list, and drops the None elements:
  val x9 = Vector(Some(1), None, Some(3), None)  //Vector[Option[Int]] = Vector(Some(1), None, Some(3), None)
  x9.flatten  //Vector[Int] = Vector(1, 3)
  
  //10.16Combining map and flatten with flatMap
  //Use flatMap in situations where you run map followed by flatten. The specific situation is this:
  //You¡¯re using map (or a for/yield expression) to create a new collection from an existing collection.
  //The resulting collection is a list of lists.
  //You call flatten immediately after map (or a for/yield expression).
  //When you¡¯re in this situation, you can use flatMap instead
  
  //The next example shows how to use flatMap with an Option. In this example, you¡¯re told that you should calculate the sum of the numbers
  //in a list, with one catch: the numbers are all strings, and some of them won¡¯t convert properly to integers. Here¡¯s the list:
  val bag = List("1", "2", "three", "4", "one hundred seventy five")
  //To solve the problem, you begin by creating a ¡°string to integer¡± conversion method that returns either Some[Int] or None, based on the String it¡¯s given:
  def toInt(in: String): Option[Int] = {
    try {
      Some(Integer.parseInt(in.trim))
    } catch {
      case e: Exception => None
    }
  }
  //With this method in hand, the resulting solution is surprisingly simple:
  bag.flatMap(toInt).sum
  //As you can imagine, once you get the original list down to a List[Int], 
  //you can call any of the powerful collections methods to get what you want:
  bag.flatMap(toInt).takeWhile(_ < 4)  //bag.flatMap(toInt).takeWhile(_ < 4)
  bag.flatMap(toInt).partition(_ > 3)  //(List[Int], List[Int]) = (List(4),List(1, 2))
  
  //10.17Using a filter to filter a collection
  val y = List.range(1, 10)
  val evens = y.filter(_ % 2 == 0)  //evens: List[Int] = List(2, 4, 6, 8)
  
  //As shown, filter returns all elements from a sequence that return true when your function/predicate is called.
  //There¡¯s also a filterNot method that returns all elements from a list for which your function returns false.
  
  //The main methods you can use to filter a collection are listed in Recipe 10.3, and are
  //repeated here for your convenience: collect, diff, distinct, drop, dropWhile, filter,
  //filterNot, find, foldLeft, foldRight, head, headOption, init, intersect, last,
  //lastOption, reduceLeft, reduceRight, remove, slice, tail, take, takeWhile, and union.
  
  //How you filter the elements in your collection is entirely up to your algorithm. 
  val list3 = "apple" :: "banana" :: 1 :: 2 :: Nil
  val strings = list3.filter {
    case s: String => true
    case _ => false
  }  //strings: List[Any] = List(apple, banana)
  
  //10.18Extracting a sequence of elements from a collection
  //There are quite a few collection methods you can use to extract a contiguous list of elements from a sequence,
  //including drop, dropWhile, head, headOption, init, last, lastOption, slice, tail, take, takeWhile.
  val x10 = (1 to 10).toArray  //x: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val y1 = x10.drop(3)  //y1: Array[Int] = Array(4, 5, 6, 7, 8, 9, 10)
  val y2 = x10.dropWhile(_ < 6)  //y2: List[Int] = List(6, 7, 8, 9, 10)
  val y3 = x.dropRight(4)  //y3: Array[Int] = Array(1, 2, 3, 4, 5, 6)
  val y4 = x.take(3)  //y4: Array[Int] = Array(1, 2, 3)
  val y5 = x.takeWhile(_ < 5)  //y5: Array[Int] = Array(1, 2, 3, 4)
  val y6 = x.takeRight(3)  //y6: Array[Int] = Array(8, 9, 10)
  
  //slice(from, until) returns a sequence beginning at the index from until the index
  //until, not including until, and assuming a zero-based index:
  val peeps = List("John", "Mary", "Jane", "Fred")
  peeps.slice(1,3)  //List[String] = List(Mary, Jane)
  
  //Even more methods
  val nums1 = (1 to 5).toArray
  nums1.head // 1
  nums1.headOption // Some(1)
  nums1.init // Array(1, 2, 3, 4)
  nums1.last // 5
  nums1.lastOption // Some(5)
  nums1.tail // Array(2, 3, 4, 5)
  
  //10.19Spliting sequences into subSets(groupBy, patition, etc.)
  //You want to partition a sequence into two or more different sequences (subsets) based on an algorithm or location you define.
  //Use the groupBy, partition, span, or splitAt methods to partition a sequence into subsequences.
  //The sliding and unzip methods can also be used to split sequences into subsequences,
  //though sliding can generate many subsequences, and unzip primarily works on a sequence of Tuple2 elements.
  
  //The groupBy, partition, and span methods let you split a sequence into subsets according to a function,
  //whereas splitAt lets you split a collection into two sequences by providing an index number
  
  val q = List(15, 10, 5, 8, 20, 12)  //List[Int] = List(15, 10, 5, 8, 20, 12)
  val w1 = q.groupBy(_ > 10)   //Map[Boolean,List[Int]] = Map(false -> List(10, 5, 8), true -> List(15, 20, 12))
  val w2 = q.partition(_ > 10)  //(List[Int], List[Int]) = (List(15, 20, 12), List(10, 5, 8))
  val w3 = q.span(_ < 20)  //(List[Int], List[Int]) = (List(15, 10, 5, 8), List(20, 12))
  val w4 = q.splitAt(2)  //(List[Int], List[Int]) = (List(15, 10), List(5, 8, 20, 12))
  
  //The groupBy method partitions the collection into a Map of subcollections based on your function.
  //The true map contains the elements for which your predicate returned true, and the false map contains the elements that returned false.
  
  //The partition, span, and splitAt methods create a Tuple2 of sequences that are of the same type as the original collection.
  //The partition method creates two lists, one containing values for which your predicate returned true, and the other containing the
  //elements that returned false. The span method returns a Tuple2 based on your predicate p, consisting of ¡°the longest prefix of this list
  //whose elements all satisfy p, and the rest of this list.¡± The splitAt method splits the original list according to the element
  //index value you supplied.
  //When a Tuple2 of sequences is returned, its two sequences can be accessed like this:
  val (m1,n1) = q.partition(_ > 10)
  //m1: List[Int] = List(15, 20, 12)
  //n1: List[Int] = List(10, 5, 8)
  //The sequences in the Map that groupBy creates can be accessed like this:
  val groups = q.groupBy(_ > 10)
  //groups: scala.collection.immutable.Map[Boolean,List[Int]] = Map(false -> List(10, 5, 8), true -> List(15, 20, 12))
  val trues = groups(true)  //trues: List[Int] = List(15, 20, 12)
  val falses = groups(false)  //val falses = groups(false)
  
  //The sliding(size, step) method is an interesting creature that can be used to break a sequence into many groups.
  //It can be called with just a size, or both a size and step:
  val nums2 = (1 to 5).toArray
  //size = 2 
  nums2.sliding(2).toList  //List[Array[Int]] = List(Array(1, 2), Array(2, 3), Array(3, 4), Array(4, 5))
  //size = 2, step = 2
  nums2.sliding(2,2).toList  //List[Array[Int]] = List(Array(1, 2), Array(3, 4), Array(5))
  // size = 2, step = 3
  nums2.sliding(2,3).toList  //List[Array[Int]] = List(Array(1, 2), Array(4, 5))
  
  //The unzip method is also interesting. It can be used to take a sequence of Tuple2 values
  //and create two resulting lists: one that contains the first element of each tuple, and
  //another that contains the second element from each tuple:
  val listOfTuple2s = List((1,2), ('a', 'b'))  //listOfTuple2s: List[(AnyVal, AnyVal)] = List((1,2), (a,b))
  val q1 = listOfTuple2s.unzip  //q1: (List[AnyVal], List[AnyVal]) = (List(1, a),List(2, b))
  //As you might guess from its name, the unzip method is the opposite of zip
  
  //10.20Walking through a collection with the reduce and fold methods
  //You want to walk through all of the elements in a sequence, comparing two neighboring elements as you walk through the collection.
  //Solution
  //Use the reduceLeft, foldLeft, reduceRight, and foldRight methods to walk through the elements in a sequence,
  //applying your function to neighboring elements to yield a new result,
  //which is then compared to the next element in the sequence to yield a new result.
  //(Related methods, such as scanLeft and scanRight, are also shown in the Discussion.)
  
  //For example, use reduceLeft to walk through a sequence from left to right (from
  //the first element to the last). reduceLeft starts by comparing the first two elements in
  //the collection with your algorithm, and returns a result. That result is compared with
  //the third element, and that comparison yields a new result. That result is compared to
  //the fourth element to yield a new result, and so on.
  val q2 = Array(12, 6, 15, 2, 20, 9)
  //Given that sequence, use reduceLeft to determine different properties about the collection.
  //The following example shows how to get the sum of all the elements in the sequence:
  q2.reduceLeft(_ + _)
  q2.reduceLeft(_ * _)
  q2.reduceLeft(_ min _)
  q2.reduceLeft(_ max _)
  
  //working with other sequences and types
  val peeps1 = Vector("al", "hannah", "emily", "christina", "aleka")
  peeps1.reduceLeft((x,y) => if (x.length > y.length) x else y)  //String = christina
  peeps1.reduceLeft((x,y) => if (x.length < y.length) x else y)  //String = al
  
  //foldLeft, reduceRight, and foldRight
  //The foldLeft method works just like reduceLeft, but it lets you set a seed value to be
  //used for the first element. The following examples demonstrate a ¡°sum¡± algorithm, first
  //with reduceLeft and then with foldLeft, to demonstrate the difference:
  val q3 = Array(1, 2, 3)
  q3.reduceLeft(_ + _)  //6
  q3.foldLeft(20)(_ + _)  //26
  
  //The reduceRight and foldRight methods work the same as reduceLeft and foldLeft, respectively,
  //but they begin at the end of the collection and work from right to left, i.e., from the end of the collection back to the beginning.
  
  //The difference between reduceLeft and reduceRight
  //In many algorithms, it may not matter if you call reduceLeft or reduceRight. In that case, you can call reduce instead.
  //The reduce Scaladoc states, ¡°The order in which operations are performed on elements is unspecified and may be nondeterministic.¡±
  //But some algorithms will yield a big difference. For example, given this divide function:
  val divide = (x: Double, y: Double) => {
    val result = x / y
    println(s"divided $x by $y to yield $result")
    result
  }
  //scanLeft and scanRight
  //Two methods named scanLeft and scanRight walk through a sequence in a manner similar to reduceLeft and reduceRight,
  //but they return a sequence instead of a single value.
  
  val product = (x: Int, y: Int) => {
    val result = x * y
    result
  }
  val w5 = Array(1, 2, 3)
  val w6 = w5.scanLeft(10)(product)  //Array[Int] = Array(10, 10, 20, 60)
  //w6.foreach { println }
  //As you can see, scanLeft returns a new sequence, rather than a single value.
  //The scanRight method works the same way, but marches through the collection from right to left.
  
  //10.21Extracting unique elements from a sequence
  //You have a collection that contains duplicate elements, and you want to remove the duplicates.
  //Solution:Call the distinct method on the collection:
  val w7 = Vector(1, 1, 2, 3, 3, 4)
  val w8 = w7.distinct  //scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4)
  //If you happen to need a Set, converting the collection to a Set is another way to remove the duplicate elements:
  val w9 = w7.toSet  //scala.collection.immutable.Set[Int] = Set(1, 2, 3, 4)
  //By definition a Set can only contain unique elements, so converting an Array, List, Vector,
  //or other sequence to a Set removes the duplicates. In fact, this is how distinct works.
  
  //10.22Merging sequential collections
  //You want to join two sequences into one sequence, either keeping all of the original elements,
  //finding the elements that are common to both collections, or finding the difference between the two sequences.
  //Solution
  //There are a variety of solutions to this problem, depending on your needs:
  //Use the ++= method to merge a sequence into a mutable sequence.
  //Use the ++ method to merge two mutable or immutable sequences.
  //Use collection methods like union, diff, and intersect.
  
  //Use the ++= method to merge a sequence (any TraversableOnce) into a mutable collection like an ArrayBuffer:
  val b1 = collection.mutable.ArrayBuffer(1,2,3)  //scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
  b1 ++= Seq(4,5,6)  //b1.type = ArrayBuffer(1, 2, 3, 4, 5, 6)
  //Use the ++ method to merge two mutable or immutable collections while assigning the result to a new variable:
  val b2 = Array(1,2,3)
  val b3 = Array(4,5,6)
  val b4 = b2 ++ b3
  println(b4.size)  //6
  //You can also use methods like union and intersect to combine sequences to create a resulting sequence:
  val b5 = Array(1,2,3,4,5)
  val b6 = Array(4,5,6,7,8)
  //elements that are in both collections
  val b7 = b5.intersect(b6)  //Array[Int] = Array(4, 5)
  //all elements from both collections
  val b8 = b5.union(b6)  //Array[Int] = Array(1, 2, 3, 4, 5, 4, 5, 6, 7, 8)
  //distinct elements from both collections
  val b9 = a.union(b).distinct  //Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8)
  //The diff method results depend on which sequence it¡¯s called on:
  val b10 = b5.diff(b6)  //Array[Int] = Array(1, 2, 3)
  val b11 = b6.diff(b5)  //Array[Int] = Array(6, 7, 8)
  //The objects that correspond to most collections also have a concat method:
  val b12 = Array.concat(b5,b6)
  b12.foreach(print)  //1234545678
  
  //If you happen to be working with a List, the ::: method prepends the elements of one list to another list:
  val c1 = List(1,2,3,4)
  val c2 = List(4,5,6,7)
  val c3 = c1 ::: c2  //List[Int] = List(1, 2, 3, 4, 4, 5, 6, 7)
  
  //You can also use the diff method to get the relative complement of two sets.
  //The relative complement of a set A with respect to a set B is the set of elements in B that are not in A.
  val c4 = Array(1,2,3,11,4,12,4,5)
  val c5 = Array(6,7,4,5)
  //the elements in c4 that are not in c5
  val c6 = c4.toSet diff c5.toSet  //scala.collection.immutable.Set[Int] = Set(1, 2, 12, 3, 11)
  // the elements in c5 that are not in c4
  val c7 = c5.toSet diff c4.toSet  //scala.collection.immutable.Set[Int] = Set(6, 7)
  //If desired, you can then sum those results to get the list of elements that are either in the first set or the second set,
  //but not both sets:
  val complement = c6 ++ c7  //complement: scala.collection.immutable.Set[Int] = Set(1, 6, 2, 12, 7, 3, 11)
  //You can also use the -- method to get the same result:
  val c8 = c4.toSet -- c5.toSet  //scala.collection.immutable.Set[Int] = Set(1, 2, 12, 3, 11)
  //Subtracting the intersection of the two sets also yields the same resul
  val c9 = c4.intersect(c5)
  val c10 = c4.toSet -- c9.toSet  //scala.collection.immutable.Set[Int] = Set(1, 2, 12, 3, 11)
  
  //10.23Merging two sequential collections into pairs with zip
  //You want to merge data from two sequential collections into a collection of key/value pairs.
  //Use the zip method to join two sequences into one:
  val women = List("Wilma", "Betty")
  val men = List("Fred", "Barney")
  val couples1 = women zip men  //couples: List[(String, String)] = List((Wilma,Fred), (Betty,Barney))
  //This creates an Array of Tuple2 elements, which is a merger of the two original sequences.
  //This code shows one way to loop over the resulting collection:
  for((wife, husband) <- couples1) {
    println(s"$wife is married to $husband")
  }
  //Once you have a sequence of tuples like couples, you can convert it to a Map, which may be more convenient:
  val couplesMap = couples1.toMap  //couplesMap: scala.collection.immutable.Map[String,String] = Map(Wilma -> Fred, Betty -> Barney)
  //Discussion
  //If one collection contains more items than the other collection, the items at the end of the longer collection will be dropped. 
  //Note that the unzip method is the reverse of zip:
  val(women1, men1) = couples1.unzip
  women1.foreach { println }
  
  //10.24Creating a lazy view on a collection
  //You¡¯re working with a large collection and want to create a ¡°lazy¡± version of it so it will only compute and return results as they are actually needed.
  //In Scala you can optionally create a view on a collection. A view makes the result nonstrict, or lazy.
  //This changes the resulting collection, so when it¡¯s used with a transformer method,
  //the elements will only be calculated as they are accessed, and not ¡°eagerly,¡± as they normally would be. 
  
  //You can see the effect of creating a view on a collection by creating one Range without a view, and a second one with a view:
  (1 to 3).foreach(println)
  (1 to 3).view.foreach(println)
  //Both of those expressions will print 100 elements to the console. Because foreach isn¡¯t a transformer method, the result is unaffected
  //However, calling a map method with and without a view has dramatically different results:
  val d1 = (1 to 3).map { _ * 2 }
  val d2 = (1 to 3).view.map { _ * 2 }
  println(d1)  //Vector(2, 4, 6)
  println(d2)  //SeqViewM(...)
  //These results are different because map is a transformer method. 
  
  //Use cases
  //There are two primary use cases for using a view:
  //Performance
  //To treat a collection like a database view
  
  //10.25Populating a collection with a range
  //You want to populate a List, Array, Vector, or other sequence with a Range.
  //Call the range method on sequences that support it, or create a Range and convert it to the desired sequence.
  //In the first approach, the range method is available on the companion object of supported types
  //like Array, List, Vector, ArrayBuffer, and others:
  Array.range(1, 5)  //Array[Int] = Array(1, 2, 3, 4)
  List.range(0, 10)  //List[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  Vector.range(0, 10, 2)  //collection.immutable.Vector[Int] = Vector(0, 2, 4, 6, 8)
  
  //For some of the collections, such as List and Array, you can also create a Range and convert it to the desired sequence:
  val d3 = (0 until 10).toArray  //Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  val d4 = 1 to 10 by 2 toList  //List[Int] = List(1, 3, 5, 7, 9)
  //Using this approach is useful for some collections, like Set, which don¡¯t offer a range method:
  //val set1 = Set.range(0, 5)  //this won't compile
  val set1 = (0 until 10 by 2).toSet  //scala.collection.immutable.Set[Int] = Set(0, 6, 2, 8, 4)
  //You can also use a Range to create a sequence of characters:
  val letters = ('a' to 'f').toList  //letters: List[Char] = List(a, b, c, d, e, f)
  
  //discussion
  //By using the map method with a Range, you can create a sequence with elements other than type Int or Char:
  val map1 = (1 to 5).map(_ * 2.0)  //map1: collection.immutable.IndexedSeq[Double] = Vector(2.0, 4.0, 6.0, 8.0, 10.0)
  //Using a similar approach, you can also return a sequence of Tuple2 elements:
  val map2 = (1 to 5).map(e => (e,e))  
  //map2: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (2,2), (3,3), (4,4), (5,5))
  //That sequence easily converts to a Map:
  val map3 = (1 to 5).map(e => (e,e)).toMap
  //map3: scala.collection.immutable.Map[Int,Int] = Map(5 -> 5, 1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4)
  
  //10.26Creating and using enumerations
  //You want to use an enumeration (a set of named values that act as constants) in your application.
  //Solution:Extend the scala.Enumeration class to create your enumeration:
  import com.embracesource.scala.collections.Margin._
  // use an enumeration value in a test
  var currentMargin = TOP
  // later in the code ...
  if (currentMargin == TOP) println("working on Top")
  // print all the enumeration values
  Margin.values foreach println
  
  //10.27Tuples, for when you just need a bag of things
  //A tuple gives you a way to store a group of heterogeneous items in a container, which is useful in many situations.
  val d5 = ("Debi", 95)  //d5: (String, Int) = (Debi,95)
  //Notice that it contains two different types. The following example shows a three-element tuple:
  case class Person6(name: String)
  val t = (3, "Three", new Person6("Al"))
  //You can access tuple elements using an underscore construct:
  val res1 = t._1
  val res2 = t._2
  val res3 = t._3  //res3: Person = Person(Al)
  //I usually prefer to assign them to variables using pattern matching:
  val(t1, t2, t3) = (3, "Three", new Person("Al"))
  //A nice feature of this approach is that if you don¡¯t want all of the elements from the tuple,
  //just use the _ wildcard character in place of the elements you don¡¯t want:
  val (t4, t5, _) = t
  //A two-element tuple is an instance of the Tuple2 class, and a tuple with three elements is an instance of the Tuple3 class. 
  
  //you can create a Tuple2 like this:
  val t6 = ("AL", "Alabama")
  //You can also create it using these approaches:
  val t7 = "AL" -> "Alabama"
  println(t7.getClass)  //class scala.Tuple2
  
  //The tuple is an interesting construct. There is no single ¡°Tuple¡± class;
  //instead, the API defines tuple case classes from Tuple2 through Tuple22, meaning that you can have
  //from 2 to 22 elements in a tuple.
  //Though a tuple isn¡¯t a collection, you can treat a tuple as a collection when needed by creating an iterator:
  val t8 = ("AL" -> "Alabama")
  val it1 = t8.productIterator  
  for (e <- it1) println(e)
  
  //10.28Sorting a collection
  //You want to sort a sequential collection.
  //Or, you want to implement the Ordered trait in a custom class so you can use the sorted method,
  //or operators like <, <=, >, and >= to compare instances of your class.
  //The sorted method can sort collections with type Double, Float, Int, and any other type that has an implicit scala.math.Ordering:
  val e1 = List(10, 5, 8, 1, 7).sorted
  val e2 = List("banana", "pear", "apple", "orange").sorted
  //The ¡°rich¡± versions of the numeric classes (like RichInt) and the StringOps class all extend the Ordered trait,
  //so they can be used with the sorted method.
  //The sortWith method lets you provide your own sorting function.
  //The following examples demonstrate how to sort a collection of Int or String in both directions:
  List(10, 5, 8, 1, 7).sortWith(_ < _)
  List(10, 5, 8, 1, 7).sortWith(_ > _)
  List("banana", "pear", "apple", "orange").sortWith(_.length < _.length)
  //If your sorting method gets longer, first declare it as a method:
  def sortByLength(s1: String, s2: String) = {
    println("comparing %s and %s".format(s1, s2))
    s1.length > s2.length
  }
  //Then use it by passing it into the sortWith method:
  List("banana", "pear", "apple").sortWith(sortByLength)
  
  //discussion
  //If the type a sequence is holding doesn¡¯t have an implicit Ordering, you won¡¯t be able to sort it with sorted. 
  
  //10.29Converting a collection to a string with mkString
  //Solution
  //Use the mkString method to print a collection as a String. Given a simple collection:
  val s3 = Array("apple", "banana", "cherry")
  println(s3.mkString)
  println(s3.mkString(", "))
  println(s3.mkString("[", ", ", "]"))  //[apple, banana, cherry]
  
  //If you happen to have a list of lists that you want to convert to a String, such as the
  //following array of arrays, first flatten the collection, and then call mkString:
  val s5 = Array(Array("a", "b"), Array("c", "d"))
  println(s5.flatten.mkString(", "))  //a, b, c, d
}