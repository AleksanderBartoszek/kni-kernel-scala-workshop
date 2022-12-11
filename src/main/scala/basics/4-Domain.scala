package basics

object Domain {

  enum Breed:
    case LabradorRetriever, GermanShepherd, FoxTerrier

  object Dog {}

  class Dog(var hasGetterAndSetter: Int, val hasGetter: Int, hasNothing: Int, val breed: Breed) extends TailWagger {
    override def startWagging: Unit = breed match {
      case Breed.FoxTerrier        => println("excited wagging")
      case Breed.GermanShepherd    => println("careful wagging")
      case Breed.LabradorRetriever => println("calm wagging")
    }
  }

  trait TailWagger {
    def startWagging: Unit
    def stopWagging: Unit = println("stopped wagging")
  }

  case class Person(name: String, vocation: String)

  sealed trait Answer
  case class Yes(grade: Int) extends Answer
  case object No extends Answer
}
