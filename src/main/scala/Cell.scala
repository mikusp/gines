class Cell(people: List[Person], val typ: CellType)

sealed abstract class CellType

case object School extends CellType
case object Work extends CellType
case object Home extends CellType
