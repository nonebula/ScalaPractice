package ScalaAtLightSpeed

//https://youtu.be/74Ia6lvVMyc?list=PL8937ckWEfX37TfUEaqXIgilfrUJrcKyW

object FunctionalProgramming extends App {

  // Scala is Object-Oriented
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  } //defining an apply method like above, instance will enact that as apply method when invoked

  val bob = new Person("Bob")
  bob.apply(43) //below is equivalent to this line when doing the above
  bob(43) // INVOKING bob as a function === bob.apply(43)

  /*
    Scala runs on the JVM
    Functional programming - we want to work with first class citizens/first class elements of programming
    Therefore, we want to be able to:
    - compose functions
    - pass functions as args
    - return functions as results

    //To make this work on JVM, Scala uses Function X
    Conclusion: FunctionX = Function1, Function2, ... Function22
   */

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23) // 24
  simpleIncrementer(23) // simpleIncrementer.apply(23)
  // defined a function!

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION_X TYPES

  // function with 2 arguments and a String return type
  val stringConcatenator = new Function2[String, String, String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }

  stringConcatenator("I love", " Scala") // "I love Scala"

  // syntax sugars
  val doubler: Int => Int = (x: Int) => 2 * x
  doubler(4) // 8

  /*
    equivalent to the much longer:

    val doubler: Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(x: Int) = 2 * x
    }
   */

  // higher-order functions: take functions as args/return functions as results
  val aMappedList: List[Int] = List(1,2,3).map(x => x + 1) // HOF
  //map allows passing of a function, anonymous function above passed as argument to the map method.
  val aFlatMappedList = List(1,2,3).flatMap { x =>
    List(x, 2 * x)
    //flatmap concatenates into a single list after results have been broken up into many lists (brings them back together as one)
  } // alternative syntax, same as .flatMap(x => List(x, 2 * x))
  val aFilteredList = List(1,2,3,4,5).filter(_ <= 3) // equivalent to x => x <= 3
  //takes function from [T] to Boolean. Above Int to Boolean

  //can chain applications to methods above because new instance is created instead of using var.
  //perk of immutability

  //Cool demo of the above below.
  // all pairs between the numbers 1, 2, 3 and the letters 'a', 'b', 'c'
  val allPairs = List(1,2,3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  //outputs: 1-a, 1-b, 1-c, 2a, 2b...

  //for used in scala is not a for loop
  //for in case below is a for comp. For comp is an expression attributed to a value:
  // for comprehensions
  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  // equivalent to the map/flatMap chain above, much more concise & efficient
  //VERY useful when working with Collections (above two sections)

  /**
   * Collections
   */

    //Fundamental part of functional programming

  // lists
  val aList = List(1,2,3,4,5)
  //list has head and tail. Head is first, tail is rest
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6 // List(0,1,2,3,4,5,6)
  //can be prepended and appended with elements
  //+: prepends, :+ appends to a list

  // sequences
  val aSequence: Seq[Int] = Seq(1,2,3) // Seq.apply(1,2,3)
  val accessedElement = aSequence(1) // the element at index 1: 2

  // vectors: fast Seq implementation
  //very fast access time, exact same as above in other ways, but very fast for large data
  val aVector = Vector(1,2,3,4,5)

  // sets = no duplicates
  val aSet = Set(1,2,3,4,1,2,3) // Set(1,2,3,4)
  val setHas5 = aSet.contains(5) // false
  val anAddedSet = aSet + 5 // Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3 // Set(1,2,4)
  //to test whether an element is contained in a set is most common usage

  // ranges
  //useful for iteration
  val aRange = 1 to 1000
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2,4,6,8..., 2000)
  //rapid way to achieve something like the above, can use toList, toSet, toSeq etc

  // tuples = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val aPhonebook: Map[String, Int] = Map(
    ("Daniel", 6437812),
    //can also be expressed as below, equivalent to commented out part
    "Jane" -> 327285 // ("Jane", 327285)
  )
  //association between keys and values

}