package simulation

sealed abstract class TimeChunk

case object Morning extends TimeChunk

case object Afternoon extends TimeChunk

case object Evening extends TimeChunk

case object Night extends TimeChunk

object TimeChunks {
  def apply: List[TimeChunk] = List(Morning, Afternoon, Evening, Night)
}