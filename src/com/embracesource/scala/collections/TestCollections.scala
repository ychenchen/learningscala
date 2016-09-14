package com.embracesource.scala.collections

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
  
  
  
  
  
  
  
  
}