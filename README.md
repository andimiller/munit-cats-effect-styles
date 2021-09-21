# munit-cats-effect-styles

This is a library for adding ScalaTest style testing layouts for munit with cats-effect.

This may seem quite opinionated, and it is, this is just the way I like to test things, although I am open to expanding it.

It's cross published for Scala 2.12, 2.13 and 3, and cats-effect 2 and 3.

## Dependencies

It's published to maven central and only depends on `munit-cats-effect`.

```scala
// for cats-effect-2
libraryDependencies += "net.andimiller" %% "munit-cats-effect-2-styles" % "1.0.0" % Test
testFrameworks += new TestFramework("munit.Framework")
// for cats-effect-3
libraryDependencies += "net.andimiller" %% "munit-cats-effect-3-styles" % "1.0.0" % Test
testFrameworks += new TestFramework("munit.Framework")
```

## Styles
### FlatSpec

Same as the ScalaTest style, this lets you write tests with the word `should` in the middle.

Since we're assuming cats-effect, the body for your `in` should be either an `IO[Any]` or  a scalacheck `Prop`.

```scala
import cats.effect.IO
import org.scalacheck.Prop.forAll
import net.andimiller.munit.cats.effect.styles.FlatIOSpec

class FlatIOSpecSpec extends FlatIOSpec {

  "a subject" should "do stuff" in {
    IO { 1234 }.assertEquals(1234)
  }

  "another subject" should "meet a property" in {
    forAll { (s: String) => s.reverse.reverse == s }
  }

}
```

### WordSpec

Same as the ScalaTest style, this lets you write nested tests using `should` and then `in`.

Since we're assuming cats-effect, the body for your `in` should be either an `IO[Any]` or  a scalacheck `Prop`.

```scala
import cats.effect.IO
import net.andimiller.munit.cats.effect.styles.WordIOSpec
import org.scalacheck.Prop.forAll

class WordIOSpecSpec extends WordIOSpec {

  "subject" should {
    "test one" in {
      IO {
        1234
      }.assertEquals(1234)
    }
    "test two" in {
      interceptIO[Throwable](
        IO {
          throw new Exception
        }
      )
    }
    "some property" in {
      forAll { (s: String) =>
        s.reverse.reverse == s
      }
    }
  }

}
```
