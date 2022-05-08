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
