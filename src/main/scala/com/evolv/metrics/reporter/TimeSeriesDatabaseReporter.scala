package com.evolv.metrics.reporter

import org.apache.kafka.connect.sink.SinkRecord

object Reporter {
  var reporter: TimeSeriesDatabaseReporter = _

  def get(reporterName: String, hostName: String, hostPort: String): TimeSeriesDatabaseReporter = {

    def getReporter(): TimeSeriesDatabaseReporter = {
      reporterName match {
        case "GraphiteReporter" => GraphiteReporter(hostName, hostPort)
      }
    }

    Option(reporter) match {
      case Some(reporter) => reporter
      case None => getReporter()
    }
  }
}

private[reporter] abstract class TimeSeriesDatabaseReporter {

  def write(record: SinkRecord)
}
