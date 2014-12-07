package gines.simulation

class Cell(val typ: CellType)

sealed abstract class CellType {
  val name: String
}

case object School extends CellType {
  val name: String = "School"
}

case object Work extends CellType {
  val name: String = "Work"
}

case object Home extends CellType {
  val name: String = "Home"
}

case object FakeHome extends CellType {
  val name : String = "FakeHome"
}