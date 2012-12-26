import org.specs2.mock.Mockito
import org.specs2.mutable._

object SpecsExample extends Specification with Mockito {

  trait Foo {

    def a: Boolean
    def b(arg: => Int) = false
    def c(arg: => Int)(arg2: => Int) = false
    def d[A](arg: => Int)(arg2: => A): A  = arg2
    def e[A](arg: Int)(arg2: => A): A  = arg2
  }

  "a" should {
    "b" in {
      val m = mock[Foo]

      m.a returns true
      m.b(1) returns true
      m.c(1)(2) returns true
      m.d(1){"ok1"} returns "ok2"

      m.a must be equalTo true
      m.b(1) must be equalTo true
      m.c(1)(2) must be equalTo true
      m.d(1){"ok1"} must be equalTo "ok2"
    }
    "c" in {
      val m = mock[Foo].defaultReturn(true)

      m.a must beTrue
      m.b(1) must beTrue
    }
    "d" in {
      val m = mock[Foo]

      def beOne: Int = beLike[Int] {
        case 1 => ok
      }

      def beTwo: Int = beLike[Int] {
        case 2 => ok
      }

      m.d(beOne)(beTwo) returns 1

      m.d(1)(2) must be equalTo (1)

      there was one(m).d(beOne)(beTwo)

    }
    "e" in {
      val m = mock[Foo]

      def beOne: Int = beLike[Int] {
        case 1 => ok
      }

      def beTwo: Int = beLike[Int] {
        case 2 => ok
      }

      // This fails with an InvalidUseOfMatchersException => `m.e(beOne)(beTwo) retruns 1`
      m.e(1)(beTwo) returns 1

      m.e(1)(2) must be equalTo (1)

      there was one(m).e(1)(beTwo)

    }
  }

}
