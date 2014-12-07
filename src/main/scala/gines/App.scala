package gines

import _root_.akka.actor.Props
import com.typesafe.config.ConfigFactory
import gines.simulation.Cell
import gines.simulation.{Home, School, Work, FakeHome}
import gines.akka.{AdminActor, StartSimulation, GinesActors}
import gines.simulation.{Virus, Morning, SimulationState, Foo}
import gines.utils.GinesLogging
import java.io.File

object App extends GinesLogging {
  def main(args: Array[String]): Unit = {

    val conf = if(args.length > 0) {
      Some(args(0))
    } else {
      None
    }

    GinesActors.system.actorOf(Props(classOf[AdminActor], conf), name="admin")

    GinesActors.system.awaitTermination()
  }

  def createSimulation(config: Option[String]): Unit = {
    import GinesActors.publisher

    val conf = config map { file =>
      ConfigFactory.parseFile(new File(file))
    } getOrElse {
      ConfigFactory.load
    }

		val world = Map((1,1) -> new Cell(FakeHome),
			(2,1) -> new Cell(FakeHome),
			(3,1) -> new Cell(FakeHome),
			(4,1) -> new Cell(FakeHome),
			(5,1) -> new Cell(FakeHome),
			(6,1) -> new Cell(FakeHome),
			(7,1) -> new Cell(FakeHome),
			(8,1) -> new Cell(FakeHome),
			(9,1) -> new Cell(FakeHome),
			(10,1) -> new Cell(FakeHome),
			(11,1) -> new Cell(FakeHome),
			(12,1) -> new Cell(FakeHome),
			(13,1) -> new Cell(FakeHome),
			(14,1) -> new Cell(FakeHome),
			(15,1) -> new Cell(FakeHome),
			(16,1) -> new Cell(FakeHome),
			(17,1) -> new Cell(FakeHome),
			(18,1) -> new Cell(FakeHome),
			(19,1) -> new Cell(FakeHome),
			(20,1) -> new Cell(FakeHome),
			(21,1) -> new Cell(FakeHome),
			(22,1) -> new Cell(FakeHome),
			(23,1) -> new Cell(Home),
			(24,1) -> new Cell(Home),
			(25,1) -> new Cell(Home),
			(26,1) -> new Cell(FakeHome),
			(27,1) -> new Cell(FakeHome),
			(28,1) -> new Cell(FakeHome),
			(29,1) -> new Cell(FakeHome),
			(30,1) -> new Cell(FakeHome),
			(31,1) -> new Cell(FakeHome),
			(32,1) -> new Cell(FakeHome),
			(1,2) -> new Cell(FakeHome),
			(2,2) -> new Cell(FakeHome),
			(3,2) -> new Cell(FakeHome),
			(4,2) -> new Cell(FakeHome),
			(5,2) -> new Cell(FakeHome),
			(6,2) -> new Cell(FakeHome),
			(7,2) -> new Cell(FakeHome),
			(8,2) -> new Cell(FakeHome),
			(9,2) -> new Cell(FakeHome),
			(10,2) -> new Cell(FakeHome),
			(11,2) -> new Cell(FakeHome),
			(12,2) -> new Cell(FakeHome),
			(13,2) -> new Cell(FakeHome),
			(14,2) -> new Cell(FakeHome),
			(15,2) -> new Cell(FakeHome),
			(16,2) -> new Cell(FakeHome),
			(17,2) -> new Cell(FakeHome),
			(18,2) -> new Cell(FakeHome),
			(19,2) -> new Cell(FakeHome),
			(20,2) -> new Cell(FakeHome),
			(21,2) -> new Cell(FakeHome),
			(22,2) -> new Cell(FakeHome),
			(23,2) -> new Cell(Home),
			(24,2) -> new Cell(Home),
			(25,2) -> new Cell(Home),
			(26,2) -> new Cell(FakeHome),
			(27,2) -> new Cell(FakeHome),
			(28,2) -> new Cell(FakeHome),
			(29,2) -> new Cell(FakeHome),
			(30,2) -> new Cell(FakeHome),
			(31,2) -> new Cell(FakeHome),
			(32,2) -> new Cell(FakeHome),
			(1,3) -> new Cell(FakeHome),
			(2,3) -> new Cell(FakeHome),
			(3,3) -> new Cell(FakeHome),
			(4,3) -> new Cell(FakeHome),
			(5,3) -> new Cell(FakeHome),
			(6,3) -> new Cell(FakeHome),
			(7,3) -> new Cell(FakeHome),
			(8,3) -> new Cell(FakeHome),
			(9,3) -> new Cell(FakeHome),
			(10,3) -> new Cell(FakeHome),
			(11,3) -> new Cell(FakeHome),
			(12,3) -> new Cell(FakeHome),
			(13,3) -> new Cell(FakeHome),
			(14,3) -> new Cell(Home),
			(15,3) -> new Cell(Home),
			(16,3) -> new Cell(Home),
			(17,3) -> new Cell(Home),
			(18,3) -> new Cell(FakeHome),
			(19,3) -> new Cell(FakeHome),
			(20,3) -> new Cell(FakeHome),
			(21,3) -> new Cell(FakeHome),
			(22,3) -> new Cell(FakeHome),
			(23,3) -> new Cell(Home),
			(24,3) -> new Cell(Home),
			(25,3) -> new Cell(Work),
			(26,3) -> new Cell(FakeHome),
			(27,3) -> new Cell(FakeHome),
			(28,3) -> new Cell(FakeHome),
			(29,3) -> new Cell(FakeHome),
			(30,3) -> new Cell(FakeHome),
			(31,3) -> new Cell(FakeHome),
			(32,3) -> new Cell(FakeHome),
			(1,4) -> new Cell(FakeHome),
			(2,4) -> new Cell(FakeHome),
			(3,4) -> new Cell(FakeHome),
			(4,4) -> new Cell(FakeHome),
			(5,4) -> new Cell(FakeHome),
			(6,4) -> new Cell(Home),
			(7,4) -> new Cell(Home),
			(8,4) -> new Cell(Home),
			(9,4) -> new Cell(Home),
			(10,4) -> new Cell(Home),
			(11,4) -> new Cell(Home),
			(12,4) -> new Cell(Home),
			(13,4) -> new Cell(Home),
			(14,4) -> new Cell(Home),
			(15,4) -> new Cell(Home),
			(16,4) -> new Cell(Home),
			(17,4) -> new Cell(Home),
			(18,4) -> new Cell(Home),
			(19,4) -> new Cell(FakeHome),
			(20,4) -> new Cell(FakeHome),
			(21,4) -> new Cell(FakeHome),
			(22,4) -> new Cell(FakeHome),
			(23,4) -> new Cell(FakeHome),
			(24,4) -> new Cell(Home),
			(25,4) -> new Cell(Work),
			(26,4) -> new Cell(FakeHome),
			(27,4) -> new Cell(FakeHome),
			(28,4) -> new Cell(FakeHome),
			(29,4) -> new Cell(FakeHome),
			(30,4) -> new Cell(FakeHome),
			(31,4) -> new Cell(FakeHome),
			(32,4) -> new Cell(FakeHome),
			(1,5) -> new Cell(FakeHome),
			(2,5) -> new Cell(FakeHome),
			(3,5) -> new Cell(FakeHome),
			(4,5) -> new Cell(FakeHome),
			(5,5) -> new Cell(FakeHome),
			(6,5) -> new Cell(Home),
			(7,5) -> new Cell(Home),
			(8,5) -> new Cell(Home),
			(9,5) -> new Cell(Home),
			(10,5) -> new Cell(Home),
			(11,5) -> new Cell(Home),
			(12,5) -> new Cell(Home),
			(13,5) -> new Cell(Home),
			(14,5) -> new Cell(Home),
			(15,5) -> new Cell(Home),
			(16,5) -> new Cell(Home),
			(17,5) -> new Cell(Work),
			(18,5) -> new Cell(Home),
			(19,5) -> new Cell(Home),
			(20,5) -> new Cell(Home),
			(21,5) -> new Cell(Work),
			(22,5) -> new Cell(Work),
			(23,5) -> new Cell(Home),
			(24,5) -> new Cell(Home),
			(25,5) -> new Cell(Home),
			(26,5) -> new Cell(Work),
			(27,5) -> new Cell(Work),
			(28,5) -> new Cell(Home),
			(29,5) -> new Cell(FakeHome),
			(30,5) -> new Cell(FakeHome),
			(31,5) -> new Cell(FakeHome),
			(32,5) -> new Cell(FakeHome),
			(1,6) -> new Cell(FakeHome),
			(2,6) -> new Cell(FakeHome),
			(3,6) -> new Cell(FakeHome),
			(4,6) -> new Cell(Home),
			(5,6) -> new Cell(Home),
			(6,6) -> new Cell(Home),
			(7,6) -> new Cell(Home),
			(8,6) -> new Cell(Home),
			(9,6) -> new Cell(Home),
			(10,6) -> new Cell(Home),
			(11,6) -> new Cell(Home),
			(12,6) -> new Cell(Home),
			(13,6) -> new Cell(Home),
			(14,6) -> new Cell(Home),
			(15,6) -> new Cell(Home),
			(16,6) -> new Cell(Home),
			(17,6) -> new Cell(Work),
			(18,6) -> new Cell(Home),
			(19,6) -> new Cell(Home),
			(20,6) -> new Cell(Work),
			(21,6) -> new Cell(Home),
			(22,6) -> new Cell(Home),
			(23,6) -> new Cell(Home),
			(24,6) -> new Cell(Work),
			(25,6) -> new Cell(Home),
			(26,6) -> new Cell(Work),
			(27,6) -> new Cell(Home),
			(28,6) -> new Cell(Home),
			(29,6) -> new Cell(FakeHome),
			(30,6) -> new Cell(FakeHome),
			(31,6) -> new Cell(FakeHome),
			(32,6) -> new Cell(FakeHome),
			(1,7) -> new Cell(FakeHome),
			(2,7) -> new Cell(FakeHome),
			(3,7) -> new Cell(FakeHome),
			(4,7) -> new Cell(Home),
			(5,7) -> new Cell(Home),
			(6,7) -> new Cell(Home),
			(7,7) -> new Cell(Home),
			(8,7) -> new Cell(Home),
			(9,7) -> new Cell(Home),
			(10,7) -> new Cell(Home),
			(11,7) -> new Cell(Home),
			(12,7) -> new Cell(Home),
			(13,7) -> new Cell(Home),
			(14,7) -> new Cell(Home),
			(15,7) -> new Cell(Home),
			(16,7) -> new Cell(Work),
			(17,7) -> new Cell(Home),
			(18,7) -> new Cell(Home),
			(19,7) -> new Cell(Home),
			(20,7) -> new Cell(Work),
			(21,7) -> new Cell(Home),
			(22,7) -> new Cell(Work),
			(23,7) -> new Cell(Home),
			(24,7) -> new Cell(Work),
			(25,7) -> new Cell(Home),
			(26,7) -> new Cell(Home),
			(27,7) -> new Cell(Work),
			(28,7) -> new Cell(Home),
			(29,7) -> new Cell(FakeHome),
			(30,7) -> new Cell(FakeHome),
			(31,7) -> new Cell(FakeHome),
			(32,7) -> new Cell(FakeHome),
			(1,8) -> new Cell(FakeHome),
			(2,8) -> new Cell(FakeHome),
			(3,8) -> new Cell(FakeHome),
			(4,8) -> new Cell(Home),
			(5,8) -> new Cell(Home),
			(6,8) -> new Cell(Home),
			(7,8) -> new Cell(Home),
			(8,8) -> new Cell(Home),
			(9,8) -> new Cell(Home),
			(10,8) -> new Cell(Home),
			(11,8) -> new Cell(Home),
			(12,8) -> new Cell(Home),
			(13,8) -> new Cell(Home),
			(14,8) -> new Cell(Home),
			(15,8) -> new Cell(Home),
			(16,8) -> new Cell(Home),
			(17,8) -> new Cell(Work),
			(18,8) -> new Cell(Home),
			(19,8) -> new Cell(Home),
			(20,8) -> new Cell(Home),
			(21,8) -> new Cell(Home),
			(22,8) -> new Cell(Home),
			(23,8) -> new Cell(Home),
			(24,8) -> new Cell(Home),
			(25,8) -> new Cell(Home),
			(26,8) -> new Cell(Home),
			(27,8) -> new Cell(Home),
			(28,8) -> new Cell(Home),
			(29,8) -> new Cell(Home),
			(30,8) -> new Cell(FakeHome),
			(31,8) -> new Cell(FakeHome),
			(32,8) -> new Cell(FakeHome),
			(1,9) -> new Cell(FakeHome),
			(2,9) -> new Cell(FakeHome),
			(3,9) -> new Cell(FakeHome),
			(4,9) -> new Cell(Home),
			(5,9) -> new Cell(Home),
			(6,9) -> new Cell(Home),
			(7,9) -> new Cell(Home),
			(8,9) -> new Cell(Home),
			(9,9) -> new Cell(Home),
			(10,9) -> new Cell(Home),
			(11,9) -> new Cell(Home),
			(12,9) -> new Cell(Home),
			(13,9) -> new Cell(Home),
			(14,9) -> new Cell(Home),
			(15,9) -> new Cell(Work),
			(16,9) -> new Cell(Home),
			(17,9) -> new Cell(Home),
			(18,9) -> new Cell(Home),
			(19,9) -> new Cell(Home),
			(20,9) -> new Cell(Home),
			(21,9) -> new Cell(Home),
			(22,9) -> new Cell(Work),
			(23,9) -> new Cell(Home),
			(24,9) -> new Cell(Home),
			(25,9) -> new Cell(Home),
			(26,9) -> new Cell(Work),
			(27,9) -> new Cell(Home),
			(28,9) -> new Cell(Work),
			(29,9) -> new Cell(Home),
			(30,9) -> new Cell(Work),
			(31,9) -> new Cell(FakeHome),
			(32,9) -> new Cell(FakeHome),
			(1,10) -> new Cell(FakeHome),
			(2,10) -> new Cell(FakeHome),
			(3,10) -> new Cell(FakeHome),
			(4,10) -> new Cell(Home),
			(5,10) -> new Cell(Home),
			(6,10) -> new Cell(Home),
			(7,10) -> new Cell(Home),
			(8,10) -> new Cell(Home),
			(9,10) -> new Cell(Home),
			(10,10) -> new Cell(Home),
			(11,10) -> new Cell(Home),
			(12,10) -> new Cell(Home),
			(13,10) -> new Cell(Home),
			(14,10) -> new Cell(Home),
			(15,10) -> new Cell(Work),
			(16,10) -> new Cell(Home),
			(17,10) -> new Cell(Work),
			(18,10) -> new Cell(Home),
			(19,10) -> new Cell(Home),
			(20,10) -> new Cell(Home),
			(21,10) -> new Cell(Work),
			(22,10) -> new Cell(Home),
			(23,10) -> new Cell(Work),
			(24,10) -> new Cell(Home),
			(25,10) -> new Cell(Home),
			(26,10) -> new Cell(Work),
			(27,10) -> new Cell(Home),
			(28,10) -> new Cell(Home),
			(29,10) -> new Cell(Work),
			(30,10) -> new Cell(Home),
			(31,10) -> new Cell(FakeHome),
			(32,10) -> new Cell(FakeHome),
			(1,11) -> new Cell(FakeHome),
			(2,11) -> new Cell(FakeHome),
			(3,11) -> new Cell(FakeHome),
			(4,11) -> new Cell(FakeHome),
			(5,11) -> new Cell(FakeHome),
			(6,11) -> new Cell(Home),
			(7,11) -> new Cell(Home),
			(8,11) -> new Cell(Home),
			(9,11) -> new Cell(Home),
			(10,11) -> new Cell(Home),
			(11,11) -> new Cell(Home),
			(12,11) -> new Cell(Work),
			(13,11) -> new Cell(Home),
			(14,11) -> new Cell(Home),
			(15,11) -> new Cell(Work),
			(16,11) -> new Cell(Home),
			(17,11) -> new Cell(Work),
			(18,11) -> new Cell(Home),
			(19,11) -> new Cell(Home),
			(20,11) -> new Cell(Home),
			(21,11) -> new Cell(Work),
			(22,11) -> new Cell(Home),
			(23,11) -> new Cell(Home),
			(24,11) -> new Cell(Work),
			(25,11) -> new Cell(Home),
			(26,11) -> new Cell(Work),
			(27,11) -> new Cell(Home),
			(28,11) -> new Cell(Work),
			(29,11) -> new Cell(Home),
			(30,11) -> new Cell(FakeHome),
			(31,11) -> new Cell(FakeHome),
			(32,11) -> new Cell(FakeHome),
			(1,12) -> new Cell(FakeHome),
			(2,12) -> new Cell(FakeHome),
			(3,12) -> new Cell(FakeHome),
			(4,12) -> new Cell(Home),
			(5,12) -> new Cell(Work),
			(6,12) -> new Cell(Home),
			(7,12) -> new Cell(Home),
			(8,12) -> new Cell(Work),
			(9,12) -> new Cell(Home),
			(10,12) -> new Cell(Home),
			(11,12) -> new Cell(Home),
			(12,12) -> new Cell(Work),
			(13,12) -> new Cell(Home),
			(14,12) -> new Cell(Home),
			(15,12) -> new Cell(Work),
			(16,12) -> new Cell(Home),
			(17,12) -> new Cell(Home),
			(18,12) -> new Cell(Home),
			(19,12) -> new Cell(Work),
			(20,12) -> new Cell(Home),
			(21,12) -> new Cell(Home),
			(22,12) -> new Cell(Work),
			(23,12) -> new Cell(Home),
			(24,12) -> new Cell(Home),
			(25,12) -> new Cell(Home),
			(26,12) -> new Cell(Work),
			(27,12) -> new Cell(Home),
			(28,12) -> new Cell(Work),
			(29,12) -> new Cell(Home),
			(30,12) -> new Cell(FakeHome),
			(31,12) -> new Cell(FakeHome),
			(32,12) -> new Cell(FakeHome),
			(1,13) -> new Cell(FakeHome),
			(2,13) -> new Cell(FakeHome),
			(3,13) -> new Cell(FakeHome),
			(4,13) -> new Cell(Home),
			(5,13) -> new Cell(Work),
			(6,13) -> new Cell(Home),
			(7,13) -> new Cell(Home),
			(8,13) -> new Cell(Home),
			(9,13) -> new Cell(Work),
			(10,13) -> new Cell(Home),
			(11,13) -> new Cell(Home),
			(12,13) -> new Cell(Work),
			(13,13) -> new Cell(Home),
			(14,13) -> new Cell(Work),
			(15,13) -> new Cell(Home),
			(16,13) -> new Cell(Home),
			(17,13) -> new Cell(Home),
			(18,13) -> new Cell(Home),
			(19,13) -> new Cell(Work),
			(20,13) -> new Cell(Home),
			(21,13) -> new Cell(Home),
			(22,13) -> new Cell(School),
			(23,13) -> new Cell(School),
			(24,13) -> new Cell(School),
			(25,13) -> new Cell(School),
			(26,13) -> new Cell(Work),
			(27,13) -> new Cell(Home),
			(28,13) -> new Cell(Home),
			(29,13) -> new Cell(Home),
			(30,13) -> new Cell(Home),
			(31,13) -> new Cell(FakeHome),
			(32,13) -> new Cell(FakeHome),
			(1,14) -> new Cell(FakeHome),
			(2,14) -> new Cell(FakeHome),
			(3,14) -> new Cell(FakeHome),
			(4,14) -> new Cell(Home),
			(5,14) -> new Cell(Home),
			(6,14) -> new Cell(Home),
			(7,14) -> new Cell(Work),
			(8,14) -> new Cell(Home),
			(9,14) -> new Cell(Work),
			(10,14) -> new Cell(Home),
			(11,14) -> new Cell(Home),
			(12,14) -> new Cell(Home),
			(13,14) -> new Cell(Home),
			(14,14) -> new Cell(Work),
			(15,14) -> new Cell(Home),
			(16,14) -> new Cell(Work),
			(17,14) -> new Cell(Home),
			(18,14) -> new Cell(Work),
			(19,14) -> new Cell(Home),
			(20,14) -> new Cell(Work),
			(21,14) -> new Cell(School),
			(22,14) -> new Cell(School),
			(23,14) -> new Cell(School),
			(24,14) -> new Cell(School),
			(25,14) -> new Cell(School),
			(26,14) -> new Cell(Home),
			(27,14) -> new Cell(Home),
			(28,14) -> new Cell(Work),
			(29,14) -> new Cell(Home),
			(30,14) -> new Cell(Home),
			(31,14) -> new Cell(FakeHome),
			(32,14) -> new Cell(FakeHome),
			(1,15) -> new Cell(FakeHome),
			(2,15) -> new Cell(FakeHome),
			(3,15) -> new Cell(FakeHome),
			(4,15) -> new Cell(Home),
			(5,15) -> new Cell(Home),
			(6,15) -> new Cell(Work),
			(7,15) -> new Cell(Home),
			(8,15) -> new Cell(Home),
			(9,15) -> new Cell(Work),
			(10,15) -> new Cell(Work),
			(11,15) -> new Cell(Home),
			(12,15) -> new Cell(Home),
			(13,15) -> new Cell(Work),
			(14,15) -> new Cell(Home),
			(15,15) -> new Cell(Work),
			(16,15) -> new Cell(Home),
			(17,15) -> new Cell(Work),
			(18,15) -> new Cell(Home),
			(19,15) -> new Cell(Home),
			(20,15) -> new Cell(Work),
			(21,15) -> new Cell(School),
			(22,15) -> new Cell(School),
			(23,15) -> new Cell(School),
			(24,15) -> new Cell(School),
			(25,15) -> new Cell(FakeHome),
			(26,15) -> new Cell(FakeHome),
			(27,15) -> new Cell(Work),
			(28,15) -> new Cell(Work),
			(29,15) -> new Cell(Work),
			(30,15) -> new Cell(Home),
			(31,15) -> new Cell(FakeHome),
			(32,15) -> new Cell(FakeHome),
			(1,16) -> new Cell(FakeHome),
			(2,16) -> new Cell(FakeHome),
			(3,16) -> new Cell(FakeHome),
			(4,16) -> new Cell(FakeHome),
			(5,16) -> new Cell(Home),
			(6,16) -> new Cell(Home),
			(7,16) -> new Cell(Home),
			(8,16) -> new Cell(Home),
			(9,16) -> new Cell(Work),
			(10,16) -> new Cell(Home),
			(11,16) -> new Cell(Home),
			(12,16) -> new Cell(Home),
			(13,16) -> new Cell(Work),
			(14,16) -> new Cell(Home),
			(15,16) -> new Cell(Home),
			(16,16) -> new Cell(Home),
			(17,16) -> new Cell(Work),
			(18,16) -> new Cell(Home),
			(19,16) -> new Cell(Work),
			(20,16) -> new Cell(Home),
			(21,16) -> new Cell(Work),
			(22,16) -> new Cell(Work),
			(23,16) -> new Cell(Work),
			(24,16) -> new Cell(Work),
			(25,16) -> new Cell(Work),
			(26,16) -> new Cell(Work),
			(27,16) -> new Cell(Work),
			(28,16) -> new Cell(Work),
			(29,16) -> new Cell(Work),
			(30,16) -> new Cell(Work),
			(31,16) -> new Cell(FakeHome),
			(32,16) -> new Cell(FakeHome),
			(1,17) -> new Cell(FakeHome),
			(2,17) -> new Cell(FakeHome),
			(3,17) -> new Cell(FakeHome),
			(4,17) -> new Cell(FakeHome),
			(5,17) -> new Cell(Home),
			(6,17) -> new Cell(Home),
			(7,17) -> new Cell(Home),
			(8,17) -> new Cell(Home),
			(9,17) -> new Cell(Work),
			(10,17) -> new Cell(Home),
			(11,17) -> new Cell(Home),
			(12,17) -> new Cell(Work),
			(13,17) -> new Cell(Home),
			(14,17) -> new Cell(Work),
			(15,17) -> new Cell(Home),
			(16,17) -> new Cell(Home),
			(17,17) -> new Cell(Home),
			(18,17) -> new Cell(Work),
			(19,17) -> new Cell(Home),
			(20,17) -> new Cell(Work),
			(21,17) -> new Cell(Work),
			(22,17) -> new Cell(Work),
			(23,17) -> new Cell(Work),
			(24,17) -> new Cell(Work),
			(25,17) -> new Cell(Work),
			(26,17) -> new Cell(Work),
			(27,17) -> new Cell(Work),
			(28,17) -> new Cell(Work),
			(29,17) -> new Cell(Work),
			(30,17) -> new Cell(Work),
			(31,17) -> new Cell(FakeHome),
			(32,17) -> new Cell(FakeHome),
			(1,18) -> new Cell(FakeHome),
			(2,18) -> new Cell(FakeHome),
			(3,18) -> new Cell(FakeHome),
			(4,18) -> new Cell(FakeHome),
			(5,18) -> new Cell(FakeHome),
			(6,18) -> new Cell(Home),
			(7,18) -> new Cell(Home),
			(8,18) -> new Cell(Work),
			(9,18) -> new Cell(Home),
			(10,18) -> new Cell(Home),
			(11,18) -> new Cell(Home),
			(12,18) -> new Cell(Home),
			(13,18) -> new Cell(Work),
			(14,18) -> new Cell(Home),
			(15,18) -> new Cell(Work),
			(16,18) -> new Cell(Home),
			(17,18) -> new Cell(Home),
			(18,18) -> new Cell(Home),
			(19,18) -> new Cell(Home),
			(20,18) -> new Cell(Work),
			(21,18) -> new Cell(Work),
			(22,18) -> new Cell(Work),
			(23,18) -> new Cell(Work),
			(24,18) -> new Cell(Work),
			(25,18) -> new Cell(Work),
			(26,18) -> new Cell(Work),
			(27,18) -> new Cell(Work),
			(28,18) -> new Cell(Work),
			(29,18) -> new Cell(School),
			(30,18) -> new Cell(School),
			(31,18) -> new Cell(School),
			(32,18) -> new Cell(School),
			(1,19) -> new Cell(FakeHome),
			(2,19) -> new Cell(FakeHome),
			(3,19) -> new Cell(FakeHome),
			(4,19) -> new Cell(FakeHome),
			(5,19) -> new Cell(FakeHome),
			(6,19) -> new Cell(Home),
			(7,19) -> new Cell(Home),
			(8,19) -> new Cell(Work),
			(9,19) -> new Cell(Home),
			(10,19) -> new Cell(Home),
			(11,19) -> new Cell(Work),
			(12,19) -> new Cell(Home),
			(13,19) -> new Cell(Home),
			(14,19) -> new Cell(Home),
			(15,19) -> new Cell(Work),
			(16,19) -> new Cell(Home),
			(17,19) -> new Cell(Home),
			(18,19) -> new Cell(Home),
			(19,19) -> new Cell(Work),
			(20,19) -> new Cell(Work),
			(21,19) -> new Cell(Work),
			(22,19) -> new Cell(Work),
			(23,19) -> new Cell(Work),
			(24,19) -> new Cell(Work),
			(25,19) -> new Cell(Work),
			(26,19) -> new Cell(Work),
			(27,19) -> new Cell(Work),
			(28,19) -> new Cell(Work),
			(29,19) -> new Cell(School),
			(30,19) -> new Cell(School),
			(31,19) -> new Cell(School),
			(32,19) -> new Cell(School),
			(1,20) -> new Cell(FakeHome),
			(2,20) -> new Cell(FakeHome),
			(3,20) -> new Cell(FakeHome),
			(4,20) -> new Cell(FakeHome),
			(5,20) -> new Cell(FakeHome),
			(6,20) -> new Cell(Home),
			(7,20) -> new Cell(Work),
			(8,20) -> new Cell(Home),
			(9,20) -> new Cell(Home),
			(10,20) -> new Cell(Home),
			(11,20) -> new Cell(Home),
			(12,20) -> new Cell(Work),
			(13,20) -> new Cell(Home),
			(14,20) -> new Cell(Work),
			(15,20) -> new Cell(Work),
			(16,20) -> new Cell(Work),
			(17,20) -> new Cell(Work),
			(18,20) -> new Cell(Home),
			(19,20) -> new Cell(Work),
			(20,20) -> new Cell(Work),
			(21,20) -> new Cell(Work),
			(22,20) -> new Cell(Work),
			(23,20) -> new Cell(Work),
			(24,20) -> new Cell(Work),
			(25,20) -> new Cell(Work),
			(26,20) -> new Cell(Work),
			(27,20) -> new Cell(Work),
			(28,20) -> new Cell(Work),
			(29,20) -> new Cell(Work),
			(30,20) -> new Cell(FakeHome),
			(31,20) -> new Cell(FakeHome),
			(32,20) -> new Cell(FakeHome),
			(1,21) -> new Cell(FakeHome),
			(2,21) -> new Cell(FakeHome),
			(3,21) -> new Cell(FakeHome),
			(4,21) -> new Cell(FakeHome),
			(5,21) -> new Cell(FakeHome),
			(6,21) -> new Cell(Home),
			(7,21) -> new Cell(Home),
			(8,21) -> new Cell(Work),
			(9,21) -> new Cell(Home),
			(10,21) -> new Cell(Home),
			(11,21) -> new Cell(Home),
			(12,21) -> new Cell(Home),
			(13,21) -> new Cell(Work),
			(14,21) -> new Cell(Work),
			(15,21) -> new Cell(Work),
			(16,21) -> new Cell(Work),
			(17,21) -> new Cell(Work),
			(18,21) -> new Cell(Home),
			(19,21) -> new Cell(Work),
			(20,21) -> new Cell(Work),
			(21,21) -> new Cell(Work),
			(22,21) -> new Cell(Work),
			(23,21) -> new Cell(Work),
			(24,21) -> new Cell(Work),
			(25,21) -> new Cell(FakeHome),
			(26,21) -> new Cell(FakeHome),
			(27,21) -> new Cell(FakeHome),
			(28,21) -> new Cell(FakeHome),
			(29,21) -> new Cell(FakeHome),
			(30,21) -> new Cell(FakeHome),
			(31,21) -> new Cell(FakeHome),
			(32,21) -> new Cell(FakeHome),
			(1,22) -> new Cell(FakeHome),
			(2,22) -> new Cell(FakeHome),
			(3,22) -> new Cell(FakeHome),
			(4,22) -> new Cell(FakeHome),
			(5,22) -> new Cell(FakeHome),
			(6,22) -> new Cell(FakeHome),
			(7,22) -> new Cell(Home),
			(8,22) -> new Cell(Home),
			(9,22) -> new Cell(Home),
			(10,22) -> new Cell(Home),
			(11,22) -> new Cell(Home),
			(12,22) -> new Cell(Work),
			(13,22) -> new Cell(Work),
			(14,22) -> new Cell(Work),
			(15,22) -> new Cell(Work),
			(16,22) -> new Cell(Work),
			(17,22) -> new Cell(Work),
			(18,22) -> new Cell(Home),
			(19,22) -> new Cell(Home),
			(20,22) -> new Cell(Work),
			(21,22) -> new Cell(FakeHome),
			(22,22) -> new Cell(FakeHome),
			(23,22) -> new Cell(FakeHome),
			(24,22) -> new Cell(FakeHome),
			(25,22) -> new Cell(FakeHome),
			(26,22) -> new Cell(FakeHome),
			(27,22) -> new Cell(FakeHome),
			(28,22) -> new Cell(FakeHome),
			(29,22) -> new Cell(FakeHome),
			(30,22) -> new Cell(FakeHome),
			(31,22) -> new Cell(FakeHome),
			(32,22) -> new Cell(FakeHome),
			(1,23) -> new Cell(FakeHome),
			(2,23) -> new Cell(FakeHome),
			(3,23) -> new Cell(FakeHome),
			(4,23) -> new Cell(FakeHome),
			(5,23) -> new Cell(FakeHome),
			(6,23) -> new Cell(FakeHome),
			(7,23) -> new Cell(Home),
			(8,23) -> new Cell(Home),
			(9,23) -> new Cell(Work),
			(10,23) -> new Cell(Home),
			(11,23) -> new Cell(Home),
			(12,23) -> new Cell(Work),
			(13,23) -> new Cell(FakeHome),
			(14,23) -> new Cell(FakeHome),
			(15,23) -> new Cell(FakeHome),
			(16,23) -> new Cell(FakeHome),
			(17,23) -> new Cell(FakeHome),
			(18,23) -> new Cell(FakeHome),
			(19,23) -> new Cell(FakeHome),
			(20,23) -> new Cell(FakeHome),
			(21,23) -> new Cell(FakeHome),
			(22,23) -> new Cell(FakeHome),
			(23,23) -> new Cell(FakeHome),
			(24,23) -> new Cell(FakeHome),
			(25,23) -> new Cell(FakeHome),
			(26,23) -> new Cell(FakeHome),
			(27,23) -> new Cell(FakeHome),
			(28,23) -> new Cell(FakeHome),
			(29,23) -> new Cell(FakeHome),
			(30,23) -> new Cell(FakeHome),
			(31,23) -> new Cell(FakeHome),
			(32,23) -> new Cell(FakeHome),
			(1,24) -> new Cell(FakeHome),
			(2,24) -> new Cell(FakeHome),
			(3,24) -> new Cell(FakeHome),
			(4,24) -> new Cell(FakeHome),
			(5,24) -> new Cell(FakeHome),
			(6,24) -> new Cell(FakeHome),
			(7,24) -> new Cell(FakeHome),
			(8,24) -> new Cell(FakeHome),
			(9,24) -> new Cell(FakeHome),
			(10,24) -> new Cell(FakeHome),
			(11,24) -> new Cell(FakeHome),
			(12,24) -> new Cell(FakeHome),
			(13,24) -> new Cell(FakeHome),
			(14,24) -> new Cell(FakeHome),
			(15,24) -> new Cell(FakeHome),
			(16,24) -> new Cell(FakeHome),
			(17,24) -> new Cell(FakeHome),
			(18,24) -> new Cell(FakeHome),
			(19,24) -> new Cell(FakeHome),
			(20,24) -> new Cell(FakeHome),
			(21,24) -> new Cell(FakeHome),
			(22,24) -> new Cell(FakeHome),
			(23,24) -> new Cell(FakeHome),
			(24,24) -> new Cell(FakeHome),
			(25,24) -> new Cell(FakeHome),
			(26,24) -> new Cell(FakeHome),
			(27,24) -> new Cell(FakeHome),
			(28,24) -> new Cell(FakeHome),
			(29,24) -> new Cell(FakeHome),
			(30,24) -> new Cell(FakeHome),
			(31,24) -> new Cell(FakeHome),
			(32,24) -> new Cell(FakeHome),
			(1,25) -> new Cell(FakeHome),
			(2,25) -> new Cell(FakeHome),
			(3,25) -> new Cell(FakeHome),
			(4,25) -> new Cell(FakeHome),
			(5,25) -> new Cell(FakeHome),
			(6,25) -> new Cell(FakeHome),
			(7,25) -> new Cell(FakeHome),
			(8,25) -> new Cell(FakeHome),
			(9,25) -> new Cell(FakeHome),
			(10,25) -> new Cell(FakeHome),
			(11,25) -> new Cell(FakeHome),
			(12,25) -> new Cell(FakeHome),
			(13,25) -> new Cell(FakeHome),
			(14,25) -> new Cell(FakeHome),
			(15,25) -> new Cell(FakeHome),
			(16,25) -> new Cell(FakeHome),
			(17,25) -> new Cell(FakeHome),
			(18,25) -> new Cell(FakeHome),
			(19,25) -> new Cell(FakeHome),
			(20,25) -> new Cell(FakeHome),
			(21,25) -> new Cell(FakeHome),
			(22,25) -> new Cell(FakeHome),
			(23,25) -> new Cell(FakeHome),
			(24,25) -> new Cell(FakeHome),
			(25,25) -> new Cell(FakeHome),
			(26,25) -> new Cell(FakeHome),
			(27,25) -> new Cell(FakeHome),
			(28,25) -> new Cell(FakeHome),
			(29,25) -> new Cell(FakeHome),
			(30,25) -> new Cell(FakeHome),
			(31,25) -> new Cell(FakeHome),
			(32,25) -> new Cell(FakeHome)
		)
    val population = Foo.populateWorld(world)
    log.debug(s"Generated population size is: ${population.length}")
    val initialState = SimulationState(0, Morning, population, world)
    val virus = Virus(conf.getDouble("simulation.params.virus.infectivity"),
      conf.getInt("simulation.params.virus.curveFactor"))

    val simulationMaker = GinesActors.makeSimulation("simulation") _
    val simulation = simulationMaker(initialState, virus)

    simulation ! StartSimulation
  }
}
