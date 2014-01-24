package gines

import _root_.akka.actor.Props
import com.typesafe.config.ConfigFactory

import gines.akka.{AdminActor, StartSimulation, GinesActors}
import gines.simulation.{Virus, Morning, SimulationState, Foo}
import gines.utils.GinesLogging
import java.io.File

object App extends GinesLogging {
  def main(args: Array[String]): Unit = {

    val conf = if(args.length > 0) {
      Some(args(0))
    } else {
      None
    }

    GinesActors.system.actorOf(Props(classOf[AdminActor], conf), name="admin")

    GinesActors.system.awaitTermination()
  }

  def createSimulation(config: Option[String]): Unit = {
    import GinesActors.publisher

    val conf = config map { file =>
      ConfigFactory.parseFile(new File(file))
    } getOrElse {
      ConfigFactory.load
    }

    val world = Foo.genWorld(conf.getInt("simulation.params.world.width"), conf.
      getInt("simulation.params.world.height"))
    val population = Foo.populateWorld(world)
    log.debug(s"Generated population size is: ${population.length}")
    val initialState = SimulationState(0, Morning, population, world)
    val virus = Virus(conf.getDouble("simulation.params.virus.infectivity"),
      conf.getInt("simulation.params.virus.curveFactor"))

    val simulationMaker = GinesActors.makeSimulation("simulation") _
    val simulation = simulationMaker(initialState, virus)

    simulation ! StartSimulation
  }
}
