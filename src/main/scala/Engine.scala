import scala.util.Random

class SimpleEngine(s: SimulationState, val virus: Virus, rnd: Random) extends Observable {
  protected var state: SimulationState = s
  protected implicit val random: Random = rnd

  def loop(duration: Int): Unit = for(i <- 1 to duration) {
    state = state.step(virus.apply)
    fireEvent(EngineEvent())
    Thread.sleep(1000)
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