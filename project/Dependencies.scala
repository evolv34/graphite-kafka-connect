import sbt._

object Dependencies {
  val dependencies = Seq(
    "org.scala-lang" % "scala-library" % "2.12.2",
    "org.apache.kafka" %% "kafka" % "1.0.0",
    "org.apache.kafka" % "connect-api" % "1.0.0",
    "org.json4s" %% "json4s-jackson" % "3.5.3",
    "org.json4s" %% "json4s-native" % "3.5.3",
    "org.scalatest" %% "scalatest" % "3.0.4" % Test
  )
}
