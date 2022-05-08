package net.andimiller.munit.cats.effect.styles

import cats.effect.IO
import munit.{CatsEffectSuite, ScalaCheckSuite}
import org.scalacheck.Prop

trait WordIOSpec extends CatsEffectSuite with ScalaCheckSuite {

  implicit class WordShouldSyntax(s: String) {
    def should(body: => Any): Unit = {
      val start = munitTestsBuffer.length
      body
      val end   = munitTestsBuffer.length
      (start until end).map { i =>
        munitTestsBuffer(i) = {
          val t = munitTestsBuffer(i)
          munitTestsBuffer(i).withName(s"$s should ${t.name}")
        }
      }
    }
  }

  implicit class WordInSyntax(action: String) {
    def in(body: IO[Any])(implicit loc: munit.Location) =
      test(action)(body)(loc)
    def in(prop: Prop)(implicit loc: munit.Location)    =
      property(action)(prop)(loc)
  }

}
