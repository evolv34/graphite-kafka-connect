package com.evolv.metrics.kafka

import com.evolv.metrics.UnitSpec
import com.evolv.metrics.reporter.Config

import scala.collection.JavaConverters._

class TimeSeriesDatabaseSinkConnectorSpec extends UnitSpec {

  "TimeSeriesDatabaseSinkConnector.taskClass" should "return TimeSeriesDatabaseSinkTask" in {
    val timeSeriesDatabaseSinkConnector = new TimeSeriesDatabaseSinkConnector()

    timeSeriesDatabaseSinkConnector.taskClass() shouldBe classOf[TimeSeriesDatabaseSinkTask]
  }

  "TimeSeriesDatabaseSinkConnector.version" should "return version" in {
    val timeSeriesDatabaseSinkConnector = new TimeSeriesDatabaseSinkConnector()

    timeSeriesDatabaseSinkConnector.version() shouldBe Version.version
  }

  "TimeSeriesDatabaseSinkConnector.taskConfigs" should "return list with all task configs" in {
    val timeSeriesDatabaseSinkConnector = new TimeSeriesDatabaseSinkConnector()

    val props = Map[String, String](
      Config.TimeSeriesDatabaseHost -> "localhost",
      Config.TimeSeriesDatabasePort -> "2003",
      Config.TimeSeriesDatabase -> "GraphiteReporter")
    timeSeriesDatabaseSinkConnector.start(props.asJava)

    timeSeriesDatabaseSinkConnector.taskConfigs(1) shouldBe List(props.asJava).asJava
    timeSeriesDatabaseSinkConnector.stop()
  }

  "TimeSeriesDatabaseSinkConnector.config" should "return ConfigDefs" in {
    val timeSeriesDatabaseSinkConnector = new TimeSeriesDatabaseSinkConnector()
    val configDefs = timeSeriesDatabaseSinkConnector.config()
    configDefs.configKeys().keySet().asScala shouldBe Set(Config.TimeSeriesDatabaseHost, Config.TimeSeriesDatabasePort)
  }
}
