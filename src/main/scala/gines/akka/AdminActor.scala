package gines.akka

import gines.akka.GinesActors.system
import akka.actor.{ActorSystem, Props, ActorLogging, Actor}
import akka.zeromq._
import akka.zeromq.Bind
import akka.zeromq.Listener

class AdminActor extends Actor with ActorLogging {
  val adminSocket = ZeroMQExtension(system).newSocket(SocketType.Rep, Listener(self), Bind(s"tcp://*:$adminPort"))

  def receive: Actor.Receive = {
    case Connecting => log.debug("Connecting")
    case m: ZMQMessage => {
      log.debug("Received message")
      log.debug(s"Message: ${m.frame(1).utf8String}")
    }
    case Closed => log.debug("Disconnected")
    case _ => log.warning("Other message")
  }
}

object AdminActor {
  def apply(port: Int) =
    Props(classOf[AdminActor], port)
}
