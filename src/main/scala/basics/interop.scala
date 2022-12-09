package basics

package interop {

  trait Pet extends Animal {
    def getName: String
    def getAge: Int

    def speak(): String

    def show(): String = s"$getAge year old $kind named $getName sais: `${speak()}`"
  }

  class Dog(val getName: String, val getAge: Int) extends Pet {
    val kind: String = "Dog"

    def speak(): String = "Woof! Woof!"
  }

  class Bird(val getName: String = "Tweety", val getAge: Int = 1) extends Pet {
    val kind: String = "Bird"

    def speak(): String = "Tweet! Tweet!"
  }

  object ScalaApp extends App {

    println("-- ScalaApp --")

    val cat = new Cat("Lil", 7)
    println(cat.show())

    val dog = new Dog("Rex", 2)
    println(dog.show())

    val bird = new Bird()
    println(bird.show())
  }

  object RunBothApps extends App {
    JavaApp.main(args)
    ScalaApp.main(args)
  }
}
