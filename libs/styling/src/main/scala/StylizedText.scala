package libs.styling

import Console.*

object StylizedText:
  def apply(t: String, f: String = "", bg: String = "", m: String = "") = new StylizedText(t, f, bg, m)


class StylizedText(val text: String, val fontColor: String = "", val bgColor: String = "", val modifiers: String = "") {

  def render = s"$fontColor$bgColor$modifiers$text$RESET"

  // font color changing
  def fontColorChange(newColor: String) = StylizedText(text, newColor, bgColor, modifiers)

  def black            = fontColorChange(s"${BLACK}")
  def white            = fontColorChange(s"${WHITE}")
  def red              = fontColorChange(s"${RED}")
  def blue             = fontColorChange(s"${BLUE}")
  def green            = fontColorChange(s"${GREEN}")
  def magenta          = fontColorChange(s"${MAGENTA}")
  def cyan             = fontColorChange(s"${CYAN}")
  def yellow           = fontColorChange(s"${YELLOW}")
  def defaultFontColor = fontColorChange("")
  def brightBlack      = fontColorChange("\u001b[30;1m")
  def brightWhite      = fontColorChange("\u001b[37;1m")

  def color(number: Int) = fontColorChange(s"\u001b[38;5;${number}m")

  // background color changing
  def bgColorChange(newColor: String) = StylizedText(text, fontColor, newColor, modifiers)

  def black_bg          = bgColorChange(s"${BLACK_B}")
  def white_bg          = bgColorChange(s"${WHITE_B}")
  def red_bg            = bgColorChange(s"${RED_B}")
  def blue_bg           = bgColorChange(s"${BLUE_B}")
  def green_bg          = bgColorChange(s"${GREEN_B}")
  def magenta_bg        = bgColorChange(s"${MAGENTA_B}")
  def cyan_bg           = bgColorChange(s"${CYAN_B}")
  def yellow_bg         = bgColorChange(s"${YELLOW_B}")
  def defaultBackground = bgColorChange("")

  // modifiers
  def applyModifier(newModifier: String) = StylizedText(text, fontColor, bgColor, modifiers + newModifier)

  def underlined     = applyModifier(s"${UNDERLINED}")
  def bold           = applyModifier(s"${BOLD}")
  def blinking       = applyModifier(s"${BLINK}")
  def cleanModifiers = StylizedText(text, fontColor, bgColor, "")
}
