//https://youtu.be/OI3F_fuFYjg?list=PL8937ckWEfX37TfUEaqXIgilfrUJrcKyW

object ObjectsAndCompanions {

  //defines both a type (singleton) and the only possible instance of this type (the object)
  object MySingleton
  //thread safety not an issue. Work like class declarations, can define fields and methods on them.

  object ClusterSingleton {
    val MAX_NODES = 20
    def getNumberOfNodes(): Int = { 42 }
  }

  val maxNodes = ClusterSingleton.MAX_NODES

  // Class + Object with same name in same file = Companions

  class Kid(name:String, age: Int) {
    def greet(): String = s"Hello, my name is $name and I'm $age years old. Do I like vegetables? ${Kid.LIKES_VEGETABLES}"
  } //private val accessible from companion object here but won't be outside of companions.
//^^ dependent logic

  //objects do not have parameters
  //companion object of the class
  object Kid {
    private val LIKES_VEGETABLES: Boolean = false
    //Preconception
  }
  //^^ independent logic

  // instance-independent code = "static" in languages like Java:
  // static boolean LIKES_VEGETABLES = false
  //companion objects and classes compile to the same bytecode as the Java alternative

  def playGame(kid: Kid) = println("playing a game with a kid")
  val bobbie = new Kid("Bobbie", 9)
  playGame(bobbie)
  // playGame(Kid) //compile error here because the Kid companion object does not share the type with all the companion instances

  def main(args: Array[String]): Unit = {

  }

}
