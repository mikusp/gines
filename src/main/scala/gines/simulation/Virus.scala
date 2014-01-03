package gines.simulation

import scala.util.Random

case class Virus(infectivity: Double, mortality: Double, incubationTime: Int,
  meanIllnessTime: Int, infectiousnessPeriodBeginning: Int,
  infectiousnessPeriodEnd: Int) {
    require(infectivity > 0 && infectivity < 1)
    require(mortality > 0 && mortality < 1)

  def apply(prs: Person)(implicit rnd: Random): Person = {
    prs
  }
}

// what should be the unit of these ints? days, chunks, ...?
