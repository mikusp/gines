package gines

import com.typesafe.config.ConfigFactory
import simulation.SimulationState

package object akka {
  val conf = ConfigFactory.load()

  val host = conf.getString("gines.simulation.host")

  val port = conf.getString("gines.simulation.port")

  val adminPort= conf.getString("gines.simulation.admin.port")

  case class Publish(sate: SimulationState)
}