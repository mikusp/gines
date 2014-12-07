package gines.simulation



object RoutineGenerator {
  import Conf.activities

  implicit class RandomList[T](l: List[T]) {
    import org.scalacheck.Gen
    def randomElem = {
      val intGen = Gen.choose(0, l.length - 1)
      l(intGen.sample.get)
    }
  }

  def apply(age: Age, home: Cell,
    world: Map[(Int, Int), Cell]): Stream[Behaviour] = {

    val possibleTypes = (for {
      chunks <- TimeChunks.apply
      types <- activities((age, chunks))
    } yield types).distinct

    val cells = for {
      t <- possibleTypes
      cells <- world if cells._2.typ == t
    } yield cells._2

    val celltypeToRandomCell = cells.groupBy(_.typ).map {
      case (Home, _) => (Home, home)
      case (FakeHome, _) => (FakeHome, home)
      case (t, cs) => (t, cs.randomElem)
    }

    val behaviours = TimeChunks.apply map ((tc: TimeChunk) => {
      val celltype = activities((age, tc)).randomElem

      Behaviour(celltypeToRandomCell(celltype), tc)
    })

    Stream.continually(behaviours).flatten
  }
}
