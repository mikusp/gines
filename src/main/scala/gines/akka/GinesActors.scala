package gines.akka

import akka.actor.{ActorRef, Props, ActorSystem}

object GinesActors {
  val system = ActorSystem("gines")

  implicit val publisher = system.actorOf(Props[PublisherActor], name="Publisher")

  def makeSimulation(implicit pub: ActorRef) =
    system.actorOf(Props(classOf[SimulationActor], pub))
}
