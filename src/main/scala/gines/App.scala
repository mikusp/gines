package gines

import gines.akka.{StartSimulation, GinesActors}
import gines.simulation.{Virus, Morning, SimulationState, Foo}
import gines.utils.GinesLogging

object App extends GinesLogging {
  def main(args: Array[String]): Unit = {
    import GinesActors.publisher

    val world = Foo.genWorld(10, 10)
    val population = Foo.populateWorld(world)
    log.debug(s"Generated population size is: ${population.length}")
    val initialState = SimulationState(0, Morning, population, world)
    val virus = Virus(0.05, 50)

    val simulation = GinesActors.makeSimulation(initialState, virus)

    simulation ! StartSimulation()

    GinesActors.system.awaitTermination()
  }
}
