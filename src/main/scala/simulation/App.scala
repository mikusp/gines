package simulation

import akka.{StartSimulation, GinesActors}

object App {
  def main(args: Array[String]): Unit = {
    import GinesActors.publisher
    val simulation = GinesActors.makeSimulation
    simulation ! StartSimulation()

    Thread.sleep(1000*60)
  }
}
