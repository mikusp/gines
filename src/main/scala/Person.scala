sealed abstract class Age

case object Child extends Age
case object Teenager extends Age
case object Adult extends Age
case object Elderly extends Age

trait Agent {
  val health: Health = Healthy
  val virusEncounters: Int = 0
}

class Person(val age: Age, val routine: Stream[Behaviour]) extends Agent {
  def nextPhase: Person = new Person(age, routine.tail)
}
