class Virus(infectivity: Double, mortality: Double, incubationTime: Int,
  meanIllnessTime: Int, infectousnessPeriodBeginning: Int,
  infectousnessPeriodEnd: Int) {
    require(infectivity > 0 && infectivity < 1)
    require(mortality > 0 && mortality < 1)
}

// what should be the unit of these Ints? days, chunks, ...?
