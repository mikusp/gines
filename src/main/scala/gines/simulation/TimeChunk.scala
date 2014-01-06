package gines.simulation

sealed abstract class TimeChunk {
  val name: String
}

case object Morning extends TimeChunk {
  val name: String = "Morning"
}

case object Afternoon extends TimeChunk {
  val name: String = "Afternoon"
}

case object Evening extends TimeChunk {
  val name: String = "Evening"
}

case object Night extends TimeChunk {
  val name: String = "Night"
}

object TimeChunks {
  def apply: List[TimeChunk] = List(Morning, Afternoon, Evening, Night)
}