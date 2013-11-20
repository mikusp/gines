sealed abstract class Health

case class Susceptible extends Health
case class Infected extends Health
case class Immune extends Health
case class Dead extends Health

sealed abstract class Age

case class Child extends Age
case class Adult extends Age
case class Elderly extends Age

class Agent(age: Age, routine: Routine) {
  var health: Health = Susceptible
  var virusEncounters: Int = 0
}
