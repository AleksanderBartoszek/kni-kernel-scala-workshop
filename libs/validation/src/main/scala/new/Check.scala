package libs.validation

object Check {

  def apply[A](data: A)(validator: Validator[A]): Either[ErrorLog, A] = validator.run(data)

  def optional[A](data: Option[A])(validator: Validator[A]): Either[ErrorLog, Option[A]] = data match {
    case None => Right(None)
    case Some(value) =>
      Check(value)(validator) match {
        case Left(value)  => Left(value)
        case Right(value) => Right(Some(value))
      }
  }

  def list[A](data: List[A])(validator: Validator[A]): Either[ErrorLog, List[A]] = ???
}

class Validator[-A](val predicate: A => Boolean, val failureDescription: ErrorLog) {

  def and[AA <: A](validator: Validator[AA]): Validator[AA] = ???
  def or[AA <: A](validator: Validator[AA]): Validator[AA]  = ???
  def unary_! : Validator[A] = ???

  def run[A](data: A): Either[ErrorLog, A] = ???
}
