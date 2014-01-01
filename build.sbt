organization := "gines"

name := "gines"

version := "0.1-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.4",
  "org.scala-lang" % "scala-swing" % "2.10.3",
  "org.clapper" % "grizzled-slf4j_2.10" % "1.0.1",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "com.typesafe" % "config" % "1.0.2",
  "com.typesafe.akka" % "akka-testkit_2.10" % "2.2.3" % "test",
  "com.typesafe.akka" % "akka-actor_2.10" % "2.2.3",
  "com.typesafe.akka" % "akka-zeromq_2.10" % "2.2.3",
  "com.cloudphysics" % "jerkson_2.10" % "0.6.3",
  "org.scalatest" % "scalatest_2.10" % "2.0",
  "org.scalacheck" %% "scalacheck" % "1.11.1",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")
