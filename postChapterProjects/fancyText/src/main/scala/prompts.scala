package postChapterProjects.fancyText

object Prompts {

  val actionChoicePrompt =
    """Please pick one of these:
      |  1. Static text
      |  2. Banner style text
      |  3. Loading bar
      |
        Waiting for user input: """.stripMargin

  val userTextInputPrompt = "Waiting for user text input: "

  val customizationPrompt =
    """Possible actions:
      |  1. Choose text color
      |  2. Choose background color
      |  3. Add modifier
      |  4. Display current version
      |
        Waiting for user input: """.stripMargin

  val colorChoicePrompt =
    """Possible colors:
      |  1. Black
      |  2. White
      |  3. Red
      |  4. Blue
      |  5. Green
      |  6. Magenta
      |  7. Cyan
      |  8. Yellow
      |  9. Reset to default
      |  10. Go back
      |
        Waiting for user input: """.stripMargin

  val modifiersChoicePrompt =
    """Possible modifiers:
      |  1. Underlined
      |  2. Bold
      |  3. Blinking
      |  4. Clean Modifiers
      |  5. Go back
      |
        Waiting for user input: """.stripMargin
}
