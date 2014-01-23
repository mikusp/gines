package gines.akka

import com.typesafe.config.{ConfigException, Config, ConfigFactory}
import akka.actor.{ActorLogging, ActorRef, Actor}
import gines.simulation._
import gines.simulation.SimulationState
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


private object Implicits {
  import scala.util.Random
  implicit class RichPopulation(val pop: Vector[Person]) {

    lazy val (healthy, notHealthy) = Random.shuffle(pop).partition(_.health == Healthy)
    lazy val illAgentsNum = conf.getInt("simulation.params.agents.initialInfected")
    lazy val immuneAgentsNum = try conf.getInt("simulation.params.agents.initialImmune")
    catch {case e: ConfigException.Missing => 0}
    lazy val immuneAgentsAge = try conf.getString("simulation.params.agents.immuneAge")
    catch {case e: ConfigException.Missing => "All"}

    def immune: Vector[Person] = {
      def stringToAge(s: String): Age = s match {
        case "Child" => Child
        case "Teenager" => Teenager
        case "Adult" => Adult
        case "Elderly" => Elderly
      }

      val (toImmune, notInAge) = immuneAgentsAge match {
        case "All" => (healthy, Vector())
        case _ => healthy partition (_.age == stringToAge(immuneAgentsAge))
      }
      val (n, rest) = toImmune.splitAt(immuneAgentsNum)
      notHealthy ++ notInAge ++ (n map (_.copy(health = Immune))) ++ rest
    }

    def infect: Vector[Person] = {
      val (toInfect, rest) = healthy.splitAt(illAgentsNum)
      notHealthy ++ (toInfect map (_.copy(health = Ill(1)))) ++ rest
    }
  }
}

class SimulationActor(var state: SimulationState, val virus: Virus, val publisher: ActorRef) extends Actor with ActorLogging {
  private var started = 0L
  private var paused = false
  private val sleepDuration = 1000

  lazy val conf = ConfigFactory.load

  def receive = {
    case StartSimulation => {
      log.debug("Starting")
      import Implicits.RichPopulation
      state = state.copy(agents = state.agents.immune.infect)
      self ! Infect //TODO: start with initial parameters
    }

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
      val healthy = state.agents count (_.health match {
        case Healthy => true
        case Exposed(_) => true
        case _ => false
      })

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

case object StartSimulation
case object StopSimulation