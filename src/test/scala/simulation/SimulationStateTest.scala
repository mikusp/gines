package simulation

import org.scalatest.FunSuite

class SimulationStateTest extends FunSuite {
  test("it should go to next phase") {
    val beh = Stream[Behaviour](
      new Behaviour(new Cell(Home), Night),
      new Behaviour(new Cell(Home), Morning)
    )
    val people = List(new Person(Adult, beh))
    val world = Map[(Int, Int), Cell]( (0,0)->new Cell(Home) )
    val stepOne = SimulationState(1, Night, people, world)
    val stepTwo = stepOne.step{
      p => p
    }

    assert(stepTwo.day == stepOne.day+1, "days should pass away")
    assert(stepTwo.chunk == Morning, "simulation.TimeChunk should move")
    stepTwo.agents.foreach{ p =>
      assert(p.routine.head.tc == stepTwo.chunk, "people should have same time chunk as simulation")
    }
    assert(stepTwo.world == stepOne.world, "World shoudl not change")
  }
}
