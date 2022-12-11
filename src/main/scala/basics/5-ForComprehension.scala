package basics

object ForComprehension {

  def printOddUpTo(n: Int): Unit = {
    val generator: Range = 1 to n

    for {
      n <- generator if n % 2 == 1
      msg = s"Next odd: $n"
    } println(msg)
  }

  def printOddUpToDesugared(n: Int): Unit = {
    val generator: Range = 1 to n

    generator
      .withFilter(n => n % 2 == 1)
      .map { n =>
        val msg = s"Next odd: $n"
        (n, msg)
      }
      .foreach { case (n, msg) => println(msg) }
  }

  def repeatAll(greetings: List[String], names: List[String]): Unit =
    for {
      greeting <- greetings
      name     <- names
    } {
      val message = s"$greeting $name!"
      println(message)
    }

  def repeatAllDesugared(greetings: List[String], names: List[String]): Unit =
    greetings.foreach { greeting =>
      names.foreach { name =>
        val message = s"$greeting $name!"
        println(message)
      }
    }

  def generate2dPlane(width: Int, height: Int): Seq[(Int, Int)] = {
    val xs = 0 until width
    val ys = 0 until height

    for {
      x <- xs
      y <- ys
    } yield (x, y)
  }

  def generate2dPlaneDesugared(width: Int, height: Int): Seq[(Int, Int)] = {
    val xs = 0 until width
    val ys = 0 until height

    xs.flatMap { x =>
      ys.map(y => (x, y))
    }
  }

  def generateNames(firstNames: Seq[String], lastNames: Seq[String]): Seq[String] =
    for {
      first <- firstNames
      last  <- lastNames
      fullName = s"$first $last" if fullName.length < 16
    } yield fullName

  def generateNamesDesugared(firstNames: Seq[String], lastNames: Seq[String]): Seq[String] =
    firstNames.flatMap { first =>
      lastNames.map { last =>
        val fullName = s"$first $last"
        (last, fullName)
      }
        .withFilter(_._2.length < 16)
        .map(_._2)
    }
}
