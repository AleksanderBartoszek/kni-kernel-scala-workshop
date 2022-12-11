## **Console Components**

#### **Task definition**

Let's write ourselves a cool little console text utility, we'd like to support these use cases:

1. Static text
2. Moving text (think of LED banners, text that moves left to right, when it reaches the right side it comes out on the left side)
3. Loading bars (eg. an animated bar of '#' that slowly fills up)

But that's not everything, obviously. We'd also like to support styling for that text, that is:

1. Text coloring (colors: black, white, red, blue, green, magenta, cyan, yellow)
2. Text modifiers - underline, boldness, blinking
3. Text background color (same colors as in #1 or none)

The flow of this program should be:
- A user starts the program and is presented with a CLI menu that lets them choose one of the 3 options (static text, moving text, loading bar),
  - if the user chooses static or moving text, they are prompted to provide it,
- Once that's done we move on to the styling menu where we let the user decide on all of the styling (remember, these should be composable! Eg. we should be able to apply red text, boldness, underlining and blinking all together),
- As the end result the specified text flavor with the chosen styling is displayed/animated in the terminal.

#### **The Plan**

To not overwhelm ourselves just yet let's make a plan for this.

Looking at this from a bird's eye view we can faintly see that these two concepts (fancy text and styling) are two separate entities that can be worked on separately and make one of them depend on the other one.

Let's start with styling first.

### **Styling**

We'd like to build this into a useful library (not just for the task mentioned above), let's start with the build definition.

This project should:
-  be named `styling`,
-  be placed in the `libs/styling` directory inside the root of the repository,
-  be built on `Scala 2.13.8`,
-  include everything to make `scalafix` work and also include these compiler options:
   - `-Xsource:3` (enables some Scala 3 features and fixes for Scala 2 projects),
   - `-Xfatal-warnings` (fails compilation when any warning is generated),
   - `-Xlint` (misc. options for linting like unused values etc.),
   - `-Wconf:cat=unused:info` (unused definitions will generate warnings even if `-Xfatal-warnings` is enabled, we do this to make `scalafix` work for these warnings and clean them up for us).

With the build definition out of the way let's see which mechanism should be abstracted away to make text styling work.

ANSI escape code constants specified in [this file (Console.scala)](https://www.scala-lang.org/api/2.13.x/scala/Console$.html#:~:text=of%20%2D135.1%C2%B0C.-,ANSI%20escape%20codes,-Use%20the%20ANSI) provide the primitives we need to work with, let's see a quick example ([you can run it here](https://scastie.scala-lang.org/arainko/yJcvTKQgSXy6acbKySLqbw)).

```scala
import Console._

object StylingTest extends App {
  println(s"$RED red text $RESET")
  println(s"$BLUE blue text $RESET normal text again")
  println(s"$BLUE_B blue background $RESET")
  println(s"$BOLD $BLINK bold & blinking! $RESET")
  println(s"$RED_B what happens if we don't reset?")
  println(s"uh oh, it carries over to every subsequent line...")
}
```

So for our abstraction we need to always ensure that the stylized `Strings` are always `RESETed` to not leak outside the provided text. We will also need something more convenient to stylize the text, an example (completely made up, feel free to provide your own domain specific language!) usage is provided below:

```scala
val text: Stylized = Stylized("lorem ipsum") // Stylized is the class that houses our modifications
val blueText: Stylized = text.blue
val redtext: Stylized = blueText.red // every .[color] operation overrides the previous ones, same goes for background colors
val redUnderlinedText: Stylized = redText.underlined
val redUnderlinedAndBoldText: Stylized = redUnderlinedText.bold // every .[text modifier] operation is stacked on top of the previous ones
val rendered: String = redUnderlinedAndBoldText.render // by calling .render we get the actual String representation of the decorated text which we can then print to the console
```

### **Fancy Text**

Build definition. This project should:
-  be named `fancyText`,
-  be placed in the `postChapterProjects/fancyText` directory inside the root of the repository,
-  be built on `Scala 2.13.8`,
-  depend on `styling` to make use of its text abstracions,
-  include everything to make `scalafix` work and also include these compiler options:
   - `-Xsource:3` (enables some Scala 3 features and fixes for Scala 2 projects),
   - `-Xfatal-warnings` (fails compilation when any warning is generated),
   - `-Xlint` (misc. options for linting like unused values etc.),
   - `-Wconf:cat=unused:info` (unused definitions will generate warnings even if `-Xfatal-warnings` is enabled, we do this to make `scalafix` work for these warnings and clean them up for us).


#### **Example menu**

The user should be presented with a CLI menu, something along the lines of this:
```
Please pick one of these:
  1. Static text
  2. Banner style text
  3. Loading bar

Waiting for user input: 
```

#### **Getting user input**
To get input from the terminal we can use the methods inside [`StdIn`](https://docs.scala-lang.org/overviews/scala-book/command-line-io.html#:~:text=Int%2C%20Float%2C%20etc.-,Reading%20input,-There%20are%20several), eg.:

```scala
object CLIInput extends App {
  val line: String = StdIn.readLine()
  val int: Int = StdIn.readInt() // this will read an Int or throw an exception
}
```

*For the needs of this project let's assume the user never inputs invalid data that will throw exceptions when reading from the command line.*

#### **The runloop**

Some of the text designs will need some notion of 'frames', we can emulate this with a loop that sleeps for some amount of time and clears the screen between each 'frame'.

To do this we can use the `Thread.sleep([miliseconds])` to stop the execution of the program for the provided amount of miliseconds, for clearing the screen we can use the `"\033[H\033[2J"` ANSI code, which when printed to the console will clear it.

#### **Don't reinvent the wheel (all of the time)**
That's a general advice for these kind of things, especially working with `Strings`, the `String` class in Scala has multitude of methods that will pretty much do the right thing for you if you just let them, the best way of discovering them is through your IDE, just type `.` after a `String` expression to get a list of all of them. 

Who knows what you may find!

### **Design Goals**

Scala is a mix of OOP and FP with the latter being slightly preferred which means we should always favor immutability (no `vars`, imperative loops, changing state etc.), elegant abstractions (without leaking implementation details, eg. missing `RESET` would be a leaking detail because it disrupts the flow of the following statements) and generally an abstinence from throwing exceptions (in those cases we should use the type system to guide the user eg. for something that would throw an exception we should use an `Option` and return `None` instead or something more expressive, like `Either`).

