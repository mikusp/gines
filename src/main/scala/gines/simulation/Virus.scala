package gines.simulation

import scala.util.Random

case class Virus(infectivity: Double, probCurveFactor: Int) {
    require(infectivity > 0 && infectivity < 1)

  def apply(prs: Person)(implicit rnd: Random): Person = prs.health match {
    case Healthy => {
      val virusEnc = prs.virusEncounters + 1
      val infectionProb = infectivity * math.exp(virusEnc / probCurveFactor)

      val h = if (math.random < infectionProb) Ill(1) else Healthy

      prs.copy(health = h, virusEncounters = virusEnc)
    }

    case _ => prs
  }
}
