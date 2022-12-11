package libs.validation

object Validator {
  type Id[A] = A

  def apply[A](text: A)(validator: Validator[A]): Either[ErrorLog, A] =
    validator.run(text)
}

class Validator[-A](
  private val predicate: A => Boolean,
  private val errorLog: ErrorLog
) extends ValidationRunner[Validator.Id, A] {

  def &&[AA <: A](next: Validator[AA]): Validator[AA] =
    new Validator(
      str => this.predicate(str) && next.predicate(str),
      ErrorLog(s"${this.errorLog.log} && ${next.errorLog.log}")
    )

  def ||[AA <: A](next: Validator[AA]): Validator[AA] =
    new Validator(
      str => this.predicate(str) || next.predicate(str),
      ErrorLog(s"${this.errorLog.log} || ${next.errorLog.log}")
    )

  def unary_! : Validator[A] =
    new Validator(str => !predicate(str), ErrorLog(s"NOT ${errorLog.log}"))

  def run[AA <: A](str: AA): Either[ErrorLog, AA] =
    if (predicate(str)) Right(str)
    else Left(errorLog)

  import ValidationRunner.*

  def optional(text: Option[A]): Either[ErrorLog, Option[A]] =
    ValidationRunnerForOption(this).run(text)

  def list(text: List[A]): Either[ErrorLog, List[A]] =
    ValidationRunnerForList(this).run(text)

}
