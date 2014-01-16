package gines.akka

import gines.akka.GinesActors.system
import akka.actor._
import akka.zeromq._
import akka.util.{Timeout, ByteString}
import akka.zeromq.Listener
import scala.util.Success
import scala.util.Failure
import scala.Some
import akka.zeromq.Bind

class AdminActor extends Actor with ActorLogging {
  val adminSocket = ZeroMQExtension(system).newSocket(SocketType.Rep, Listener(self), Bind(s"tcp://*:$adminPort"))

  def receive: Actor.Receive = {
    case Connecting => log.debug("Connecting")
    case m: ZMQMessage => {
      sender ! ZMQMessage(ByteString("gines"), ByteString("{\"status:\": \"OK\"}"))
      log.debug("Received message")
      log.debug(s"Message: ${m.frame(1).utf8String}")
      processMessage(m.frame(1).utf8String, if(m.frames.length < 3) None else Some(m.frame(2).utf8String))
    }
    case Closed => log.debug("Disconnected")
    case _ => log.warning("Other message")
  }

  def processMessage(cmd: String, payload: Option[String]): Unit = cmd match {
    case "start" => payload.map { p =>
      simulationControl(
        onSuccess = { actor =>
          log.warning("Simulation is already running")
        },
        onFailure = {
          log.info("Creating simulation")
        }
      )
    } getOrElse {
      log.debug("Error! There is lack of parameters")
    }
    case "pause" => log.warning("Pause command is not supported")
    case "resume" => log.warning("Resume command is not supported")
    case "stop" => {
      simulationControl(onSuccess = { actor =>
        log.info("Stoping simulation")
        actor ! PoisonPill
      }, onFailure = {
        log.warning("Cannot stop not running simulation")
      })
    }
    case "restart" => {
      log.info("Restarting simulation")
      processMessage("stop", None)
      processMessage("start", payload)
    }
  }

  private def simulationControl(onSuccess: (ActorRef) => Unit = {actor => ()}, onFailure: => Unit = {}): Unit = {
    implicit val timeout: Timeout = 5000
    import context.dispatcher
    context.system.actorSelection("/user/simulation").resolveOne.onComplete {
      case Success(actor) => onSuccess(actor)
      case Failure(ex) => onFailure
    }
  }
}

object AdminActor {
  def apply(port: Int) =
    Props(classOf[AdminActor], port)
}
