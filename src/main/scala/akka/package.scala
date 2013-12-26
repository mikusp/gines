import akka.GinesActors.system
import akka.actor.{Props}
import com.typesafe.config.ConfigFactory

package object akka {
  val conf = ConfigFactory.load("application")

  val host = conf.getString("simulation.host")

  val port = conf.getString("simulation.port")

  val adminPort= conf.getString("simulation.admin.port")

  case class Publish()
}