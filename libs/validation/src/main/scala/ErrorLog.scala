package libs.validation

import Console._

object ErrorLog {
  def apply(log: String) = new ErrorLog(s"$CYAN$log$RESET")
}

case class ErrorLog(val log: String)