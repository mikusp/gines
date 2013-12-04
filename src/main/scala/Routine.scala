case class Behaviour(cell: Cell, tc: TimeChunk)

object RoutineGenerator {
  implicit class RandomList[T](l: List[T]) {
    def shuffle = util.Random.shuffle(l)
  }

  def apply(age: Age, activities: Map[(Age, TimeChunk), List[CellType]],
    world: Map[(Int, Int), Cell]): Stream[Behaviour] = {

    val getTypes = (tc: TimeChunk) => {
      activities.getOrElse((age, tc), List[CellType]())
    }

    val cell = (tc: TimeChunk) => {
      (for {
        types <- getTypes(tc)
        cells <- world if cells._2.typ == types
      } yield cells._2, tc)
    }

    val cells = List(cell(Morning), cell(Afternoon), cell(Evening),
      cell(Night))

    // ogromny błąd - może wylosować np. dwa różne domy na wieczór i na noc :D
    // TODO

    val behaviours = cells map { case (x, tc) => Behaviour(x.shuffle.head, tc) }

    Stream.continually(behaviours).flatten
  }

  def swapFirst(b: Behaviour)(s: Stream[Behaviour]): Stream[Behaviour] = {
    import collection.immutable.Stream.Empty
    def mapOnce[A](f: A => Option[A], xs: Stream[A]): Stream[A] = xs match {
      case Empty => Empty
      case a #:: as => f(a) match {
        case None => a #:: mapOnce(f, as)
        case Some(s) => s #:: as
      }
    }

    def swap(newB: Behaviour)(b: Behaviour): Option[Behaviour] =
      if (b.tc == newB.tc) Some(newB) else None

    mapOnce(swap(b)_, s)
  }
}
