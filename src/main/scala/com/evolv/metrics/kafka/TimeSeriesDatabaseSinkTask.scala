package com.evolv.metrics.kafka

import java.util

import com.evolv.metrics.reporter.{Config, Reporter}
import com.evolv.metrics.{toScalaList, toScalaPropertiesMap}
import org.apache.kafka.connect.sink.{SinkRecord, SinkTask}

class TimeSeriesDatabaseSinkTask extends SinkTask {
  var properties: Map[String, String] = _

  override def start(props: util.Map[String, String]): Unit = {
    properties = props
  }

  override def put(records: util.Collection[SinkRecord]): Unit = {
    val reporter = Reporter.get(properties(Config.TimeSeriesDatabase),
      properties(Config.TimeSeriesDatabaseHost),
      properties(Config.TimeSeriesDatabasePort))

    records.foreach(reporter.write)
  }

  override def stop(): Unit = {}

  override def version(): String = Version.version
}
