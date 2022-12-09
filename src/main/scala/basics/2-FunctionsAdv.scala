package basics

import scala.annotation.{ nowarn, tailrec }

object FunctionsAdv {

  /** Currying
    */
  val sum: (Int, Int, Int) => Int                = (a, b, c) => a + b + c
  val sumABandC: (Int, Int) => (Int => Int)      = (a, b) => (c => a + b + c)
  val sumAandBandC: Int => (Int => (Int => Int)) = a => (b => (c => a + b + c))
  val curriedSum: Int => Int => Int => Int       = a => b => c => a + b + c

  val regularCall: Int = sum(1, 2, 3)
  val curriedCall: Int = curriedSum(1)(2)(3)

  /** HOFs
    */
  val logPretty: (String, String => String) => Unit = (message, prettify) => println(prettify(message))

  val curriedAdd: Int => (Int => Int) = a => (b => a + b)

  val ticketPrice: (Int => String, String => Double) => (Int => Double) = (groupByAge, priceByGroup) =>
    (age => groupByAge.andThen(priceByGroup)(age))

  /** Function characteristics
    */
  val pureAdd: (Int, Int) => Int = (a, b) => a + b

  val impureAdd: (Int, Int) => Int = (a, b) => {
    println(s"Adding $a and $b")
    a + b
  }

  val pureDivide: (Int, Int) => Option[Int] = (a, b) =>
    if (b == 0) None
    else Some(a / b)

  val impureDivide: (Int, Int) => Int = (a, b) => a / b

  import java.time.LocalTime

  val pureTime: Long => LocalTime = seconds => LocalTime.ofSecondOfDay(seconds)

  val impureTime: () => LocalTime = () => LocalTime.now()

  /** Partial functions
    */
  val addOneButOnlyToEvenNumbers: PartialFunction[Int, Int] = {
    case x if x % 2 == 0 => x + 1
  }

  // exception
  addOneButOnlyToEvenNumbers(1)

  val isDefinedFor1 = addOneButOnlyToEvenNumbers.isDefinedAt(1)
  val isDefinedFor2 = addOneButOnlyToEvenNumbers.isDefinedAt(2)
  println(s"isDefinedFor1 = $isDefinedFor1")
  println(s"isDefinedFor2 = $isDefinedFor2")

  val addOneButOnlyToEvenNumbersAsTotalFunction: Int => Int = addOneButOnlyToEvenNumbers

  val partialGreet: PartialFunction[String, String] =
    (name: String) =>
      name match {
        case "Jim" | "Jack" | "John" => s"Hello $name!"
      }

  /** EXERCISE 8
    *
    * Use the `applyOrElse` method to turn a partial function into a total function.
    */
  val totalGreet: String => String = ???

  /** EXERCISE 9
    *
    * Use the `lift` method to turn a partial function into a total function (by returning Some or None depending if the
    * function is defined for that argument).
    */
  val partialGreenAsOption: String => Option[String] = ???

  /** EXERCISE 10
    *
    * Define a method that takes a String 'text' and returns Some(text) if the number of letters is even or None if the
    * number of letters is odd.
    *
    * Hint: For an elegant solution, use the PartialFunction.condOpt method in tandem with pattern matching.
    */
  def isNumberOfLettersEven(text: String): Option[String] = ???

  /** EXERCISE 11
    *
    * Analyse the following method. Is it pure? Why? Note your findings.
    */
  val pi: Double                         = 3.14
  def circleArea(radius: Double): Double = pi * radius * radius

  /** EXERCISE 12
    *
    * Analyse the following method. Is it pure? Why? Note your findings.
    */
  def fibonacci(n: Int): Long =
    if (n < 0) ???
    else if (n == 0) 0
    else if (n <= 2) 1
    else {
      var (left, right, i) = (0, 1, 2)

      def calculateNextSum() = {
        val sum = left + right
        if (i % 2 == 0) left = sum
        else right = sum
      }

      while (i < n) {
        calculateNextSum()
        i = i + 1
      }
      left + right
    }

  /** Function composition
    */
  val inc: Int => Int    = _ + 1
  val triple: Int => Int = _ * 3

  val tripleInc: Int => Int = n => triple.andThen(inc)(n)

  val tripleIncApply: Int => Int = n => inc(triple(n))

  val incTriple: Int => Int = n => triple.compose(inc)(n)

  val incTripleApply: Int => Int = n => triple(inc(n))

  /** Function evaluation
    */
  @nowarn
  def callByValue(thunk: Unit): Unit = {
    thunk
    thunk
  }

  def callByName(thunk: => Unit): Unit = {
    thunk
    thunk
  }

  callByValue(println("Foo"))
  callByName(println("Bar"))

  /** EXERCISE 13
    *
    * Using lazy arguments we're able to implement our own control structures, like the `while` loop.
    *
    * A `while` loop consists of:
    *   - a condition (that needs to be recomputed for each iteration of the loop)
    *   - a body (that usually does something unpure, like mutates some values etc.)
    *
    * Define a method that will mimick the `while` loop. Using multiple parameter lists we can make it look just like
    * the real deal, try to make it look as `whiley` as possible.
    */
  def whileLoop(condition: => Boolean, body: => Unit): Unit = ???

  /** Tail Recursion
    */
  @tailrec
  def countdown(n: Int): Unit =
    if (n == 0) println("BOOM!")
    else {
      println(n)
      Thread.sleep(1000)
      countdown(n - 1)
    }
  countdown(5)

  // nesting rec function inside to hide additional arguments
  def tailRecFactorial(n: Int): Int = {
    @tailrec
    def loop(acc: Int, n: Int): Int =
      if (n == 1) acc
      else loop(acc * n, n - 1)

    loop(1, n)
  }
  println((1 to 5).map(tailRecFactorial).mkString(","))

  /** EXERCISE 14
    *
    * Let's put this to use. Try to reverse a String with a tail-recursive method.
    */
  def reverse(string: String): String = ???

  /** EXERCISE 15
    *
    * Implement tail-recursive fibonacci function.
    */
  def tailRecFibonacci(n: Int): Long = ???
  println((0 to 10).map(tailRecFibonacci).mkString(","))

  /** EXERCISE 16
    *
    * Using functions, lazy arguments and tail recursion implement a method that mimics the `for` loop. A 'for' loop
    * consists of:
    *   - a decrementing or an inrementing counter
    *   - a boolean expression that looks at the counter and decides to continue the loop or not
    *   - the body of the loop which is a function that takes an Int (it should have access to the counter)
    *
    * Once again, using multiple param lists we can make it look like a standard `for` loop so let's aim for it!
    */
  def forLoop = ???
}
