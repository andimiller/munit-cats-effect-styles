package net.andimiller.munit.cats.effect.styles

import cats.effect.IO
import munit.{CatsEffectSuite, ScalaCheckSuite}
import org.scalacheck.Prop

import scala.annotation.targetName

trait FlatIOSpec extends CatsEffectSuite with ScalaCheckSuite {

  opaque type ShouldAction = (String, String)

  extension (thing: String) def should(action: String): ShouldAction = (thing, action)

  extension (s: ShouldAction)
    @targetName("inIO")
    def in(body: => IO[Any])(using loc: munit.Location) = test(s"${s._1} should ${s._2}")(body)(loc)

    @targetName("inProp")
    def in(body: => Prop)(using loc: munit.Location): Unit =
      property(s"${s._1} should ${s._2}")(body)(loc)

}
