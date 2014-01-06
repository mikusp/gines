package gines.akka

import akka.actor.{ActorLogging, ActorRef, Actor}
import gines.simulation._
import gines.simulation.SimulationState
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
class SimulationActor(var state: SimulationState, val virus: Virus, val publisher: ActorRef) extends Actor with ActorLogging {
  private var started = 0L
  private val sleepDuration = 3000

  def receive = {
    case StartSimulation() => {
      log.debug("Starting")
      self ! Infect
    } //TODO: start with initial parameters
    case PauseSimulation => ???
    case StopSimulation => ???

    //TODO: add more steps to have more control
    case Infect => {
      started = System.currentTimeMillis()
      self ! NextDay
    }

    case NextDay => {
      val world = state.world.map {
        case (key, value) => {
          val peopleOfCell = state.agents.filter(p => p.routine.head.cell == value)
          
          val notHealthyPeople = peopleOfCell.filter(p => p.health != Healthy)
          val condition = if(peopleOfCell.length != 0) (notHealthyPeople.length.toDouble/peopleOfCell.length) else 0.0
          
          (key, Cell(peopleOfCell.length,condition, value.typ))
        }
      }

      publisher ! Publish(state.day, state.chunk, world, Condition(0, 0, 100)) //TODO: fix days flow
      state = state.step(p => p)

      val calculationTook = System.currentTimeMillis() - started
      val sleepLeft = (sleepDuration - calculationTook).millis
      context.system.scheduler.scheduleOnce(sleepLeft, self, Infect)
    }
  }
}

case object Infect
case object NextDay

case class StartSimulation()
case object StopSimulation
case object PauseSimulation