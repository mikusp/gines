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
import com.codahale.jerkson.Json
import com.fasterxml.jackson.annotation.{JsonInclude, JsonTypeInfo, JsonSubTypes}
import com.fasterxml.jackson.annotation.JsonInclude.Include
import gines.App

class AdminActor(confFile: Option[String]) extends Actor with ActorLogging {
  val adminSocket = ZeroMQExtension(system).newSocket(SocketType.Rep, Listener(self), Bind(s"tcp://*:$adminPort"))

  def receive: Actor.Receive = {
    case Connecting => log.debug("Connecting")
    case m: ZMQMessage => {
      sender ! ZMQMessage(ByteString("gines"), ByteString("{\"status:\": \"OK\"}"))
      log.debug(s"Received message: ${m.frame(1).utf8String}")
      val obj = Json.parse[Command](m.frame(1).utf8String.toString)
      processMessage(obj)
    }
    case Closed => log.debug("Disconnected")
    case _ => log.warning("Other message")
  }

  def processMessage(cmd: Command): Unit = cmd match {
    case c: StartCommand => simulationControl(
      onSuccess = { actor =>
        log.debug("Simulation is already running")
      },
      onFailure = {
        log.info("Creating simulation")
        App.createSimulation(confFile)
      }
    )
    case c: StopCommand => {
      simulationControl(onSuccess = { actor =>
        log.info("Stoping simulation")
        actor ! PoisonPill
      }, onFailure = {
        log.warning("Cannot stop not running simulation")
      })
    }
    case _ => log.warning("Mismatch message")
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

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "command"
)
@JsonSubTypes(
  Array(
    new JsonSubTypes.Type(value=classOf[StartCommand], name="start"),
    new JsonSubTypes.Type(value=classOf[StopCommand], name="stop")
  )
)
abstract class Command
case class StartCommand(params: Option[String]) extends Command
case class StopCommand(foo: Option[String]) extends Command

object AdminActor {
  def apply(port: Int) =
    Props(classOf[AdminActor], port)
}
