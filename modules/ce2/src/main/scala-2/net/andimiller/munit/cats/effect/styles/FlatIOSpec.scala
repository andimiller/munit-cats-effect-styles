package net.andimiller.munit.cats.effect.styles

import cats.effect.IO
import munit.{CatsEffectSuite, ScalaCheckSuite}
import org.scalacheck.Prop

trait FlatIOSpec extends CatsEffectSuite with ScalaCheckSuite {

  case class ShouldAction(thing: String, action: String) {
    def in(body: IO[Any])(implicit loc: munit.Location) =
      test(s"$thing should $action")(body)(loc)
    def in(prop: Prop)(implicit loc: munit.Location)    =
      property(s"$thing should $action")(prop)(loc)
  }

  implicit class ShouldSyntax(s: String) {
    def should(doThing: String): ShouldAction = ShouldAction(s, doThing)
  }

}
