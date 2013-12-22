package simulation

import org.scalatest.FunSuite

class PersonTest extends FunSuite {
  test("should be morning after night") {
    val home = new Cell(Home)
    val night = new Behaviour(home, Night)
    val morning = new Behaviour(home, Morning)
    val p = new Person(Adult, Stream(night, morning))

    val nextPhase = p.nextPhase.routine.head
    assert(nextPhase.cell.typ == Home, "simulation.Person should be at home")
    assert(nextPhase.tc == Morning, "It should be morning")
  }
}
