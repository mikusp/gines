case class SimulationState(
  day: Int,
  chunk: TimeChunk,
  agents: List[Person],
  world: Map[(Int, Int), Cell]) {
  def step(f: Person=>Person): SimulationState = {
    val people = agents map (f(_).nextPhase)
    val nextChunk = chunk match {
      case Morning => Afternoon
      case Afternoon => Evening
      case Evening => Night
      case Night => Morning
    }
    SimulationState(day+1, nextChunk, people, world)
  }
}


object RandomWorldGenerator extends WorldGenerator {
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

trait WorldGenerator {
  def apply(x: Int, y: Int): Map[(Int, Int), Cell]
}