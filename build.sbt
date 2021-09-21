import xerial.sbt.Sonatype._

name := "munit-cats-effect-styles"

version := "0.1"

scalaVersion := "3.0.2"

val munitCatsEffectVersion = "1.0.5"
val scalaVersions          = List("2.13.6", "2.12.14", "3.0.2")
val commonSettings         = List(
  crossScalaVersions         := scalaVersions,
  organization               := "net.andimiller",
  crossPaths                 := true,
  testFrameworks += new TestFramework("munit.Framework"),
  scalaVersion               := "3.0.2",
  ThisBuild / scalafmtConfig := file(".scalafmt.conf"),
  useGpg := true,
  publishTo := sonatypePublishTo.value,
  licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT")),
  sonatypeProjectHosting := Some(GitHubHosting("andimiller", "munit-cats-effect-styles", "andi at andimiller dot net")),
  developers := List(Developer(id = "andimiller", name = "Andi Miller", email = "andi@andimiller.net", url = url("http://andimiller.net"))),
)

lazy val root = project
  .in(file("."))
  .aggregate(ce2, ce3)
  .settings(
    crossScalaVersions := Nil,
    publish / skip     := true
  )

lazy val ce2 = project
  .in(file("modules/ce2"))
  .settings(commonSettings)
  .settings(
    name                                   := "munit-cats-effect-2-styles",
    libraryDependencies += "org.typelevel" %% "munit-cats-effect-2" % munitCatsEffectVersion,
    libraryDependencies += "org.scalameta" %% "munit-scalacheck"    % "0.7.29"
  )

lazy val ce3 = project
  .in(file("modules/ce3"))
  .settings(commonSettings)
  .settings(
    name                                   := "munit-cats-effect-3-styles",
    libraryDependencies += "org.typelevel" %% "munit-cats-effect-3" % munitCatsEffectVersion,
    libraryDependencies += "org.scalameta" %% "munit-scalacheck"    % "0.7.29"
  )
