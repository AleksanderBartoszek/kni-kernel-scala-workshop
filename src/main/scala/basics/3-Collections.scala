package basics

object Collections {
  val intArray: Array[Int]     = Array(1, 2, 3, 4, 5)
  val intSequence: Seq[Int]    = Seq(1, 2, 3, 4, 5)
  val intList: List[Int]       = List(1, 2, 3, 4, 5)
  val intVector: Vector[Int]   = Vector(1, 2, 3, 4, 5)
  val intSet: Set[Int]         = Set(1, 2, 3, 4, 5)
  val intMap: Map[String, Int] = Map("one" -> 1, "two" -> 2, "three" -> 3)

  val digits: Range        = 0 to 9
  val reverseDigits: Range = 9 to 0 by -1

  val listFromArray: List[Int]    = intArray.toList
  val vectorFromList: Vector[Int] = intList.toVector
}
