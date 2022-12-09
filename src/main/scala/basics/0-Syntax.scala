package basics

import annotation.nowarn

@nowarn
object Syntax {

  // built-in data types
  "Scala!" // String
  2137 // Int
  0.2137d // Double
  true // Boolean
  2137L // Long
  0.2137f // Float
  'a' // Char

  // variables and values
  val howdy = "Top" + " of" + " the" + " morning" + " to" + " you!"
  var four  = 1 + 3
  four = 5 // possible but not encouraged

  // string interpolation
  val fact = s"car has $four wheels" 

  // explicit type
  val title: String = "Welcome to the KNI Kernel workshop!"

  // why not?
  val helloMessage: String = {
    val name    = "Marty"
    val surname = "McCow"
    s"Hello $name $surname!" // the last line will be the return value of the block
  }

  // method definition
  def personalizedHello(name: String): String = {
    val welcome = s"Hi $name!"
    val message = s"$welcome $title"
    message
  }

  // multiple arguments
  def sum3(a: Int, b: Int, c: Int): Int = a + b + c

  // default value for argument
  def log(message: String, level: String = "INFO"): Unit = println(s"[$level] $message")
  def info(message: String): Unit                        = log(message)
  def error(message: String): Unit                       = log(message, "ERROR")
  // explicitly named arguments
  log(level = "DEBUG", message = "This is a debug message")

  // var arg -> Int* can take one or more arguments of that type
  def sumAll(head: Int, tail: Int*): Int = head + tail.reduceLeft(_ + _)
  val sum4                               = sumAll(1, 2, 3, 4)
  val sum5                               = sumAll(1, 2, 3, 4, 5)
  val sum6                               = sumAll(1, 2, 3, 4, 5, 6)
  val sum100 = sumAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 1, 2,
    3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
    17, 18, 19, 20, 21, 22, 23, 24, 25)

  // tuples
  val point: (Int, Int)       = (0, 0)
  val vector: (Int, Int, Int) = (0, 1, 2)
  val x = vector._1

  // alternative tuple syntax
  val person: (String, Int) = "Jack" -> 17

  // creating tuple on the fly
  def divisionWithRemainder(dividend: Int, divisor: Int): (Int, Int) = {
    def quotient: Int  = dividend / divisor
    def remainder: Int = dividend % divisor
    (quotient, remainder)
  }

  // lazy vals
  lazy val lazyValue: String = {
    val value = "<foo>"
    println("Initializing `lazyValue`")
    value
  }

  println("-- Start --")
  println(s"The value is $lazyValue")
  println(s"The value is still $lazyValue")
  println("-- End --")

  /** Experiment with `println` at diffrent positons. Note your findings.
    */
  lazy val boolValue: String = s"$boolA $boolB"
  lazy val intValue: String  = s"$intA $intB"

  // println(boolValue)
  // println(intValue)

  val boolA: Boolean = true
  val boolB: Boolean = false
  val intA: Int      = 1
  val intB: Int      = 2

  // println(boolValue)
  // println(intValue)
}
