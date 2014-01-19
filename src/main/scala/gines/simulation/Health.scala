package gines.simulation

import com.typesafe.config.ConfigFactory

sealed abstract class Health {
  def advance: Health = this match {
    case Ill(i) =>
      lazy val conf = ConfigFactory.load
      lazy val illnessLengthInNumOfChunks = conf.getInt("simulation.params." +
        "illness.length") * 4

      if (i < illnessLengthInNumOfChunks) Ill(i+1) else Immune

    case Exposed(i) =>
      lazy val conf = ConfigFactory.load
      lazy val exposedPhaseLength = conf.getInt("simulation.params" +
        "exposed.length") * 4

      if (i < exposedPhaseLength) Exposed(i+1) else Ill(1)

    case h => h
  }
}

case object Healthy extends Health

case class Ill(i: Int) extends Health

case class Exposed(i: Int) extends Health

case object Immune extends Health

object Health {
  implicit def healthToInt(h: Health): Int = 1 //TODO: Fixme!
}
