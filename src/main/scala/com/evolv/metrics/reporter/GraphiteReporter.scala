package com.evolv.metrics.reporter

import java.io.{BufferedReader, DataOutputStream, InputStreamReader}
import java.net.Socket

import org.apache.kafka.connect.sink.SinkRecord

class GraphiteReporter(host: String, port: String) extends TimeSeriesDatabaseReporter {

  val clientSocket = new Socket(host, port.toInt)
  val outToServer = new DataOutputStream(clientSocket.getOutputStream)

  override def write(record: SinkRecord): Unit = {
    val metric = record.value().asInstanceOf[Metric]

    write(metric.metricName, metric.value, metric.timestamp)
  }

  private def write(metricName: String, value: String, timestampOption: Option[String]): Unit = {
    def toLineProtocol = {
      timestampOption match {
        case None => {
          val timestamp = System.currentTimeMillis / 1000
          s"$metricName ${value.toString} $timestamp \n"
        }
        case Some(timestamp) => s"$metricName ${value.toString} $timestamp \n"
      }
    }

    outToServer.writeBytes(toLineProtocol)
    outToServer.flush()
  }
}

object GraphiteReporter {
  def apply(host: String, port: String): GraphiteReporter = new GraphiteReporter(host, port)
}
