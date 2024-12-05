package ScalaAtLightSpeed

//https://youtu.be/In_ey5yyIXo?list=PL8937ckWEfX37TfUEaqXIgilfrUJrcKyW

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

object Advanced extends App {

  /**
   * lazy evaluation
   */
  //not evaluated until it's first used
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy!")
    43
  }

  val eagerValue = lazyValueWithSideEffect + 1
  // useful in infinite collections

  /**
   * "pseudo-collections": Option, Try
   * not actually collections, are their own types but you can think of them as collections
   * very useful when you have 'unsafe' methods such as below
   */
  def methodWhichCanReturnNull(): String = "hello, Scala"

  val anOption = Option(methodWhichCanReturnNull()) // Some("hello, Scala")
  // option = "collection" which contains at most one element: Some(value) or None
  //None means null but this is a regular value so causes no issues
  //in other languages we have to write very protected code to avoid null pointer exceptions etc but option protects from most of these in Scala

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing" //can instead return something when None is the result
  }

  //can write like below but should apply the second example below as this is better
  def methodThatCanThrowException(): String = throw new RuntimeException

  try {
    methodThatCanThrowException()
  } catch {
    case e: Exception => "defend against this evil exception"
  }

  //the above adds too much complexity to large codebases and makes them unreadable.
  // Instead, use the try pseudo-collection as below:

  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())
  // a try = "collection" with either a value if the code went well, or an exception if the code threw one
  //guards against methods that can throw exceptions
  //exceptions really bad for jvm programs and so we need to guard against them

  //pattern match example below:
  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(ex) => s"I have obtained an exception: $ex"
  }
  // map, flatMap, filter

  /**
   * Evaluate something on another thread
   * (asynchronous programming)
   */
  val aFuture = Future { //defined in code block and expression will be evaluated on another thread
    println("Loading...")
    Thread.sleep(1000) //block the running thread in ms
    println("I have computed a value.")
    67
  }//import of implicits required. Global value is now ready to run the feature

//  if using:
  //Thread.sleep(2000)
  //the above future will have the time to perform

  // future is a "collection" which contains a value when it's evaluated
  // future is composable with map, flatMap and filter

  /**
   * Implicits basics
   */
    //multiple use cases
  // #1: implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitInt = 46
  println(aMethodWithImplicitArgs)  // aMethodWithImplicitArgs(myImplicitInt)

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0
  }

  //even though isEven doesn't belong to println class, implicit class ALLOWS this.
  println(23.isEven()) // new MyRichInteger(23).isEven()
  //makes scala an incredibly expressive language but is also dangerous
  // use this carefully

}