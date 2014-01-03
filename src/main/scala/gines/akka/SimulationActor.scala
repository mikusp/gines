package gines.akka

import akka.actor.{ActorLogging, ActorRef, Actor}
import gines.simulation.{RandomWorldGenerator, Person, Morning, SimulationState}

class SimulationActor(val publisher: ActorRef) extends Actor with ActorLogging {
  def receive = {
    case StartSimulation() => {
      log.debug("Starting")
      self ! Infect
    } //TODO: start with initial parameters
    case PauseSimulation => ???
    case StopSimulation => ???

    //TODO: add more steps to have more control
    case Infect => {
      self ! NextDay
    }
    case NextDay => {
      val world = RandomWorldGenerator(10, 10)
      val tmpState = SimulationState(0, Morning, List.empty[Person], world)
      publisher ! Publish(tmpState) //TODO: publish state to all listeners
      self ! Infect
    }
  }
}

case object Infect
case object NextDay

case class StartSimulation()
case object StopSimulation
case object PauseSimulation