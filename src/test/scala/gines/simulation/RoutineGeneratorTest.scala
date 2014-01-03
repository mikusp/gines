package simulation



/*
object RoutineSpecification extends Properties("Routine") {
  import Prop.forAll
  import RoutineArbitrary._

  property("timechunksOrder") = forAll {
    (r: Stream[Behaviour]) => isOrdered(r)
  }

  property("oneSwap") = forAll {
    (r: Stream[Behaviour], b: Behaviour) => {
      val r2 = RoutineGenerator.swapFirst(b)(r)
      isOrdered(r2)
    }
  }

  property("actuallyIsSwapping") = forAll {
    (r: Stream[Behaviour], b: Behaviour) => {
      val r2 = RoutineGenerator.swapFirst(b)(r)
      r != r2
    }
  }

  def isOrdered(r: Stream[Behaviour]): Boolean = {
    lazy val isSucc = (a: TimeChunk, b: TimeChunk) => a match {
        case Morning => b == Afternoon
        case Afternoon => b == Evening
        case Evening => b == Night
        case Night => b == Morning
      }

      lazy val timechunks = Stream.continually(TimeChunks.apply).flatten
      lazy val testStream = r.take(TimeChunks.apply.length + 1).map(_.tc).zip(timechunks)

      testStream.forall {
        (t: (TimeChunk, TimeChunk)) =>
          t._1 == t._2
      }
  }
}

object RoutineArbitrary {
  implicit def arbRoutine: Arbitrary[Stream[Behaviour]] = Arbitrary {
    val genWorld = for {
      dim <- Gen.choose(10, 200)
    } yield RandomWorldGenerator(dim, dim)

    val genAge = Gen.oneOf(Child, Teenager, Adult, Elderly)

    for {
      w <- genWorld
      a <- genAge
    } yield RoutineGenerator(a, Conf.activities, w)
  }

  implicit def arbCell: Gen[Cell] = for {
    t <- Gen.oneOf(School, Home, Work)
  } yield new Cell(t)

  implicit def arbBehaviour: Arbitrary[Behaviour] = Arbitrary {
    val genTimeChunk = Gen.oneOf(Morning, Afternoon, Evening, Night)

    for {
      c <- arbCell
      tc <- genTimeChunk
    } yield Behaviour(c, tc)
  }
}
*/