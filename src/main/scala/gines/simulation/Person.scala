package gines.simulation

class Person(val age: Age, val routine: Stream[Behaviour],
  override val health: Health = Healthy, override val
  virusEncounters: Int = 0) extends Agent {
  def nextPhase: Person = new Person(age, routine.tail, health.advance, virusEncounters)

  def copy(age: Age = this.age, routine: Stream[Behaviour] = this.routine,
    health: Health = this.health, virusEncounters: Int = this.virusEncounters) =
      new Person(age, routine, health, virusEncounters)
}

sealed abstract class Age

case object Child extends Age

case object Teenager extends Age

case object Adult extends Age

case object Elderly extends Age

trait Agent {
  val health: Health = Healthy
  val virusEncounters: Int = 0
}

sealed abstract class Sex

case object Male extends Sex

case object Female extends Sex

object Foo {
  import org.scalacheck.Gen

  // dane dla dzieci obliczone nast.: s. 120, tab. 4 (65)
  // suma p. proc. dla osob w wieku 0-2, 3-6, 7-12 w miastach wynosi 12,1%
  // 23360 * 12,1 ~= 2827
  // dane dla nastolatków:
  // liczba ludności w wieku przedprodukcyjnym - liczba dzieci = 4240
  def ageGen: Gen[Age] = Gen.frequency((2827, Child), (4240, Teenager), (24606, Adult), (6861, Elderly))
  def singleAgeGen: Gen[Age] = Gen.frequency((24606, Adult), (6861, Elderly))

  def celltypeGen: Gen[CellType] = Gen.frequency((90, Home), (9, Work), (1, School))

  // dane s. 127, tab. 10 (71)
  def householdSizeGen: Gen[Int] = Gen.frequency((2491, 1), (2590, 2), (1942, 3), (1362, 4), (765, 5))

  def genWorld(x: Int, y: Int): Map[(Int, Int), Cell] = {
    val world = collection.mutable.Map[(Int, Int), Cell]()

    for (i <- 1 to x) {
      for (j <- 1 to y) {
        val cell = celltypeGen.sample.map(new Cell(_)).get

        world += (i, j) -> cell
      }
    }

    world.toMap
  }

  def populateWorld(world: Map[(Int, Int), Cell]): Vector[Person] =
    world.filter{ case (_, c) => c.typ == Home }.foldLeft(Vector[Person]())((list, home) => home match {
      case (_, c) => {
        val size = householdSizeGen.sample.get

        val routineF = (a: Age) => RoutineGenerator(a, c, world)
        val firstPersonAge = singleAgeGen.sample.get

        val newPeople = new Person(firstPersonAge, routineF(firstPersonAge)) +:
        ((2 to size).map(_ => ageGen.sample.get) map {
            age => new Person(age, routineF(age))
        }).to[Vector]

        newPeople ++ list
      }
    })
}
