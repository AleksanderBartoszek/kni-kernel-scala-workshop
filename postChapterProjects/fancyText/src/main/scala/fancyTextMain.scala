package postChapterProjects.fancyText

import scala.io.StdIn
import libs.styling._
import Console._
import scala.annotation.tailrec

object fancyTextMain extends App {
  
  println("\u001b[2J")
  println(Prompts.actionChoicePrompt)
  val userInput = StdIn.readInt()
  println("\u001b[2J")
  userInput match {
    case 1 => staticDisplay()
    case 2 => bannerDisplay()
    case 3 =>
      val loadingBar = LoadingBar()
      loadingBar.animate()
  }

  def staticDisplay() = {
    println(Prompts.userTextInputPrompt)
    val userText = StdIn.readLine()
    println("\u001b[2J")
    val staticText = StylizedText(userText)

    val colorfulText = customize(staticText)

    println(colorfulText.render)
  }

  def bannerDisplay() = {
    println(Prompts.userTextInputPrompt)
    val userText = StdIn.readLine()
    println("\u001b[2J")
    val bannerText = StylizedText(userText)

    val colorfulText = customize(bannerText)

    bannerAnimation(colorfulText)
  }

  @tailrec
  def bannerAnimation(text: StylizedText, disp: Int = 0): Unit = {
    println(text.render)
    val right   = text.text.takeRight(1)
    val newText = StylizedText(right + text.text.dropRight(1), text.fontColor, text.bgColor, text.modifiers)
    Thread.sleep(300)
    println("\u001b[2J")
    if (disp < 30)
      bannerAnimation(newText, disp + 1)
  }

  def customize(text: StylizedText): StylizedText = {
    println(Prompts.customizationPrompt)
    val userInput = StdIn.readInt()
    println("\u001b[2J")
    userInput match {
      case 1 =>
        val text1 = chooseFontColor(text)
        customize(text1)
      case 2 =>
        val text1 = chooseBackGroundColor(text)
        customize(text1)
      case 3 => 
        val text1 = chooseModifiers(text)
        customize(text1)
      case 4 => text
    }
  }

  def chooseFontColor(text: StylizedText): StylizedText = {
    println(Prompts.colorChoicePrompt)
    val userInput = StdIn.readInt()
    println("\u001b[2J")
    userInput match {
      case 1  => text.black
      case 2  => text.white
      case 3  => text.red
      case 4  => text.blue
      case 5  => text.green
      case 6  => text.magenta
      case 7  => text.cyan
      case 8  => text.yellow
      case 9  => text.defaultFontColor
      case 10 => text
    }
  }

  def chooseBackGroundColor(text: StylizedText): StylizedText = {
    println(Prompts.colorChoicePrompt)
    val userInput = StdIn.readInt()
    println("\u001b[2J")
    userInput match {
      case 1  => text.black_bg
      case 2  => text.white_bg
      case 3  => text.red_bg
      case 4  => text.blue_bg
      case 5  => text.green_bg
      case 6  => text.magenta_bg
      case 7  => text.cyan_bg
      case 8  => text.yellow_bg
      case 9  => text.defaultBackground
      case 10 => text
    }
  }

  def chooseModifiers(text: StylizedText): StylizedText = {
    println(Prompts.modifiersChoicePrompt)
    val userInput = StdIn.readInt()
    println("\u001b[2J")
    userInput match {
      case 1 => text.underlined
      case 2 => text.bold
      case 3 => text.blinking
      case 4 => text.cleanModifiers
      case 5 => text
    }
  }
}
