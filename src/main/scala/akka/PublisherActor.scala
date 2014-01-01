package akka

import akka.zeromq.{ZMQMessage, ZeroMQExtension, Bind, SocketType}
import akka.actor.{ActorLogging, Actor}
import akka.util.ByteString

class PublisherActor extends Actor with ActorLogging {
  val socket = ZeroMQExtension(context.system).newSocket(
    SocketType.Pub, Bind(s"tcp://$host:$port"))

  def receive: Actor.Receive = {
    case Publish() => {
      socket ! ZMQMessage(ByteString("gines"), ByteString("hello world!"))
    }
  }
}
