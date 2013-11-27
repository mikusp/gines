organization := "gines"

name := "gines"

version := "0.1-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.4",
  "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")
