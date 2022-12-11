package libs.validation

trait ValidationRunner[F[_], A] {
  def validator: Validator[A]

  def run(value: F[A]): Either[ErrorLog, F[A]]

  final def apply(value: F[A]): Either[ErrorLog, F[A]] = run(value)
}

object ValidationRunner {

  final case class ValidationRunnerForOption[A](valid: Validator[A]) extends ValidationRunner[Option, A] {

    override def validator: Validator[A] = valid
    override def run(value: Option[A]): Either[ErrorLog, Option[A]] = value match {
      case Some(x) =>
        valid.run(x) match {
          case Left(y)  => Left(y)
          case Right(y) => Right(Some(y))
        }
      case None => Right(None)
    }
  }

  final case class ValidationRunnerForList[A](valid: Validator[A]) extends ValidationRunner[List, A] {

    override def validator: Validator[A] = valid

    override def run(value: List[A]): Either[ErrorLog, List[A]] = ???//value match {
    //   case Nil => valid.run(value.head) match {
    //     case Left(x)  => Left(x)
    //     case Right(x) => ???
    //   case
    // }
  }
}