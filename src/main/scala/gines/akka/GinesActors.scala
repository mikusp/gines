package gines.akka

import akka.actor.{ActorRef, Props, ActorSystem}
import gines.simulation.{Virus, SimulationState}

object GinesActors {
  val system = ActorSystem("gines")

  implicit val publisher = system.actorOf(Props[PublisherActor], name="Publisher")

  def makeSimulation(name: String)(initialState: SimulationState, virus: Virus)(implicit pub: ActorRef) = {
    system.actorOf(Props(classOf[SimulationActor], initialState, virus, pub), name)
  }
}
