import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

package object akka {
  val system = ActorSystem("gines")

  val conf = ConfigFactory.load()

  val host = conf.getString("simulation.host")

  val port = conf.getString("simulation.port")

  case class GinesCommand(cmd: String) {
    override def toString: String = "gines." + cmd
  }
}