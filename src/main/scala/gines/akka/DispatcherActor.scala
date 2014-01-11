package gines.akka

import akka.actor.{Props, Actor}

class DispatcherActor extends Actor {
  def receive: Actor.Receive = ???
}

object DispatcherActor {
  def apply() = Props(classOf[DispatcherActor])
}
