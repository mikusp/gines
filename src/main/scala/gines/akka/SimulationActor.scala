package gines.akka

import com.typesafe.config.ConfigFactory
import akka.actor.{ActorLogging, ActorRef, Actor}
import gines.simulation._
import gines.simulation.SimulationState
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class SimulationActor(var state: SimulationState, val virus: Virus, val publisher: ActorRef) extends Actor with ActorLogging {
  private var started = 0L
  private val sleepDuration = 1000

  lazy val conf = ConfigFactory.load

  def receive = {
    case StartSimulation() => {
      log.debug("Starting")
      val illAgentsNum = conf.getInt("simulation.params.agents.initialInfected")
      val newAgents = state.agents.take(illAgentsNum).map(_.copy(health = Ill(1)))
      state = state.copy(agents = newAgents ++ state.agents.drop(illAgentsNum))
      self ! Infect //TODO: start with initial parameters
    }
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
          val peopleOfCell = state.agents.view.filter(p => p.routine.head.cell == value)

          val notHealthyPeople = peopleOfCell.view.filter(p => p.health != Healthy)
          val condition = if(peopleOfCell.length != 0) (notHealthyPeople.length.toDouble/peopleOfCell.length) else 0.0

          (key, Cell(peopleOfCell.length,condition, value.typ))
        }
      }

      val ill = state.agents count (_.health match {
        case Ill(_) => true
        case _ => false
      })
      val immune = state.agents count (_.health == Immune)
      val healthy = state.agents count (_.health == Healthy)

      publisher ! Publish(state.day + (state.chunk match {
        case Morning => 0
        case Afternoon => 0.25
        case Evening => 0.5
        case Night => 0.75
      }), state.chunk, world, Condition(ill, immune, healthy))
      implicit val rnd = scala.util.Random
      state = state.step(virus.apply)

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