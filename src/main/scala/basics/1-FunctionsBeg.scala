package basics

object FunctionsBeg {

  def addMethod(a: Int, b: Int): Int = a + b

  val inferredFn = (a: Int, b: Int) => a + b

  val functionClass: Function2[Int, Int, Int] = inferredFn

  val addition: (Int, Int) => Int = (a, b) => a + b

  def calculate(a: Int, b: Int, operation: (Int, Int) => Int): Int = operation(a, b)

  /** EXERCISE 1
    *
    * Define integer subtraction function.
    */
  val subtraction = ???

  /** EXERCISE 2
    *
    * Define integer multiplication function.
    */
  val multiplication = ???

  /** EXERCISE 3
    *
    * Use the calculate method to add 5 to 10.
    */
  val fivePlusTen: Int = ???

  /** EXERCISE 4
    *
    * Use the calculate method to multiply 3 by 6.
    */
  val threeTimesSix: Int = ???

  /** EXERCISE 5
    *
    * Use the calculate method to add 5 to 10 and then multiply it by 3.
    */
  val fivePlusTenTimesThree: Int = ???

  // isomorphic
  val twoPlusEight: Int = calculate(2, 8, addMethod)
  // with lambdas
  val sixMinusTwo: Int = calculate(6, 2, (a: Int, b: Int) => a - b)
  // underscores
  val sixPlusTwo: Int           = calculate(6, 2, _ + _)
  val modulo: (Int, Int) => Int = _ % _
  // underscore with partial functions
  val multiply: (Int, Int) => Int = (a, b) => a * b
  val double: Int => Int          = multiply(2, _)

  /** EXERCISE 6
    *
    * Implement the combine method, which takes two string values and a binary operation and returns a string.
    */
  def combine = ???

  /** EXERCISE 7
    *
    * Use the `combine` method with an anonymous function to construct the full name.
    */
  val firstName: String = "Jason"
  val lastName: String  = "Momoa"
  val fullName: String  = ???

}
