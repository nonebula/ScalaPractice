package ScalaAtLightSpeed

//https://youtu.be/ttpWoj98-aQ?list=PL8937ckWEfX37TfUEaqXIgilfrUJrcKyW

object PatternMatching extends App {

  // switch expression
  val anInteger = 55 //returns 55th
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }
  // Pattern Match is an EXPRESSION
  //Can be reduced to a value
  //therefore we can assign to a value

  //Extremely powerful tool that is able to deconstruct data structures how we want to
  // Case class decomposition
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43) // Person.apply("Bob", 43)

  val personGreeting = bob match {
    //Person is a whole structure passed here and then deconstructed
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    case _ => "Something else"
  }

  //pattern matching readily available on case classes, available for classes but requires a lot of work behind the scenes

  // deconstructing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre"
    case _ => "I don't know what you're talking about"
  }

  // decomposing lists
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_, 2, _) => "List containing 2 on its second position"
    case _ => "unknown list"
  } //in this case the list must be EXACT as above.

  // if PM doesn't match anything, it will throw a MatchError
  //This will crash program. Which is why we always add _ case
  // PM will try all cases in sequence (descending order from the top one)
  //PM is much more powerful than we've written here: complex data deconstruction & name binding etc
  //This code is everything you'll need to know to work with 90% of pattern matching use cases
}