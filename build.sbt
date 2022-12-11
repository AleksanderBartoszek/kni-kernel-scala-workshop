val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scalaWorkshop",
    organization := "kni.kernel",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )

// Part - 1
lazy val styling = (project in file("libs/styling"))
  .settings(name := "styling", organization := "kni.kernel", scalaVersion := scala3Version)

lazy val fancyText = (project in file("postChapterProjects/fancyText"))
  .dependsOn(styling)
  .settings(name := "fancyText", organization := "kni.kernel", scalaVersion := scala3Version)


// Part - 2
lazy val validation = (project in file("libs/validation"))
  .settings(
    name := "validation",
    scalaVersion := scala3Version,
  )
