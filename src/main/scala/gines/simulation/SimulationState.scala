package gines.simulation

import RoutineGenerator.RandomList

case class SimulationState(
  day: Int,
  chunk: TimeChunk,
  agents: List[Person],
  world: Map[(Int, Int), Cell]) {
  def step(f: Person=>Person): SimulationState = {
    lazy val numberOfAgents = agents.length

    lazy val (ill, rest) = agents partition (_.health match {
      case Ill(_) => true
      case _ => false
    })

    lazy val (healthy, immune) = rest partition (_.health match {
      case Healthy => true
      case _ => false
    })

    assert(ill.length + healthy.length + immune.length == numberOfAgents)

    def worldAdjacent(w: Map[Cell, (Int, Int)], c1: Cell, c2: Cell): Boolean = {
      if (c1.typ != c2.typ)
        false
      else {
        val (x1, y1) = w(c1)
        val (x2, y2) = w(c2)
        (x1 == x2 && math.abs(y1 - y2) == 1) || (math.abs(x1 - x2) == 1 && y1 == y2)
      }
    }

    lazy val outsideWorld = world.map(_.swap)

    lazy val healthyPeopleInAdjacentCells = (ill map (i => healthy filter (j =>
      worldAdjacent(outsideWorld, j.routine.head.cell, i.routine.head.cell))) flatten).toSet
    lazy val restOfHealthyPeople = healthy.filterNot(healthyPeopleInAdjacentCells)
    lazy val potentiallyInfectedPeople = healthyPeopleInAdjacentCells map f
    lazy val allPeople = (potentiallyInfectedPeople.toList ++ restOfHealthyPeople ++ ill ++ immune) map (_.nextPhase)
    val nextChunk = chunk match {
      case Morning => Afternoon
      case Afternoon => Evening
      case Evening => Night
      case Night => Morning
    }

    assert(allPeople.length == numberOfAgents, "We are messing with agents!!!")

    SimulationState(if (nextChunk == Morning) day + 1 else day, nextChunk, allPeople, world)
  }
}

object RandomWorldGenerator extends WorldGenerator {

  def apply(x: Int, y: Int): Map[(Int, Int), Cell] = {
    val world = collection.mutable.Map[(Int, Int), Cell]()

    for (i <- 1 to x) {
      for (j <- 1 to y) {
        // TODO
        // get all subclasses of CellType from the compiler
        val t = List(School, Work, Home).randomElem

        world += (i, j) -> new Cell(t)
      }
    }

    world.toMap
  }
}

trait WorldGenerator {
  def apply(x: Int, y: Int): Map[(Int, Int), Cell]
}
