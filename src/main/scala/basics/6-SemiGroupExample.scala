package basics

object SemigroupExample extends App {

  def fold[A](zero: A)(combine: (A, A) => A)(list: List[A]): A = {
    def loop(acc: A, list: List[A]): A =
      list match {
        case Nil          => acc
        case head :: tail => loop(combine(acc, head), tail)
      }

    loop(zero, list)
  }

  val sumAll     = fold(0)(_ + _)(_)
  val productAll = fold(1)(_ * _)(_)

  val values = (1 to 5).toList

  println(s"+ all: $values = ${sumAll(values)}")
  println(s"* all: $values = ${productAll(values)}")
}
