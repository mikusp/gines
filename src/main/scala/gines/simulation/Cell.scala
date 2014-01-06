package gines.simulation

class Cell(t: CellType) {
  val typ = t
}

sealed abstract class CellType {
  val name: String
}

case object School extends CellType{
  val name: String = "School"
}

case object Work extends CellType{
  val name: String = "Work"
}

case object Home extends CellType{
  val name: String = "Home"
}