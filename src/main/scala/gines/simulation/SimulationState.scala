package gines.simulation

import RoutineGenerator.RandomList
import com.google.common.collect.{HashMultiset}
import scala.collection.{mutable, immutable}
import scala.collection.JavaConverters._

case class SimulationState(
  day: Int,
  chunk: TimeChunk,
  agents: Vector[Person],
  world: Map[(Int, Int), Cell]) {
  def step(f: Person=>Person): SimulationState = {
    val numberOfAgents = agents.length

    val (ill, rest) = agents partition (_.health match {
      case Ill(_) => true
      case _ => false
    })

    val (healthy, immuneAndExposed) = rest partition (_.health match {
      case Healthy => true
      case _ => false
    })

    assert(ill.length + healthy.length + immuneAndExposed.length == numberOfAgents)

    def worldAdjacent(w: Map[Cell, (Int, Int)], c1: Cell, c2: Cell): Boolean = {
      if (c1.typ != c2.typ)
        false
      else {
        val (x1, y1) = w(c1)
        val (x2, y2) = w(c2)
        (x1 == x2 && y1 == y2) || (x1 == x2 && math.abs(y1 - y2) == 1) || (math.abs(x1 - x2) == 1 && y1 == y2)
      }
    }

    val outsideWorld = world.map(_.swap)

    val healthyInAdjacent: HashMultiset[Person] = HashMultiset.create()
    ill foreach (i => healthy foreach (j =>
      if (worldAdjacent(outsideWorld, i.routine.head.cell, j.routine.head.cell))
        healthyInAdjacent.add(j)))

    def iterate[A](n: Int, f: A => A, e: A): A = n match {
      case 0 => e
      case _ => iterate(n-1, f, f(e))
    }

    val restOfHealthyPeople = healthy.toSet &~ healthyInAdjacent.elementSet.asScala
    val potentiallyInfectedPeople = healthyInAdjacent.elementSet.asScala.map((e) =>
      iterate(healthyInAdjacent.count(e), f, e))

    val allPeople = (potentiallyInfectedPeople.toVector ++ restOfHealthyPeople ++ ill ++ immuneAndExposed) map (_.nextPhase)
    val nextChunk = chunk.next

    assert(allPeople.length == numberOfAgents, "We are messing with agents!!!")

    SimulationState(if (nextChunk == Morning) day + 1 else day, nextChunk, allPeople, world)
  }
}

object RandomWorldGenerator extends WorldGenerator {

  def apply(x: Int, y: Int): Map[(Int, Int), Cell] = {
    val world = collection.mutable.Map[(Int, Int), Cell]()

    for (i <- 1 to x) {
      for (j <- 1 to y) {
        // TODO                   i
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
