package gines.simulation

sealed abstract class Health {
  def advance: Health = this match {
    case Ill(i) if i < 40 => Ill(i+1)
    case Ill(i) => Immune

    case h => h
  }
}

case object Healthy extends Health

case class Ill(i: Int) extends Health

case object Immune extends Health

object Health {
  implicit def healthToInt(h: Health): Int = 1 //TODO: Fixme!
}
