package gines.akka

import akka.zeromq._
import akka.actor.{ActorLogging, Actor}
import akka.util.ByteString
import akka.zeromq.Bind
import com.codahale.jerkson.Json
import gines.simulation.Morning

class PublisherActor extends Actor with ActorLogging {
  val socket = ZeroMQExtension(context.system).newSocket(
    SocketType.Pub, Bind(s"tcp://*:$port"))

  def receive = {
    case Connecting => () //TODO: send world
    case p: Publish => {
      //TODO: send current state
      val jsonState = Json.generate(p)
      log.debug(jsonState)
      socket ! ZMQMessage(ByteString("gines"), ByteString(jsonState))
    }
  }
}
