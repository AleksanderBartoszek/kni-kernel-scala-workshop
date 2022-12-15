package libs.validation

object main extends App {

  val text = "this is random mock string"
  val someText = Some("this is random mock string")

  val validator: Validator[Int] = new Validator[Int](x => x % 2 == 0, ErrorLog("Number is not even"))
  val normal: Either[ErrorLog, Int] = Check(10)(validator)
  println(normal)

  val customTest = Check("hello world"){ new Validator(_ => false, ErrorLog("bye world"))}
  println(customTest)

  val defaultRule = Check("hello world")(Rule.alwaysValid)
  println(defaultRule)

  val validated = Check.optional(someText)(Rule.matchesRegex("[0-9]".r))
  println(validated)

  val test1 = Check(text)(Rule.alwaysInvalid and !Rule.alwaysInvalid and Rule.nonBlank)
  println(test1)

  val validatorInt: Validator[Int] = new Validator[Int](x => x % 2 == 0, ErrorLog("Number is not even"))
  val intFailedTest: Either[ErrorLog, Int] = Check(5)(validatorInt)
  val intPassedTest: Either[ErrorLog, Int] = Check(4)(validatorInt)
  println(intFailedTest)
  println(intPassedTest)

  val validatorAnyVal: Validator[AnyVal] = new Validator[AnyVal](x => false, ErrorLog("?????"))
  val weirdTest = Check(10)(Rule.alwaysInvalid or validatorAnyVal and validatorInt)
  println(weirdTest)

  val testvalidator: Validator[Int] = new Validator[Int](x => x % 2 == 0, ErrorLog("Number is not even"))
  val testnormal: Either[ErrorLog, Int] =  Check(1)(testvalidator)
  val optional: Either[ErrorLog, Option[Int]] =  Check.optional(Some(1))(testvalidator) // should run validation only when value is Some or succeed otherwise
  
  
  val list: Either[ErrorLog, List[Int]] =  Check.list(List(1, 2, 3))(testvalidator) // should fail when any of the elements is not valid
}
