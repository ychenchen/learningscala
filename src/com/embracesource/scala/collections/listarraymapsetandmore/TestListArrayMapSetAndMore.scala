package com.embracesource.scala.collections.listarraymapsetandmore

import scala.collection.mutable.Queue

object TestListArrayMapSetAndMore extends App {
  //List
  //If you¡¯re coming to Scala from Java, you¡¯ll quickly see that despite their names, the Scala List class is nothing like
  //the Java List classes, such as the popular Java ArrayList. The Scala List class is immutable,
  //so its size as well as the elements it refers to can¡¯t change.
  //It¡¯s implemented as a linked list, and is generally thought of in terms of its head, tail, and isEmpty methods.
  //Therefore, most operations on a List involve recursive algorithms, 
  //where the algorithm splits the list into its head and tail components.

  //Array (and ArrayBuffer)
  //A Scala Array is an interesting collection type. The Scaladoc for the Array class states,
  //¡°Arrays are mutable, indexed collections of values.¡± The class is mutable in that its elements can be changed,
  //but once the size of an Array is set, it can never grow or shrink. Although the Array is often demonstrated in Scala examples,
  //and often shows up in the Scala API and third-party APIs, the recommendation with Scala 2.10.x is to use the
  //Vector class as your ¡°go to¡± immutable, indexed sequence class, and ArrayBuffer as your mutable, indexed sequence of choice.
  //In keeping with this suggestion, in my realworld code, I use Vector and ArrayBuffer for those use cases, and then convert them
  //to an Array when needed.

  //Maps
  //A Scala Map is a collection of key/value pairs, like a Java Map, Ruby Hash, or Python dictionary.
  //One big difference between a Scala Map and the Java map classes is that the default Map in Scala is immutable,
  //so if you¡¯re not used to working with immutable collections,
  //this can be a big surprise when you attempt to add, delete, or change elements in the map.
  //The techniques of using both immutable and mutable map traits are demonstrated in this chapter.

  //Sets
  //A Scala Set is also like a Java Set. It¡¯s a collection that contains only unique elements,
  //where ¡°uniqueness¡± is determined by the == method of the type the set contains. If you attempt to add duplicate elements to a set,
  //the set silently ignores the request.
  //Scala has both mutable and immutable versions of its base Set implementation and offers additional set classes for other needs,
  //such as having a sorted set.

  //11.1Different Ways to Create and Populate a List
  // 1
  val list1 = 1 :: 2 :: 3 :: Nil
  //list: List[Int] = List(1, 2, 3)
  // 2
  val list2 = List(1, 2, 3)
  //x: List[Int] = List(1, 2, 3)
  // 3a
  val x1 = List(1, 2.0, 33D, 4000L)
  //x: List[Double] = List(1.0, 2.0, 33.0, 4000.0)
  // 3b
  val x2 = List[Number](1, 2.0, 33D, 4000L)
  //x: List[java.lang.Number] = List(1, 2.0, 33.0, 4000)
  // 4
  val x3 = List.range(1, 10)
  //x: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val x4 = List.range(0, 10, 2)
  //x: List[Int] = List(0, 2, 4, 6, 8)
  // 5
  val x5 = List.fill(3)("foo")
  //x: List[String] = List(foo, foo, foo)
  // 6
  val x6 = List.tabulate(5)(n => n * n)
  //x: List[Int] = List(0, 1, 4, 9, 16)
  // 7
  val x7 = collection.mutable.ListBuffer(1, 2, 3).toList
  //x: List[Int] = List(1, 2, 3)
  // 8
  "foo".toList
  //res0: List[Char] = List(f, o, o)

  //11.2Creating a mutable list
  //Problem:You want to use a mutable list (a LinearSeq, as opposed to an IndexedSeq), but a List isn¡¯t mutable.
  //Solution:Use a ListBuffer, and convert the ListBuffer to a List when needed.
  import scala.collection.mutable.ListBuffer
  var fruits = new ListBuffer[String]()
  // add one element at a time to the ListBuffer
  fruits += "Apple"
  fruits += "Banana"
  fruits += "Orange"
  // add multiple elements
  fruits += ("Strawberry", "Kiwi", "Pineapple")
  // remove one element
  fruits -= "Apple"
  // remove multiple elements
  fruits -= ("Banana", "Orange")
  // remove multiple elements specified by another sequence
  fruits --= Seq("Kiwi", "Pineapple")
  // convert the ListBuffer to a List when you need to
  val fruitsList = fruits.toList

  //don¡¯t use ListBuffer if you want to access elements arbitrarily, such as accessing items
  //by index (like list(10000)); use ArrayBuffer instead. 
  //Although you can¡¯t modify the elements in a List, you can create a new List from an
  //existing one, typically prepending items to the original list with the :: method:
  val x8 = List(2)
  //x8: List[Int] = List(2)
  val y = 1 :: x8
  //y: List[Int] = List(1, 2)
  val z = 0 :: y
  //z: List[Int] = List(0, 1, 2)

  //11.3Adding Elements to a List
  //¡°How do I add elements to a List?¡± is a bit of a trick question, because a List is immutable,
  //so you can¡¯t actually add elements to it. If you want a List that is constantly changing,
  //use a ListBuffer (as described in Recipe 11.2), and then convert it to a List when necessary.

  //To work with a List, the general approach is to prepend items to the list with the ::
  //method while assigning the results to a new List, just as val x8 has shown

  //Rather than continually reassigning the result of this operation to a new variable, you
  //can declare your variable as a var, and reassign the result to it:
  var x9 = List(2)
  x9 = 1 :: x9

  //Though using :: is very common, there are additional methods that let you prepend or append single elements to a List:
  val x10 = 1 +: x9 //List[Int] = List(0, 1)
  val x11 = x9 :+ 2 //List[Int] = List(1, 2)

  //11.4Deleting Elements from a List (or ListBuffer)
  //A List is immutable, so you can¡¯t delete elements from it,
  //but you can filter out the elements you don¡¯t want while you assign the result to a new variable:
  val originalList = List(5, 1, 4, 3, 2)
  val newList = originalList.filter(_ > 2)

  //If you¡¯re going to be modifying a list frequently, it may be better to use a ListBuffer instead of a List.
  //A ListBuffer is mutable, so you can remove items from it using all the methods for mutable sequences shown in Chapter 10. 
  import scala.collection.mutable.ListBuffer
  val x12 = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9)
  x12 -= 5
  x12 -= (2, 3)
  x12.remove(0) //You can delete elements by position
  //You can use remove to delete from a given starting position and provide the number of elements to delete
  x12.remove(1, 3)
  //You can also use --= to delete multiple elements that are specified in another collection
  val x13 = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9)
  x13 --= Seq(1, 2, 3)

  //11.5Merging (Concatenating) Lists
  //Merge two lists using the ++, concat, or ::: methods. Given these two lists:
  val a1 = List(1, 2, 3)
  val a2 = List(4, 5, 6)
  val a3 = a1 ++ a2
  val a4 = a1 ::: a2
  val a5 = List.concat(a1, a2)

  //11.6Using Stream, a Lazy Version of a List
  //You want to use a collection that works like a List but invokes its transformer methods (map, filter, etc.) lazily.
  //A Stream is like a List, except that its elements are computed lazily,
  //in a manner similar to how a view creates a lazy version of a collection. Because Stream elements are computed lazily,
  //a Stream can be long ... infinitely long. Like a view, only the elements that are accessed are computed.
  //Other than this behavior, a Stream behaves similar to a List.
  //Just like a List can be constructed with ::, a Stream can be constructed with the #::method,
  //using Stream.empty at the end of the expression instead of Nil:
  val stream = 1 #:: 2 #:: 3 #:: Stream.empty //stream: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  val stream1 = (1 to 100000000).toStream
  println(stream1) //Stream(1, ?)
  stream.head //res0: Int = 1
  stream.tail //res1: scala.collection.immutable.Stream[Int] = Stream(2, ?)
  //The ? symbol is the way a lazy collection shows that the end of the collection hasn¡¯t been evaluated yet.
  val stream2 = stream1.filter(_ > 2)
  println(stream2) //Stream(3, ?)

  //Using a Stream gives you a chance to specify a huge list, and begin working with its elements:
  stream(2) //returns 3

  //11.7Different Ways to Create and Update an Array
  //There are many different ways to define and populate an Array. You can create an array
  //with initial values, in which case Scala can determine the array type implicitly:
  val a6 = Array(1, 2, 3)
  //a6: Array[Int] = Array(1, 2, 3)
  val fruits1 = Array("Apple", "Banana", "Orange")
  //fruits1: Array[String] = Array(Apple, Banana, Orange)
  //If you don¡¯t like the type Scala determines, you can assign it manually:
  // scala makes this Array[Double]
  val a7 = Array(1, 2.0, 33D, 400L)
  //a7: Array[Double] = Array(1.0, 2.0, 33.0, 400.0)
  // manually override the type
  val a8 = Array[Number](1, 2.0, 33D, 400L)
  //a8: Array[java.lang.Number] = Array(1, 2.0, 33.0, 400)
  //You can define an array with an initial size and type, and then populate it later:
  // create an array with an initial size
  val fruits2 = new Array[String](3)
  // somewhere later in the code ...
  fruits2(0) = "Apple"
  fruits2(1) = "Banana"
  fruits2(2) = "Orange"
  //You can create a var reference to an array in a class, and then assign it later:
  // this uses a null. don't do this in the real world
  var fruits3: Array[String] = _
  // later ...
  fruits3 = Array("apple", "banana")
  //The following examples show a handful of other ways to create and populate an Array:
  val a9 = Array.range(1, 10)
  //a9: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val a10 = Array.range(0, 10, 2)
  //a10: Array[Int] = Array(0, 2, 4, 6, 8)
  val a11 = Array.fill(3)("foo")
  //a11: Array[String] = Array(foo, foo, foo)
  val a12 = Array.tabulate(5)(n => n * n)
  //a12: Array[Int] = Array(0, 1, 4, 9, 16)
  val a13 = List(1, 2, 3).toArray
  //a13: Array[Int] = Array(1, 2, 3)
  "Hello".toArray
  //res0: Array[Char] = Array(H, e, l, l, o)

  //The Array is an interesting creature: It¡¯s mutable in that its elements can be changed,
  //but it¡¯s immutable in that its size cannot be changed.
  //Just as you access an array element by index, you update elements in a similar way:
  a13(0) = 100
  println(a13(0))
  //11.8Creating an Array Whose Size Can Change(ArrayBuffer)
  //An Array is mutable in that its elements can change, but its size can¡¯t change. To create a mutable,
  //indexed sequence whose size can change, use the ArrayBuffer class.
  //To use an ArrayBuffer, import it into scope and then create an instance.
  //You can declare an ArrayBuffer without initial elements, and then add them later:
  import scala.collection.mutable.ArrayBuffer
  var characters = ArrayBuffer[String]()
  characters += "Ben"
  characters += "Jerry"
  characters += "Dale"
  //You can add elements when you create the ArrayBuffer, and continue to add elements later:
  // initialize with elements
  val characters1 = collection.mutable.ArrayBuffer("Ben", "Jerry")
  // add one element
  characters1 += "Dale"
  // add two or more elements (method has a varargs parameter)
  characters1 += ("Gordon", "Harry")
  // add multiple elements with any TraversableOnce type
  characters1 ++= Seq("Andy", "Big Ed")
  // append one or more elements (uses a varargs parameter)
  characters1.append("Laura", "Lucy")

  //11.9Deleting Array and ArrayBuffer Elements
  //An ArrayBuffer is a mutable sequence, so you can delete elements with the usual -=, --=, remove, and clear methods.
  //You can remove one or more elements with -=:
  import scala.collection.mutable.ArrayBuffer
  val a14 = ArrayBuffer('a', 'b', 'c', 'd', 'e')
  // remove one element
  a14 -= 'a'
  // remove multiple elements (methods defines a varargs param)
  a14 -= ('b', 'c')
  //Use --= to remove multiple elements that are declared in another collection (any collection that extends TraversableOnce):
  val a15 = ArrayBuffer('a', 'b', 'c', 'd', 'e')
  a15 --= Seq('a', 'b')
  a15 --= Array('c')
  a15 --= Set('d')
  //Use the remove method to delete one element by its position in the ArrayBuffer,
  //or a series of elements beginning at a starting position:
  val a16 = ArrayBuffer('a', 'b', 'c', 'd', 'e', 'f')
  //a16: scala.collection.mutable.ArrayBuffer[Char] = ArrayBuffer(a, b, c, d, e, f)
  a16.remove(0)
  //res1: scala.collection.mutable.ArrayBuffer[Char] = ArrayBuffer(b, c, d, e, f)
  a16.remove(1, 3)
  //res2: scala.collection.mutable.ArrayBuffer[Char] = ArrayBuffer(b, f)

  //The clear method removes all the elements from an ArrayBuffer:
  var a17 = ArrayBuffer(1, 2, 3, 4, 5)
  //a: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 5)
  a17.clear
  //res0: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()

  //11.10Sorting arrays
  //If you¡¯re working with an Array that holds elements that have an implicit Ordering,
  //you can sort the Array in place using the scala.util.Sorting.quickSort method.
  //For example, because the String class has an implicit Ordering, it can be used with quickSort:
  val fruits4 = Array("cherry", "apple", "banana")
  //fruits4: Array[String] = Array(cherry, apple, banana)
  //scala.util.Sorting.quickSort(fruits4)  //fruits4: Array[String] = Array(apple, banana, cherry)
  //This example works because the String class (via StringOps) has an implicit Ordering.
  //Sorting.quickSort can also sort arrays with the base numeric types like Double, Float, and Int,
  //because they also have an implicit Ordering.
  //Other solutions
  //If the type an Array is holding doesn¡¯t have an implicit Ordering, you can either modify it to mix in the Ordered trait
  //(which gives it an implicit Ordering), or sort it using the sorted, sortWith, or sortBy methods.
  //These approaches are shown in Recipe 10.29.
  //Also, there are no unique sorting approaches for an ArrayBuffer, so see Recipe 10.29 for an example of how to sort it as well.

  //11.11Creating Multidimensional Arrays
  //There are two main solutions:
  //Use Array.ofDim to create a multidimensional array. You can use this approach to create arrays of up to five dimensions.
  //With this approach you need to know the number of rows and columns at creation time.
  //Create arrays of arrays as needed.
  //Using Array.ofDim
  //Use the Array.ofDim method to create the array you need:
  val rows = 2
  val cols = 3
  val a18 = Array.ofDim[String](rows, cols)
  //After declaring the array, add elements to it:
  a18(0)(0) = "a"
  a18(0)(1) = "b"
  a18(0)(2) = "c"
  a18(1)(0) = "d"
  a18(1)(1) = "e"
  a18(1)(2) = "f"
  //Access the elements using parentheses, similar to a one-dimensional array:
  println(a18(0)(2)) //c

  //Using an array of arrays
  val a19 = Array(Array("a", "b", "c"), Array("d", "e", "f"))
  println(a19(0))
  println(a19(0)(0)) //a

  //You can declare your variable as a var
  var a20 = Array(Array("a", "b", "c"), Array("d", "e", "f"))
  a20 ++= Array(Array("g", "h"))

  //11.12Creating Maps
  //To use an immutable map, you don¡¯t need an import statement, just create a Map:
  val states1 = Map("AL" -> "Alabama", "AK" -> "Alaska")
  //To create a mutable map, either use an import statement to bring it into scope,
  //or specify the full path to the scala.collection.mutable.Map class when you create an instance.
  //You can define a mutable Map that has initial elements:
  var states2 = collection.mutable.Map("AL" -> "Alabama")
  //You can also create an empty, mutable Map initially, and add elements to it later:
  var states3 = collection.mutable.Map[String, String]()
  states3 += ("AL" -> "Alabama") //res0: scala.collection.mutable.Map[String,String] = Map(AL -> Alabama)
  //Because you can also declare a Tuple2 as ("AL", "Alabama"), you may also see maps created like this:
  val states4 = Map(("AL", "Alabama"), ("AK", "Alaska"))

  //11.13Choosing a map implemention
  //Scala has a wealth of map types to choose from, and you can even use Java map classes.
  //If you¡¯re looking for a basic map class, where sorting or insertion order doesn¡¯t matter,
  //you can either choose the default, immutable Map, or import the mutable Map, as shown in the previous recipe.
  //If you want a map that returns its elements in sorted order by keys, use a SortedMap:
  import scala.collection.SortedMap
  val grades = SortedMap("Kim" -> 90,
    "Al" -> 85,
    "Melissa" -> 95,
    "Emily" -> 91,
    "Hannah" -> 92)
  //If you want a map that remembers the insertion order of its elements, use a LinkedHashMap or ListMap.
  //Scala only has a mutable LinkedHashMap, and it returns its elements in the order you inserted them:
  import scala.collection.mutable.LinkedHashMap
  var states5 = LinkedHashMap("IL" -> "Illinois")
  states5 += ("KY" -> "Kentucky")
  states5 += ("TX" -> "Texas")

  //Scala has both mutable and immutable ListMap classes.
  //They return elements in the opposite order in which you inserted them,
  //as though each insert was at the head of the map (like a List):
  import scala.collection.mutable.ListMap
  var states6 = ListMap("IL" -> "Illinois")
  states6 += ("KY" -> "Kentucky")
  states6 += ("TX" -> "Texas")

  //11.14. Adding, Updating, and Removing Elements with a Mutable Map
  //Add elements to a mutable map by simply assigning them, or with the += method.
  //Remove elements with -= or --=. Update elements by reassigning them.
  //Given a new, mutable Map:
  var states7 = scala.collection.mutable.Map[String, String]()
  //You can add an element to a map by assigning a key to a value:
  states7("AK") = "Alaska"
  //You can also add elements with the += method:
  states7 += ("AL" -> "Alabama")
  //Add multiple elements at one time with +=:
  states7 += ("AR" -> "Arkansas", "AZ" -> "Arizona")
  //Add multiple elements from another collection using ++=:
  states7 ++= List("CA" -> "California", "CO" -> "Colorado")
  //Remove a single element from a map by specifying its key with the -= method:
  states7 -= "AR"
  //Remove multiple elements by key with the -= or --= methods:
  states7 -= ("AL", "AZ")
  // remove multiple with a List of key
  //states7 --= List("AL", "AZ")
  //Update elements by reassigning their key to a new value:
  states7("AK") = "Alaska, A Really Big State"

  //The methods shown in the Solution demonstrate the most common approaches.
  //You can also use put to add an element (or replace an existing element);
  //retain to keep only the elements in the map that match the predicate you supply;
  //remove to remove an element by its key value; and clear to delete all elements in the map. 

  //11.15Adding, Updating, and Removing Elements with Immutable Maps
  //Use the correct operator for each purpose, remembering to assign the results to a new map.
  //To be clear about the approach, the following examples use an immutable map with a
  //series of val variables. First, create an immutable map as a val:
  val b = Map("AL" -> "Alabama")
  //Add one or more elements with the + method, assigning the result to a new Map variable during the process:
  // add one element
  val b1 = b + ("AK" -> "Alaska")
  //b1: scala.collection.immutable.Map[String,String] = Map(AL -> Alabama, AK -> Alaska)
  // add multiple elements
  val b2 = b1 + ("AR" -> "Arkansas", "AZ" -> "Arizona")
  //To update a key/value pair with an immutable map, reassign the key and value while using the + method,
  //and the new values replace the old:
  val b3 = b2 + ("AR" -> "banana")
  //To remove one element, use the - method:
  val b4 = b3 - "AR"
  val b5 = b4 - "AZ" - "AL"

  //You can also declare an immutable map as a var. 
  //It¡¯s important to understand that when you create an immutable map as a var, you still
  //have an immutable map. For instance, you can¡¯t reassign an element in the map:

  //11.16Accessing Map Values
  //Given a sample map:
  val states8 = Map("AL" -> "Alabama", "AK" -> "Alaska", "AZ" -> "Arizona")
  //Access the value associated with a key in the same way you access an element in an array:
  val az = states8("AZ")
  //However, be careful, because if the map doesn¡¯t contain the requested key,
  //a java.util.NoSuchElementException exception is thrown:
  //val s = states8("FOO")  //java.util.NoSuchElementException: key not found: FOO
  //One way to avoid this problem is to create the map with the withDefaultValue method.
  //As the name implies, this creates a default value that will be returned by the map whenever a key isn¡¯t found:
  val states9 = Map("AL" -> "Alabama").withDefaultValue("Not found")
  //Another approach is to use the getOrElse method when attempting to find a value.
  //It returns the default value you specify if the key isn¡¯t found:
  val s = states8.getOrElse("FOO", "No such state")

  //11.17Traversing a Map
  //There are several different ways to iterate over the elements in a map. Given a sample map:
  val ratings = Map("Lady in the Water" -> 3.0,
    "Snakes on a Plane" -> 4.0,
    "You, Me and Dupree" -> 3.5)
  //my preferred way to loop over all of the map elements is with this for loop syntax:
  for ((k, v) <- ratings) println(s"key: $k, value: $v")
  //Using a match expression with the foreach method is also very readable:
  ratings.foreach {
    case (movie, rating) => println(s"key: $movie, value: $rating")
  }
  //The following approach shows how to use the Tuple syntax to access the key and value fields:
  ratings.foreach(x => println(s"key: ${x._1}, value: ${x._2}"))
  //If you just want to use the keys in the map, the keys method returns an Iterable you can use:
  ratings.keys.foreach((movie) => println(movie))
  //For simple examples like this, that expression can be reduced as follows:
  ratings.keys.foreach(println)
  //In the same way, use the values method to iterate over the values in the map:
  ratings.values.foreach((rating) => println(rating))

  //Operating on map values
  //If you want to traverse the map to perform an operation on its values, the mapValues method may be a better solution.
  //It lets you perform a function on each map value, and returns the modified map:
  var b6 = collection.mutable.Map(1 -> "a", 2 -> "b")
  //b6: scala.collection.mutable.Map[Int,String] = Map(2 -> b, 1 -> a)
  val b7 = b6.mapValues(_.toUpperCase)
  //b7: scala.collection.Map[Int,String] = Map(2 -> B, 1 -> A)

  //The transform method gives you another way to create a new map from an existing map.
  //Unlike mapValues, it lets you use both the key and value to write a transformation method:
  val b8 = Map(1 -> 10, 2 -> 20, 3 -> 30)
  val newMap = b8.transform((k, v) => k + v)

  //11.18Getting the Keys or Values from a Map
  //To get the keys, use keySet to get the keys as a Set, keys to get an Iterable, or keysIterator to get the keys as an iterator:
  val states10 = Map("AK" -> "Alaska", "AL" -> "Alabama", "AR" -> "Arkansas")
  //states: scala.collection.immutable.Map[String,String] = Map(AK -> Alaska, AL -> Alabama, AR -> Arkansas)
  states10.keySet
  //res0: scala.collection.immutable.Set[String] = Set(AK, AL, AR)
  states10.keys
  //res1: Iterable[String] = Set(AK, AL, AR)
  states10.keysIterator
  //res2: Iterator[String] = non-empty iterator
  //To get the values from a map, use the values method to get the values as an Iterable, 
  //or valuesIterator to get them as an Iterator:
  states10.values
  //res0: Iterable[String] = MapLike(Alaska, Alabama, Arkansas)
  states10.valuesIterator
  //res1: Iterator[String] = non-empty iterator
  //As shown in these examples, keysIterator and valuesIterator return an iterator from the map data.

  //11.19Reversing Keys and Values
  //You can reverse the keys and values of a map with a for comprehension, being sure to assign the result to a new variable:
  val reverseMap = for ((k, v) <- states10) yield (v, k)
  //But be aware that values don¡¯t have to be unique and keys must be, so you might lose some content. 

  //11.20Testing for the Existence of a Key or Value in a Map
  //To test for the existence of a key in a map, use the contains method:
  if (states10.contains("AK")) println("Found AK") else println("No AK")
  //To test whether a value exists in a map, use the valuesIterator method to search for the value using exists and contains:
  states10.valuesIterator.exists(_.contains("Alaska"))

  //11.21Filtering a Map
  //You want to filter the elements contained in a map, either by directly modifying a mutable map,
  //or by applying a filtering algorithm on an immutable map to create a new map.
  //Solution
  //Use the retain method to define the elements to retain when using a mutable map,
  //and use filterKeys or filter to filter the elements in a mutable or immutable map,
  //remembering to assign the result to a new variable.
  //Mutable maps
  //You can filter the elements in a mutable map using the retain method to specify which elements should be retained:
  var b9 = collection.mutable.Map(1 -> "a", 2 -> "b", 3 -> "c")
  b9.retain((k, v) => k > 1)
  //res0: scala.collection.mutable.Map[Int,String] = Map(2 -> b, 3 -> c)
  //your algorithm can test both the key and value of each element to decide which elements to retain in the map  
  b9.transform((k, v) => v.toUpperCase)
  //Mutable and immutable maps
  //When working with a mutable or immutable map, you can use a predicate with the filterKeys methods
  //to define which map elements to retain. When using this method, remember to assign the filtered result to a new variable:
  val b10 = b9.filterKeys(_ > 2)
  //In an interesting use, you can also use a Set with filterKeys to define the elements to retain:
  val b11 = b9.filterKeys(Set(2, 3))
  //The filter method provides your predicate a Tuple2, so you can access the key and value as shown in these examples:
  // access the key
  b9.filter((t) => t._1 > 1)
  // access the value
  b9.filter((t) => t._2 == "c")
  //The take method lets you ¡°take¡± (keep) the first N elements from the map:
  b9.take(2)

  //11.22Sorting an Existing Map by Key and Value
  //Given a basic, immutable Map:
  val grades1 = Map("Kim" -> 90,
    "Al" -> 85,
    "Melissa" -> 95,
    "Emily" -> 91,
    "Hannah" -> 92)
  //grades1: scala.collection.immutable.Map[String,Int] = Map(Hannah -> 92, Melissa -> 95, Kim -> 90, Emily -> 91, Al -> 85)
  //You can sort the map by key, from low to high, using sortBy:
  //import scala.collection.immutable.ListMap
  ListMap(grades.toSeq.sortBy(_._1): _*)
  //res0: scala.collection.immutable.ListMap[String,Int] = Map(Al -> 85, Emily -> 91, Hannah -> 92, Kim -> 90, Melissa -> 95)
  //You can also sort the keys in ascending or descending order using sortWith:
  // low to high
  ListMap(grades.toSeq.sortWith(_._1 < _._1): _*)
  // high to low
  ListMap(grades.toSeq.sortWith(_._1 > _._1): _*)
  //You can sort the map by value using sortBy:
  ListMap(grades.toSeq.sortBy(_._2): _*)
  //You can also sort by value in ascending or descending order using sortWith
  // low to high
  ListMap(grades.toSeq.sortWith(_._2 < _._2): _*)

  //In all of these examples, you¡¯re not sorting the existing map; the sort methods result in a new sorted map,
  //so the output of the result needs to be assigned to a new variable.
  //Also, you can use either a ListMap or a LinkedHashMap in these recipes. 
  val b12 = collection.mutable.LinkedHashMap(grades.toSeq.sortBy(_._1): _*)
  b12.foreach(println)

  //About that _*
  //It¡¯s used to convert the data so it will be passed as multiple parameters to the ListMap or LinkedHashMap.
  def printAll(strings: String*) {
    strings.foreach(println)
  }
  // a sequence of strings
  val fruits5 = List("apple", "banana", "cherry")
  //you won¡¯t be able to pass that List into printAll; it will fail
  //printAll(fruits5)
  //But you can use _* to adapt the List to work with printAll, like this:
  // this works
  printAll(fruits: _*)

  //11.23Finding the largest key or value in a map
  //Use the max method on the map, or use the map¡¯s keysIterator or valuesIterator with other approaches, depending on your needs.
  //For example, given this map:
  val grades2 = Map("Al" -> 80, "Kim" -> 95, "Teri" -> 85, "Julia" -> 90)
  val max1 = grades2.max //(Teri,85)
  println(max1)
  //You can also call keysIterator to get an iterator over the map keys, and call its max method:  
  grades2.keysIterator.max //res1: String = Teri
  //You can find the same maximum by getting the keysIterator and using reduceLeft:
  grades2.keysIterator.reduceLeft((x, y) => if (x > y) x else y)
  //This approach is flexible, because if your definition of ¡°largest¡± is the longest string, you can compare string lengths instead:
  grades2.keysIterator.reduceLeft((x, y) => if (x.length > y.length) x else y) //res3: String = Julia
  //Because the values in the map are of type Int in this example, you can use this simple approach to get the largest value:
  grades2.valuesIterator.max //res4: Int = 95
  //You can also use the reduceLeft approach, if you prefer:
  grades2.valuesIterator.reduceLeft(_ max _)
  //You can also compare the numbers yourself, which is representative of what you may need to do with more complex types:
  grades2.valuesIterator.reduceLeft((x, y) => if (x > y) x else y)

  //11.24Adding an element to a set
  //You want to add elements to a mutable set, or create a new set by adding elements to an immutable set.

  //Mutable set
  //Add elements to a mutable Set with the +=, ++=, and add methods:
  // use var with mutable
  var set = scala.collection.mutable.Set[Int]()
  //set: scala.collection.mutable.Set[Int] = Set()
  // add one element
  set += 1
  //res0: scala.collection.mutable.Set[Int] = Set(1)
  // add multiple elements
  set += (2, 3)
  //res1: scala.collection.mutable.Set[Int] = Set(2, 1, 3)
  // notice that there is no error when you add a duplicate element
  set += 2
  //res2: scala.collection.mutable.Set[Int] = Set(2, 6, 1, 4, 3, 5)
  // add elements from any sequence (any TraversableOnce)
  set ++= Vector(4, 5)
  //res3: scala.collection.mutable.Set[Int] = Set(2, 1, 4, 3, 5)
  set.add(6)
  //res4: Boolean = true
  set.add(5)
  //res5: Boolean = false
  //The last two examples demonstrate a unique characteristic of the add method on a set:
  //It returns true or false depending on whether or not the element was added. The other
  //methods silently fail if you attempt to add an element that¡¯s already in the set.
  //You can test to see whether a set contains an element before adding it:
  set.contains(5)
  //But as a practical matter, I use += and ++=, and ignore whether the element was already in the set.
  //Whereas the first example demonstrated how to create an empty set, you can also add
  //elements to a mutable set when you declare it, just like other collections:
  var set1 = scala.collection.mutable.Set(1, 2, 3)
  //set1: scala.collection.mutable.Set[Int] = Set(2, 1, 3)

  //Immutable set
  //The following examples show how to create a new immutable set by adding elements to an existing immutable set.
  //First, create an immutable set:
  val s1 = Set(1, 2)
  //s1: scala.collection.immutable.Set[Int] = Set(1, 2)
  //Create a new set by adding elements to a previous set with the + and ++ methods:
  // add one element
  val s2 = s1 + 3
  //s2: scala.collection.immutable.Set[Int] = Set(1, 2, 3)
  // add multiple elements (+ method has a varargs field)
  val s3 = s2 + (4, 5)
  //s3: scala.collection.immutable.Set[Int] = Set(5, 1, 2, 3, 4)
  // add elements from another sequence
  val s4 = s3 ++ List(6, 7)
  //s4: scala.collection.immutable.Set[Int] = Set(5, 1, 6, 2, 7, 3, 4)

  //11.25Deleting Elements from sets
  //Mutable and immutable sets are handled differently
  //Mutable set
  //When working with a mutable Set, remove elements from the set using the -= and --= methods, as shown in the following examples:
  var set2 = scala.collection.mutable.Set(1, 2, 3, 4, 5)
  // one element
  set2 -= 1
  // two or more elements (-= has a varags field)
  set2 -= (2, 3)
  // multiple elements defined in another sequence
  set2 --= Array(4, 5)
  //You can also use other methods like retain, clear, and remove, depending on your needs:  
  set2.retain(_ > 2)
  set2.remove(2) //res: Boolean = false
  set2.clear
  //As shown, the remove method provides feedback as to whether or not any elements were removed.
  //Immutable set
  //By definition, when using an immutable Set you can¡¯t remove elements from it, but you can use the - and -- operators
  //to remove elements while assigning the result to a new variable:
  val s5 = Set(1, 2, 3, 4, 5, 6)
  val s6 = s5 - 1
  val s7 = s6 - (2, 3)
  val s8 = s7 -- Array(4, 5)

  //You can also use all of the filtering methods shown in Chapter 10. For instance, you can use the filter or take methods:
  val s9 = Set(1, 2, 3, 4, 5, 6)
  val s10 = s9.filter(_ > 3)
  val firstTwo = s10.take(2)

  //11.26Using sortable sets
  //To retrieve values from a set in sorted order, use a SortedSet. To retrieve elements from
  //a set in the order in which elements were inserted, use a LinkedHashSet.
  val s11 = scala.collection.SortedSet(10, 4, 8, 2) //s: scala.collection.SortedSet[Int] = TreeSet(2, 4, 8, 10)
  val s12 = scala.collection.SortedSet("cherry", "kiwi", "apple") //s: scala.collection.SortedSet[String] = TreeSet(apple, cherry, kiwi)
  //A LinkedHashSet saves elements in the order in which they were inserted:
  var s13 = scala.collection.mutable.LinkedHashSet(10, 4, 8, 2) //s: scala.collection.mutable.LinkedHashSet[Int] = Set(10, 4, 8, 2)
  //The SortedSet is available only in an immutable version. If you need a mutable version use the java.util.TreeSet.
  //The LinkedHashSet is available only as a mutable collection.
  //The examples shown in the Solution work because the types used in the sets have an implicit Ordering.
  //Custom types won¡¯t work unless you also provide an implicit Ordering. 

  //11.27Using a Queue
  //A queue is a first-in, first-out (FIFO) data structure. Scala offers both an immutable queue and mutable queue. 
  //You can also create a queue with initial elements:
  val q1 = Queue(1, 2, 3)
  //Once you have a mutable queue, add elements to it using +=, ++=, and enqueue
  // create an empty queue
  var q2 = new Queue[String]
  // add elements to the queue in the usual ways
  q2 += "apple"
  q2 += ("kiwi", "banana")
  q2 ++= List("cherry", "coconut")
  q2.enqueue("pineapple")
  println(q2) //TestListArrayMapSetAndMore.scala

  // take an element from the head of the queue
  val next = q2.dequeue
  println(next) //apple
  q2.dequeueFirst(_.startsWith("b"))
  q2.dequeueAll(_.length > 6)

  //11.28Using a Stack
  //A stack is a last-in, first-out (LIFO) data structure.
  //In most programming languages you add elements to a stack using a push method, and take elements off the stack with pop,
  //and Scala is no different. Scala has both immutable and mutable versions of a stack, as well as an ArrayStack
  //Create an empty, mutable stack of any data type:
  import scala.collection.mutable.Stack
  var ints = Stack[Int]()
  var fruits6 = Stack[String]()
  case class Person(var name: String)
  var people = Stack[Person]()
  //You can also populate a stack with initial elements when you create it:
  val ints1 = Stack(1, 2, 3)
  
  var fruits7 = Stack[String]()
  fruits7.push("apple")
  fruits7.push("banana")  //scala.collection.mutable.Stack[String] = Stack(banana, apple)
  fruits7.push("coconut", "orange", "pineapple")  //Stack(pineapple, orange, coconut, banana, apple)
  val next1 = fruits7.pop  //pipeapple
  
  //Stack extends from Seq, so you can inspect it with the usual methods:
  fruits7.size
  fruits7.isEmpty
  fruits7.clear
  
  //11.29Using a Range
  //Ranges are often used to populate data structures, and to iterate over for loops.
  //Ranges provide a lot of power with just a few methods
  //You can use ranges to create and populate seq
  val y1 = (1 to 10).toList  //y1: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val y2 = (1 to 10).toArray  //y2: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val y3 = (1 to 10).toSet  //y3: scala.collection.immutable.Set[Int] = Set(5, 10, 1, 6, 9, 2, 7, 3, 8, 4)
  
  //Some sequences have a range method in their objects to perform the same function:
  val y4 = Array.range(1, 10)
  val y5 = Vector.range(1, 10)
  val y6 = List.range(1, 10, 2)  //y6: List[Int] = List(0, 2, 4, 6, 8)
  //In addition to the approaches shown, a Range can be combined with the map method to populate a collection:
  val y7 = (1 to 5).map { e => (e + 1.1) * 2 }
  //While discussing ways to populate collections, the tabulate method is another nice approach:
  val y8 = List.tabulate(5)(_ + 1)  //y8: List[Int] = List(1, 2, 3, 4, 5)
  val y9 = Vector.tabulate(5)(_ * 2)  //u9: scala.collection.immutable.Vector[Int] = Vector(0, 2, 4, 6, 8)
}