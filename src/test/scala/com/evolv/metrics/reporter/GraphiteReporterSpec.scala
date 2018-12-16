package com.evolv.metrics.reporter

import com.evolv.metrics.UnitSpec
import com.evolv.metrics.converters.model.Measurement
import com.evolv.metrics.utils.MockServer
import org.apache.kafka.connect.sink.SinkRecord

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class GraphiteReporterSpec extends UnitSpec {
  val mockServerPort = 12003

  "GraphiteReporter.write" should "take the time from message and write the sink record to timeseries database" in {
    val serverFuture = MockServer(mockServerPort).init()
    val graphiteReporter = GraphiteReporter("localhost", mockServerPort.toString)
    val (metric, sinkRecord) = getSinkRecord(Some((System.currentTimeMillis / 1000).toString))

    graphiteReporter.write(sinkRecord)
    val actualResponse = Await.result[String](serverFuture, Duration.Inf)

    actualResponse shouldBe s"${metric.metricName} ${metric.value} ${metric.timestamp.get} "
  }

  it should "take the current time and write the sink record to timeseries database" in {
    val serverFuture = MockServer(mockServerPort).init()
    val graphiteReporter = GraphiteReporter("localhost", mockServerPort.toString)
    val (metric, sinkRecord) = getSinkRecord(None)

    graphiteReporter.write(sinkRecord)
    val actualResponse = Await.result[String](serverFuture, Duration.Inf)

    assert(actualResponse.contains(metric.metricName))
    assert(actualResponse.contains(metric.value))
  }

  private def getSinkRecord(timestamp: Option[String]): (Measurement, SinkRecord) = {
    val metric = Measurement("sample-topic", None, "123456789", timestamp)
    val sinkRecord = new SinkRecord(metric.metricName, 0, null, null, null, metric, 0L)
    (metric, sinkRecord)
  }
}
