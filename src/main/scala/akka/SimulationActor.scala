package akka

import akka.actor.Actor
import akka.zeromq.ZMQMessage

class SimulationActor extends Actor {
  def receive: Actor.Receive = {
    case m: ZMQMessage if m.frame(0).utf8String == GinesCommand("start").toString => ???
    case m: ZMQMessage if m.frame(0).utf8String == GinesCommand("stop").toString => ???
    case m: ZMQMessage if m.frame(0).utf8String == GinesCommand("pause").toString => ???
  }
}
