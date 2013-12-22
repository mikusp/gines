package akka

import akka.zeromq.{ZeroMQExtension, Bind, SocketType}
import akka.actor.Actor

class PublisherActor extends Actor {
  val socket = ZeroMQExtension(context.system).newSocket(
    SocketType.Pub, Bind(s"tcp://$host:$port"))

  def receive: Actor.Receive = ???
}
