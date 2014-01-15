package gines

import com.typesafe.config.ConfigFactory
import gines.simulation.{CellType, TimeChunk}

package object akka {
  val conf = ConfigFactory.load()

  val akkaLogLevel = conf.getString("akka.loglevel")

  val host = conf.getString("gines.simulation.host")

  val port = conf.getString("gines.simulation.port")

  val adminPort= conf.getString("gines.simulation.admin.port")

  case class Cell(count: Int, condition: Double, typ: CellType)

  case class Condition(infected: Double, immune: Double, healthy: Double)

  case class Publish(day: Double, time: TimeChunk, world: Map[(Int,Int), Cell], condition: Condition)
}