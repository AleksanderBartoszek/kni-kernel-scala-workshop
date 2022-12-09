package basics

object Imports {

  import Syntax.{ howdy, title }

  println(title)
  println("---")
  println(howdy)
  println("---")

  import Syntax.*

  println(personalizedHello("Clint Eastwood"))
}
