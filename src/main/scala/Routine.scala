case class Behaviour(cell: Cell, tc: TimeChunk)

object RoutineGenerator {
  implicit class RandomList[T](l: List[T]) {
    def shuffle = util.Random.shuffle(l)
  }

  def apply(age: Age, activities: Map[(Age, TimeChunk), List[CellType]],
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
      case (t, cs) => (t, cs.shuffle.head)
    }

    val behaviours = TimeChunks.apply map ((tc: TimeChunk) => {
      val celltype = activities((age, tc)).shuffle.head

      Behaviour(celltypeToRandomCell(celltype), tc)
    })

    Stream.continually(behaviours).flatten
  }

  def swapFirst(b: Behaviour)(implicit s: Stream[Behaviour]): Stream[Behaviour] = {
    import collection.immutable.Stream.Empty
    def mapOnce[A](f: A => Option[A], xs: Stream[A]): Stream[A] = xs match {
      case Empty => Empty
      case a #:: as => f(a) match {
        case None => a #:: mapOnce(f, as)
        case Some(fa) => fa #:: as
      }
    }

    def swap(newB: Behaviour)(b: Behaviour): Option[Behaviour] =
      if (b.tc == newB.tc) Some(newB) else None

    mapOnce(swap(b)_, s)
  }
}
