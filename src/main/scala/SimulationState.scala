class SimulationState(
  day: Int,
  chunk: TimeChunk,
  agents: List[Person],
  world: Map[(Int, Int), Cell]) {
  def step: SimulationState = this
}
