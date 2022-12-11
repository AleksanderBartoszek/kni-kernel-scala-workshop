package basics

object ErrorHandling {

  // Functional programmers don’t use null values
  // A main replacement for null values is to use the Option classes
  // Functional methods don’t throw exceptions; instead they return values like Option, Try, or Either
  // Common ways to work with Option values are match and for expressions
  // Options can be thought of as containers of one item (Some) and no items (None)
  // Options can also be used for optional constructor or method parameters

  val o1: Option[Int] = None
  val o2 = Some(10)

  val v1 = o1 match {
    case Some(n) =>
      n
    case None =>
      0
  }

  // .get
  // .getOrElse
  // .orElse
  val v2 = o1.getOrElse(0)
}
