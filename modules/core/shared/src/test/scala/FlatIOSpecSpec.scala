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
