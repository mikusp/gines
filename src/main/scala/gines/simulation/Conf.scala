package gines.simulation

abstract class Conf {
  def onStart(): Unit
  def onStop(): Unit
}

object Conf {
  val activities: Map[(Age, TimeChunk), List[CellType]] = Map(
    (Child, Morning) -> List(School),
    (Child, Afternoon) -> List(Home, FakeHome),
    (Child, Evening) -> List(Home, FakeHome),
    (Child, Night) -> List(Home, FakeHome),
    (Teenager, Morning) -> List(School),
    (Teenager, Afternoon) -> List(School),
    (Teenager, Evening) -> List(Home, FakeHome),
    (Teenager, Night) -> List(Home, FakeHome),
    (Adult, Morning) -> List(Work),
    (Adult, Afternoon) -> List(Work),
    (Adult, Evening) -> List(Home, FakeHome),
    (Adult, Night) -> List(Home, FakeHome),
    (Elderly, Morning) -> List(Home, FakeHome),
    (Elderly, Afternoon) -> List(Home, FakeHome),
    (Elderly, Evening) -> List(Home, FakeHome),
    (Elderly, Night) -> List(Home, FakeHome)
  )
}
