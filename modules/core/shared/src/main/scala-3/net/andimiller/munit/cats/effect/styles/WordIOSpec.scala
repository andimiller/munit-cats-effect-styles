package net.andimiller.munit.cats.effect.styles

import cats.effect.IO
import munit.{CatsEffectSuite, ScalaCheckSuite}
import org.scalacheck.Prop

import scala.annotation.targetName

trait WordIOSpec extends CatsEffectSuite with ScalaCheckSuite {
  opaque type Subject = String

  extension (s: String)
    def should(body: => Subject ?=> Any): Any =
      given Subject = s
      body

    def in(body: => IO[Any])(using subject: Subject, loc: munit.Location): Unit =
      test(s"$subject should $s")(body)(loc)

    @targetName("inProp") inline def in(body: => Prop)(using subject: Subject, loc: munit.Location): Unit =
      property(s"$subject should $s")(body)(loc)

}
