sealed abstract class Health {
  def advance: Health = this match {
    case Healthy => Healthy

    case Incubating(i) if i < 10 => Incubating(i+1)
    case Incubating(i) => Ill(1)

    case Ill(i) if i < 14 => Ill(i+1)
    case Ill(i) => Immune(1)

    case Immune(i) => Immune(i+1)

    case Vaccinated(i) => Vaccinated(i+1)
  }
}

case object Healthy extends Health
case class Incubating(i: Int) extends Health
case class Ill(i: Int) extends Health
case class Immune(i: Int) extends Health
case class Vaccinated(i: Int) extends Health

object Health {
  implicit def healthToInt(h: Health): Int = 1
}