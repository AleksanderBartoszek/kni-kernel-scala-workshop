/** 3 ways to define main method in your program
  */

// preferred way implemented in scala 3
@main def Scala3Main: Unit =
  println("Hello world!")

// canonical way from scala 2
object Scala2Main {
  def main(args: Array[String]): Unit = println("Hello world!")
}

// shorthand where everything inside object extending App is like if it where in main method
object ScalaMainApp extends App {
  println("The body of the object becomes the body of the main method.")
  println("[Note] We still have access to args value: " + args.getClass.getTypeName)
}
