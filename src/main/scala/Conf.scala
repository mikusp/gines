object Conf {
  val activities: Map[(Age, TimeChunk), List[CellType]] = Map(
    (Child, Morning) -> List(School),
    (Child, Afternoon) -> List(Home),
    (Child, Evening) -> List(Home),
    (Child, Night) -> List(Home),
    (Teenager, Morning) -> List(School),
    (Teenager, Afternoon) -> List(School),
    (Teenager, Evening) -> List(Home),
    (Teenager, Night) -> List(Home),
    (Adult, Morning) -> List(Work),
    (Adult, Afternoon) -> List(Work),
    (Adult, Evening) -> List(Home),
    (Adult, Night) -> List(Home),
    (Elderly, Morning) -> List(Home),
    (Elderly, Afternoon) -> List(Home),
    (Elderly, Evening) -> List(Home),
    (Elderly, Night) -> List(Home)
  )
}
