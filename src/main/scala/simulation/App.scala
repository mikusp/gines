package simulation

import akka.{StartSimulation, GinesActors}

object App {
  def main(args: Array[String]): Unit = {
    import GinesActors.publisher
    val simulation = GinesActors.makeSimulation
    simulation ! StartSimulation()

    (1 to 1000) map(x => Thread.sleep(1000))
  }
}
