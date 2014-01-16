package gines

import _root_.akka.actor.Props
import com.typesafe.config.ConfigFactory

import gines.akka.{AdminActor, StartSimulation, GinesActors}
import gines.simulation.{Virus, Morning, SimulationState, Foo}
import gines.utils.GinesLogging

object App extends GinesLogging {
  def main(args: Array[String]): Unit = {
    GinesActors.system.actorOf(Props[AdminActor], name="admin")

    if(args.length > 0 && args(0).toLowerCase == "gui") {
      import GinesActors.publisher

      val conf = ConfigFactory.load

      val world = Foo.genWorld(conf.getInt("simulation.params.world.width"), conf.
        getInt("simulation.params.world.height"))
      val population = Foo.populateWorld(world)
      log.debug(s"Generated population size is: ${population.length}")
      val initialState = SimulationState(0, Morning, population, world)
      val virus = Virus(conf.getDouble("simulation.params.virus.infectivity"),
        conf.getInt("simulation.params.virus.curveFactor"))

      val simulationMaker = GinesActors.makeSimulation("simulation")_
      val simulation = simulationMaker(initialState, virus)

      simulation ! StartSimulation()
    }

    GinesActors.system.awaitTermination()
  }
}
