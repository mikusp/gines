class SimulationState(
  day: Int,
  chunk: TimeChunk,
  agents: List[Person],
  world: Map[(Int, Int), Cell]) {
  def step: SimulationState = this
}


object World {
  import RoutineGenerator._

  def apply(x: Int, y: Int): Map[(Int, Int), Cell] = {
    val world = collection.mutable.Map[(Int, Int), Cell]()

    for (i <- 1 to x) {
      for (j <- 1 to y) {
        // TODO
        // get all subclasses of CellType from the compiler
        val t = List(School, Work, Home).shuffle.head

        world += (i, j) -> new Cell(t)
      }
    }

    world.toMap
  }
}
