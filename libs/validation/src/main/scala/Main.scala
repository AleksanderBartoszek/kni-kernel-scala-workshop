 package libs.validation

object main extends App {

  val text = "this is random mock string"
  val someText = Some("this is random mock string")

  //val validated = Validator.optional(someText)(Rule.matchesRegex("[0-9]".r))
  //println(validated)

  val test1 = Validator(text)(Rule.nonBlank && Rule.alwaysInvalid && !Rule.alwaysInvalid)
  println(test1)

  val customTest = Validator("hello world"){ new Validator(_ => false, ErrorLog("bye world"))}
  println(customTest)

  ///////////////////////////////////////////

  val validatorInt: Validator[Int] = new Validator[Int](x => x % 2 == 0, ErrorLog("Number is not even"))
  val intFailedTest = Validator(5)(validatorInt)
  val intPassedTest = Validator(4)(validatorInt)
  println(intFailedTest)
  println(intPassedTest)

  val validatorAnyVal: Validator[AnyVal] = new Validator[AnyVal](x => false, ErrorLog("?????"))
  val weirdTest = Validator(10)(validatorAnyVal && validatorInt || Rule.alwaysInvalid)
  println(weirdTest)

  val validator: Validator[Int] = new Validator[Int](x => x % 2 == 0, ErrorLog("Number is not even"))
  val normal: Either[ErrorLog, Int] =  validator.run(1)
  val optional: Either[ErrorLog, Option[Int]] =  validator.optional(Some(1)) // should run validation only when value is Some or succeed otherwise
  val list: Either[ErrorLog, List[Int]] =  validator.list(List(1, 2, 3)) // should fail when any of the elements is not valid
}