class SimulationState(
  day: Int,
  chunk: TimeChunk,
  agents: List[Agent],
  world: Map[(Int, Int), Cell]) {
  def step: SimulationState = this
}
