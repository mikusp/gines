package engine

import scala.concurrent.Future
import scala.util.Random
import simulation.{Virus, SimulationState}
import scala.concurrent.ExecutionContext.Implicits.global

abstract class SimpleEngine(s: SimulationState, val virus: Virus, rnd: Random) extends Observable {
  protected val simThread: Future[Unit]
  protected var state: SimulationState = s
  protected implicit val random: Random = rnd

  def loop(duration: Int): Unit = for(i <- 1 to duration) {
    state = state.step(virus.apply)
    fireEvent(EngineEvent())
    Thread.sleep(1000)
  }
}

object SimpleEngine {
  def apply(days: Int, s: SimulationState, virus: Virus, rnd: Random): SimpleEngine = {
    new SimpleEngine(s, virus, rnd) {
      protected val simThread: Future[Unit] = Future{
        loop(days)
      }
    }
  }
}

trait Observable {
  protected var observers: List[Listener] = List.empty[Listener]
  def register(observer: Listener): Unit = observers = observer :: observers
  def fireEvent(evt: EngineEvent) = observers foreach (_.onEngineEvent(evt))
}

case class EngineEvent()

trait Listener {
  def onEngineEvent(evt: EngineEvent): Unit
}