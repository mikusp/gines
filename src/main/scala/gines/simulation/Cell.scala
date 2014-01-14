package gines.simulation

import java.util.UUID

class Cell(t: CellType, private val uuid: UUID) {
  val typ = t

  def this(t: CellType) = this(t, UUID.randomUUID)

  override def equals(obj: Any) = this.uuid == obj.asInstanceOf[Cell].uuid
  override def hashCode() = uuid.hashCode()
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