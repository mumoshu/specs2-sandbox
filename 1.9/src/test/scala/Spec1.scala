import org.specs2.matcher.DataTables
import org.specs2.mock.Mockito
import org.specs2.mutable._

object Spec1 extends Specification with DataTables with Mockito {

  "Foo" should {
    "bar" in {

      "A" | "B" |
        1 !! true |
        2 !! false |
        3 !! true |
        4 !! false |
        5 !! false |> {
        (num, doThrow) =>
          if (doThrow)
            throw new RuntimeException("a simulated exception")
          else
            num must equalTo (num)
      }
    }
  }

  "Mockito" in {
    "mock vals and lazy vals" in {
      trait Foo {
        val src: Int
        private val prv: Int = src + 1
        private def transform(n: Int) = n.toString
        lazy val myLazyVal = transform(prv)
        val myVal = prv.toString
      }
      val foo = mock[Foo]
      foo.src returns 1
      foo.myLazyVal returns "3"
      foo.myVal returns "4"
      foo.myLazyVal must be equalTo ("3")
      foo.myVal must be equalTo ("4")
      foo.myVal must be equalTo ("4")

      case class Bar(src: Int) extends Foo
      val bar = mock[Bar]
      bar.src returns 1
      bar.myLazyVal returns "3"
      bar.myLazyVal must be equalTo ("3")
      bar.myVal returns "3"
      bar.myVal must be equalTo ("3")

      trait Baz {
        val src: Int
        private val prv: Int = src + 1
        private def transform(n: Int) = n.toString
        lazy val myLazyVal2 = transform(prv)
        val myVal2 = prv.toString
      }
      case class Hoge(src: Int) extends Foo with Baz
      val hoge = mock[Hoge]
      hoge.src returns 1
      hoge.myLazyVal returns "3"
      hoge.myLazyVal must be equalTo ("3")
      hoge.myVal returns "3"
      hoge.myVal must be equalTo ("3")
      hoge.myLazyVal2 returns "3"
      hoge.myLazyVal2 must be equalTo ("3")
      hoge.myVal2 returns "3"
      hoge.myVal2 must be equalTo ("3")
    }
  }
}