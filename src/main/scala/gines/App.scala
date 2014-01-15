package gines

import com.typesafe.config.ConfigFactory

import gines.akka.{StartSimulation, GinesActors}
import gines.simulation.{Virus, Morning, SimulationState, Foo}
import gines.utils.GinesLogging

object App extends GinesLogging {
  def main(args: Array[String]): Unit = {
    import GinesActors.publisher

    val conf = ConfigFactory.load

    val world = Foo.genWorld(conf.getInt("simulation.params.world.width"), conf.
      getInt("simulation.params.world.height"))
    val population = Foo.populateWorld(world)
    log.debug(s"Generated population size is: ${population.length}")
    val initialState = SimulationState(0, Morning, population, world)
    val virus = Virus(conf.getDouble("simulation.params.virus.infectivity"),
      conf.getInt("simulation.params.virus.curveFactor"))

    val simulationMaker = GinesActors.makeSimulation("localhost")_
    val simulation = simulationMaker(initialState, virus)

    simulation ! StartSimulation()

    GinesActors.system.awaitTermination()
  }
}
