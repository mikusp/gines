class Cell(people: List[Agent], val typ: CellType)

sealed abstract class CellType

case object School extends CellType
case object Work extends CellType
case object Home extends CellType
