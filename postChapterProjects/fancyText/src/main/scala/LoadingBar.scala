package postChapterProjects.fancyText

import scala.annotation.tailrec

class LoadingBar(val progress: Int = 0) {
  @tailrec
  final def animate(currentProgress: Int = 0): Unit = {
    print("[")
    for (e <- 0 to currentProgress) print("#")
    for (e <- currentProgress to 30) print(" ")
    print("]")
    Thread.sleep(100)
    println("\u001b[2J")
    if (currentProgress < 30)
      animate(currentProgress + 1)
  }
}
