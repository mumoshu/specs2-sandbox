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
        private val prv2: Int = prv + 1
        private def transform(n: Int) = transform2(n)
        private def transform2(n: Int) = n.toString
        val fooVal1 = prv.toString
        val fooVal2 = prv2.toString
        lazy val fooLazyVal1 = transform(prv)
        lazy val fooLazyVal2 = transform(prv2)
        def fooDef1 = transform(prv)
      }
      val foo = mock[Foo]
      foo.src returns 1
      foo.fooLazyVal1 returns "3"
      foo.fooLazyVal1 must be equalTo ("3")
      foo.fooVal1 returns "4"
      foo.fooVal1 must be equalTo ("4")
      foo.fooLazyVal2 returns "4"
      foo.fooLazyVal2 must be equalTo ("4")
      foo.fooVal2 returns ("4")
      foo.fooVal2 must be equalTo ("4")
      foo.fooDef1 returns ("5")
      foo.fooDef1 must be equalTo ("5")

      case class Bar(src: Int) extends Foo
      val bar = mock[Bar]
      bar.src returns 1
      // It fails on Scala 2.9.1
      /*
        [error]     MissingMethodInvocationException:
        [error] when() requires an argument which has to be 'a method call on a mock'.
        [error] For example:
        [error]     when(mock.getArticles()).thenReturn(articles);
        [error]
        [error] Also, this error might show up because:
        [error] 1. you stub either of: final/private/equals()/hashCode() methods.
        [error]    Those methods *cannot* be stubbed/verified.
        [error] 2. inside when() you don't call method on mock but on some other object.
       */
      bar.fooLazyVal1 returns "3"
      bar.fooLazyVal1 must be equalTo ("3")
      // It fails on Scala 2.9.1
      /*
        [error] ! mock vals and lazy vals
        [error]     MissingMethodInvocationException:
        [error] when() requires an argument which has to be 'a method call on a mock'.
        [error] For example:
        [error]     when(mock.getArticles()).thenReturn(articles);
        [error]
        [error] Also, this error might show up because:
        [error] 1. you stub either of: final/private/equals()/hashCode() methods.
        [error]    Those methods *cannot* be stubbed/verified.
        [error] 2. inside when() you don't call method on mock but on some other object.
       */
      bar.fooVal1 returns "3"
      bar.fooVal1 must be equalTo ("3")
      // It fails on Scala 2.9.1
      /*
        [error] ! mock vals and lazy vals
        [error]     MissingMethodInvocationException:
        [error] when() requires an argument which has to be 'a method call on a mock'.
        [error] For example:
        [error]     when(mock.getArticles()).thenReturn(articles);
        [error]
        [error] Also, this error might show up because:
        [error] 1. you stub either of: final/private/equals()/hashCode() methods.
        [error]    Those methods *cannot* be stubbed/verified.
        [error] 2. inside when() you don't call method on mock but on some other object.
       */
      bar.fooDef1 returns ("5")
      bar.fooDef1 must be equalTo ("5")

      trait Baz {
        val src: Int
        private val prv: Int = src + 1
        private val prv2: Int = prv + 1
        private def transform(n: Int) = transform2(n)
        private def transform2(n: Int) = n.toString
        lazy val bazLazyVal1 = transform(prv)
        val bazVal1 = prv.toString
        lazy val bazLazyVal2 = transform(prv2)
        val bazVal2 = prv2.toString
      }
      case class Hoge(src: Int) extends Foo with Baz
      val hoge = mock[Hoge]
      hoge.src returns 1
      hoge.fooLazyVal1 returns "3"
      hoge.fooVal1 returns "3"
      hoge.fooLazyVal2 returns "3"
      hoge.fooVal2 returns "3"
      hoge.bazLazyVal1 returns "3"
      hoge.bazVal1 returns "3"
      hoge.bazLazyVal2 returns "3"
      hoge.bazVal2 returns "3"

      hoge.fooLazyVal1 must be equalTo ("3")
      hoge.fooVal1 must be equalTo ("3")
      hoge.fooLazyVal2 must be equalTo ("3")
      hoge.fooVal2 must be equalTo ("3")
      hoge.bazLazyVal1 must be equalTo ("3")
      hoge.bazVal1 must be equalTo ("3")
      hoge.bazLazyVal2 must be equalTo ("3")
      hoge.bazVal2 must be equalTo ("3")

      val a = mock[com.github.mumoshu.specs2.tester.Baz]
      
      a.src returns 1
      
      a.fooDef1 returns "3"
      a.fooDef2 returns "3"
      a.barDef1 returns "3"
      a.barDef2 returns "3"
      
      a.fooVal1 returns "3"
      a.fooVal2 returns "3"
      a.barVal1 returns "3"
      a.barVal2 returns "3"
      
      a.fooLazyVal1 returns "3"
      a.fooLazyVal2 returns "3"
      a.barLazyVal1 returns "3"
      a.barLazyVal2 returns "3"
      

      a.fooDef1 must be equalTo ("3")
      a.fooDef2 must be equalTo ("3")
      a.barDef1 must be equalTo ("3")
      a.barDef2 must be equalTo ("3")

      a.fooVal1 must be equalTo ("3")
      a.fooVal2 must be equalTo ("3")
      a.barVal1 must be equalTo ("3")
      a.barVal2 must be equalTo ("3")
      
      a.fooLazyVal1 must be equalTo ("3")
      a.fooLazyVal2 must be equalTo ("3")
      a.barLazyVal1 must be equalTo ("3")
      a.barLazyVal2 must be equalTo ("3")
    }
  }
}