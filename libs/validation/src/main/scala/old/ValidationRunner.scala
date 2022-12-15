// package libs.validation

// trait ValidationRunner[F[_], A] {
//   def validator: Validator[A]

//   def run[AA <: A](value: F[AA]): Either[ErrorLog, F[AA]]

//   final def apply(value: F[A]): Either[ErrorLog, F[A]] = run(value)
// }

// object ValidationRunner {

//   final case class ValidationRunnerForOption[A](valid: Validator[A]) extends ValidationRunner[Option, A] {

//     override def validator: Validator[A] = valid
//     override def run[AA <: A](value: Option[AA]): Either[ErrorLog, Option[AA]] = value match {
//       case Some(x) =>
//         valid.run(x) match {
//           case Left(y)  => Left(y)
//           case Right(y) => Right(Some(y))
//         }
//       case None => Right(None)
//     }
//   }

//   final case class ValidationRunnerForList[A](valid: Validator[A]) extends ValidationRunner[List, A] {

//     override def validator: Validator[A] = valid

//     override def run[AA <: A](value: List[AA]): Either[ErrorLog, List[AA]] = ???//value match {
//     //   case Nil => valid.run(value.head) match {
//     //     case Left(x)  => Left(x)
//     //     case Right(x) => ???
//     //   case
//     // }
//   }
// }