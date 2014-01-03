package akka

import akka.zeromq._
import akka.actor.{ActorLogging, Actor}
import akka.util.ByteString
import akka.zeromq.Bind
import com.codahale.jerkson.Json

class PublisherActor extends Actor with ActorLogging {
  val socket = ZeroMQExtension(context.system).newSocket(
    SocketType.Pub, Bind(s"tcp://$host:$port"))

  def receive: Actor.Receive = {
    case Connecting => () //TODO: send world
    case Publish(state) => {
      //TODO: send current state
      val jsonState = Json.generate(state)
      socket ! ZMQMessage(ByteString("gines"), ByteString(jsonState))
    }
  }
}
