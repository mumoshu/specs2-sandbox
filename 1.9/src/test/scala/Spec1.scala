import org.specs2.matcher.DataTables
import org.specs2.mutable._

object Spec1 extends Specification with DataTables {

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
}