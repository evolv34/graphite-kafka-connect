import Dependencies._
import Resolvers._

enablePlugins(GatlingPlugin)

name := "kafka-connect-timeseriesdb"

version := "1.0"

scalaVersion := "2.12.2"

resolvers := allResolvers

libraryDependencies := dependencies

addCommandAlias("build-complete", ";clean ;scalastyle ;assembly")