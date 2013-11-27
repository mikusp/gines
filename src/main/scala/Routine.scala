import RandomListImplicit._

case class Behaviour(cell: Cell)

object RoutineGenerator {
  def apply(age: Age, activities: Map[(Age, TimeChunk), List[CellType]],
    world: Map[(Int, Int), Cell]): Stream[Behaviour] = {

    val getTypes = (tc: TimeChunk) => {
      activities.getOrElse((age, tc), List[CellType]())
    }

    val cell = (tc: TimeChunk) => {
      for {
        types <- getTypes(tc)
        cells <- world if cells._2.typ == types
      } yield cells._2
    }

    val cells = List(cell(Morning), cell(Afternoon), cell(Evening),
      cell(Night))

    val behaviours = cells map ((x: List[Cell]) => new Behaviour(x.shuffle.head))

    Stream.continually(behaviours).flatten
  }
}

class RandomList[T](l: List[T]) {
  def shuffle = util.Random.shuffle(l)
}

object RandomListImplicit {
  implicit def toRandom[T](l: List[T]): RandomList[T] = new RandomList(l)
}
