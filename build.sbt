import Dependencies._
enablePlugins(GatlingPlugin)

name := "kafka-connect-timeseriesdb"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies := dependencies

addCommandAlias("build-complete", ";clean ;scalastyle ;assembly")