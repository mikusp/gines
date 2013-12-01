sealed abstract class Age

case object Child extends Age
case object Teenager extends Age
case object Adult extends Age
case object Elderly extends Age

trait Agent {
  val health: Health = Healthy
  val virusEncounters: Int = 0
}

class Person(age: Age, routine: Stream[Behaviour]) extends Agent
