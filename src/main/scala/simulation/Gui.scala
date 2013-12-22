package simulation

import engine.{Listener, EngineEvent, SimpleEngine}
import grizzled.slf4j.Logger
import java.awt.Color
import scala.concurrent.Future
import scala.swing._
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

object Gui extends SimpleSwingApplication {
  val logger = Logger("Gui")
  val shapeSize = 10
  val colorsMap = Map[CellType, Color](
    Home -> Color.GRAY,
    School -> Color.ORANGE,
    Work -> Color.MAGENTA
  )
  val random = new Random
  val world = RandomWorldGenerator(100, 50)
  val people = List[Person]()
  val state = SimulationState(0,
    Morning,
    people,
    world
  )
  val virus = Virus(0.2, 0.4, 10, 15, 3, 7)
  val simulation = SimpleEngine(1000, state, virus, random)

  val gridSize = new Dimension(50, 50)

  lazy val ui = new FlowPanel() with Listener {
    simulation.register(this)

    preferredSize = new Dimension(500, 500)

    Future{ simulation.loop(1000) }

    override protected def paintComponent(g: swing.Graphics2D): Unit = {
      super.paintComponent(g)
      world.foreach {
        case((x,y), t) => {
          val peopleIn = state.agents.filter(_.routine.head.cell == t)
          val condition = peopleIn.foldLeft(0) { (acc, p) =>
            acc + p.health
          }
          logger.debug(s"simulation.Health condition of $condition")
          colorsMap.get(t.typ).map(c => g.setColor(c)) getOrElse (g.setColor(Color.BLACK))
          g.fillOval(x*shapeSize, y*shapeSize, shapeSize, shapeSize)
        }
      }
    }

    def onEngineEvent(evt: EngineEvent): Unit = {
      repaint()
    }
  }

  lazy val scrollPane = new ScrollPane(ui) {
    verticalScrollBarPolicy = ScrollPane.BarPolicy.AsNeeded
    horizontalScrollBarPolicy = ScrollPane.BarPolicy.AsNeeded
  }

  lazy val controlPane = new FlowPanel() {
    contents += new Label("Homes") {
      foreground = colorsMap(Home)
      border = Swing.LineBorder(Color.black)
    }
    contents += new Label("Schools") {
      foreground = colorsMap(School)
      border = Swing.LineBorder(Color.black)
    }
    contents += new Label("Working areas") {
      foreground = colorsMap(Work)
      border = Swing.LineBorder(Color.black)
    }
  }

  lazy val splitPane = new SplitPane(Orientation.Horizontal, scrollPane, controlPane) {
    resizeWeight = 0.85
    enabled = false
    dividerSize = 2
  }

  def top: Frame = new MainFrame {
    title = "GINES Gui"
    contents = splitPane
    size = new Dimension(400, 400)
  }
}
