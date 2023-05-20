import xerial.sbt.Sonatype._

name := "munit-cats-effect-styles"

val munitCatsEffectVersion = "2.0.0-M3"
val scalaVersions          = List("2.13.6", "2.12.14", "3.1.2")
val commonSettings         = List(
  crossScalaVersions         := scalaVersions,
  organization               := "net.andimiller",
  crossPaths                 := true,
  testFrameworks += new TestFramework("munit.Framework"),
  version                    := "2.0.0-M1",
  scalaVersion               := "3.1.2",
  ThisBuild / scalafmtConfig := file(".scalafmt.conf"),
  useGpg                     := true,
  publishTo                  := sonatypePublishTo.value,
  licenses                   := Seq("MIT" -> url("https://opensource.org/licenses/MIT")),
  sonatypeProjectHosting     := Some(GitHubHosting("andimiller", "munit-cats-effect-styles", "andi at andimiller dot net")),
  developers                 := List(
    Developer(
      id = "andimiller",
      name = "Andi Miller",
      email = "andi@andimiller.net",
      url = url("http://andimiller.net")
    )
  )
)

lazy val core = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("modules/core"))
  .settings(commonSettings)
  .settings(
    name                                    := "munit-cats-effect-styles",
    libraryDependencies += "org.typelevel" %%% "munit-cats-effect" % munitCatsEffectVersion,
    libraryDependencies += "org.scalameta" %%% "munit-scalacheck"  % "1.0.0-M7"
  )
