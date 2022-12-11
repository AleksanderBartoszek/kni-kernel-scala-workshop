package libs.validation

import scala.util.matching.Regex

object Rule {

  def alwaysValid: Validator[Any] =
    new Validator(_ => true, ErrorLog("Always valid"))

  def alwaysInvalid: Validator[Any] =
    new Validator(_ => false, ErrorLog("Always invalid"))

  def nonBlank: Validator[String] =
    new Validator(str => !str.isBlank(), ErrorLog(s"Text is blank"))

  def maxLength(max: Int): Validator[String] =
    new Validator(str => str.length <= max, ErrorLog(s"Text is longer than '$max'"))

  def minLength(min: Int): Validator[String] =
    new Validator(str => str.length >= min, ErrorLog(s"Text is shorter than '$min'"))

  def matchesRegex(regex: Regex): Validator[String] =
    new Validator(str => regex.findAllIn(str).length > 0, ErrorLog(s"Text does NOT match '$regex' regex"))

  def digitsOnly: Validator[String] =
    new Validator(str => str.forall(_.isDigit), ErrorLog(s"Text has non-digits"))

  def capitalized: Validator[String] =
    new Validator(str => str(0).isUpper, ErrorLog(s"Text is NOT capitalized"))

  def contains(text: String): Validator[String] =
    new Validator(str => str.contains(text), ErrorLog(s"Text does not contain '$text'"))

}
