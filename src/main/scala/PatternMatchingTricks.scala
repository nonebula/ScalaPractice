// https://youtu.be/qe85507fSc8?list=PL8937ckWEfX37TfUEaqXIgilfrUJrcKyW

object PatternMatchingTricks {

  //If already using, likely using as:
    //a switch on steroids:
      val aNumber = 44
      val ordinal = aNumber match {
        case 1 => "first"
        case 2 => "second"
        case 3 => "third"
        case _ => aNumber +"th"
      }
    //case class deconstruction
    case class Person(name: String, age: Int)
    val bob = Person("Bob", 34)

    val bobGreeting = bob match {
      case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    }

  //Some tricks!
  //#1 - List extractors
  val numberList = List(1,2,3,42)

  val mustHaveThree = numberList match {
    case List(_, _, 3, somethingElse) => s"List has third element 3, so the 4th element is $somethingElse"
  }

  //#2 - Haskell-like prepending
  //Haskell has a head:tail pattern matching behaviours
  val startsWithOne = numberList match {
    case 1 :: tail => s"List starts with one, tail is $tail"
  }
  //useful when building api methods such as 'process':
  def process(aList: List[Int]) = aList match {
    case Nil => "List is empty"
    case head :: tail => s"List starts with $head, tail is $tail"
  }

  //#3 - vararg pattern
  val dontCareAboutTheRest = numberList match {
    case List(_, 2, _*) => "I only care about the second number being 2"
    //_* is the variable argument pattern. Test the first few elements then allow the rest that you don't care about
  }

  //#4 - other infix patterns
  val mustEndWithTheMeaningOfLife = numberList match {
    case List(1,2,_) :+ 42 => "That's right, I have a meaning" //tests that 42 is at the end of a list
  }
  //very powerful when paired with vararg pattern:
  val mustEndWithTheMeaningOfLifeV2 = numberList match {
    case List(1, _*) :+ 42 => "I don't care how long the list is, I just want it to end in 42"
  }

  //#5 - type specifiers
  def gimmeAValue(): Any = 45
  //match the value being returned against it's type
  val gimmeTheType = gimmeAValue() match {
    case _: String => "I have a string"
    case _: Int => "I have an int"
    case _ => "I have something else"
    //also called type guards
    //drawback is that this is based on reflection. Can cause performance hits when overused
  }

  //#6 - name binding
  //could prove useful in practice, often used in Scala code
  def requestMoreInfo(p: Person): String = s"The person ${p.name} is a good person."

  val bobsInfo = bob match { //using asinstanceof instead of bob is TERRIBLE
    case Person(n, a) => s"$n's info: ${requestMoreInfo(bob)}'"
    case Person(n, a) => s"$n's info: ${requestMoreInfo(Person(n, a))}'" //this is preferred
    //many different ways to approach this. Powerful and useful.
  }

  //#7 - conditional guards
  //to be used for matching a condition, not necessarily a value specifically
  //better than chaining if/else statements etc
  val ordinal2 = aNumber match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case n if n % 10 == 1 => n + "st"
    case n if n % 10 == 2 => n + "nd"
    case n if n % 10 == 3 => n + "rd"
    case _ => aNumber + "th"
  }

  //#8 - alternative patterns
  //for when you return the same expression for multiple patterns because you don't need to copy and paste the same code
  val myOptimalList = numberList match {
    case List(1, _*) => "I like this list"
    case List(_, _, 3, _*) => "I like this list"
    //or can do like this:
    //case List(1, _*) | List(_, _, 3, _*) => "I like this list"
    //can chain patterns as alternatives to avoid pasting over and over
    case _ => "I hate this list"
  }

  def main(args: Array[String]): Unit = {
    println(myOptimalList)
    println(ordinal2)
  }
}
