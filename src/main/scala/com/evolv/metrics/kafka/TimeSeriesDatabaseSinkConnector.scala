package com.evolv.metrics.kafka

import java.util

import com.evolv.metrics.reporter.Config
import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.common.config.ConfigDef.Importance
import org.apache.kafka.connect.connector.Task
import org.apache.kafka.connect.sink.SinkConnector
import com.evolv.metrics.{toJavaList, toScalaMap}

import scala.annotation.tailrec

class TimeSeriesDatabaseSinkConnector extends SinkConnector {
  var graphiteHost: String = _
  var graphitePort: String = _
  var timeseriesReporter: String = _

  override def start(props: util.Map[String, String]): Unit = {
    graphiteHost = props(Config.TimeSeriesDatabaseHost)
    graphitePort = props(Config.TimeSeriesDatabasePort)
    timeseriesReporter = props(Config.TimeSeriesDatabase)
  }

  override def taskClass(): Class[_ <: Task] = classOf[TimeSeriesDatabaseSinkTask]

  override def version(): String = Version.version

  override def stop(): Unit = {}

  override def taskConfigs(maxTasks: Int): util.List[util.Map[String, String]] = {
    @tailrec
    def loop(iterator: Int, configs: List[Map[String, String]]): List[Map[String, String]] = {
      if (iterator >= maxTasks) {
        configs
      } else {
        loop(iterator + 1,
          List(Map(Config.TimeSeriesDatabaseHost -> graphiteHost,
            Config.TimeSeriesDatabasePort -> graphitePort,
            Config.TimeSeriesDatabase -> timeseriesReporter)) ::: configs)
      }
    }

    loop(0, List())
  }

  override def config(): ConfigDef = new ConfigDef()
    .define(Config.TimeSeriesDatabaseHost, ConfigDef.Type.STRING, Importance.HIGH, "TimeSeries Database hostname")
    .define(Config.TimeSeriesDatabasePort, ConfigDef.Type.STRING, Importance.HIGH, "TimeSeries port")
}
