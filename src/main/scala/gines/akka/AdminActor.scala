package gines.akka

import gines.akka.GinesActors.system
import akka.actor.{ActorLogging, Actor}
import akka.zeromq._
import akka.zeromq.Bind
import akka.zeromq.Listener

class AdminActor extends Actor with ActorLogging {
  val adminSocket = ZeroMQExtension(system).newSocket(SocketType.Rep, Listener(self), Bind(s"tcp://*:$adminPort"))

  //TODO: add admin logic
  def receive: Actor.Receive = {
    case Connecting => log.debug("Connecting")
    case m: ZMQMessage => log.debug("Received message")
    case Closed => log.debug("Disconnected")
  }
}
