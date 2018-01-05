package com.evolv.metrics.kafka

import com.evolv.metrics.UnitSpec
import com.evolv.metrics.reporter.{Config, Metric}
import com.evolv.metrics.utils.MockServer
import org.apache.kafka.connect.sink.SinkRecord

import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class TimeSeriesDatabaseSinkTaskSpec extends UnitSpec {
  val mockServerPort = 12001
  val mockServer = MockServer(mockServerPort)

  "TimeSeriesDatabaseSinkTask.version" should "return 0.0.1" in {
    val timeSeriesDatabaseSinkTask = new TimeSeriesDatabaseSinkTask()

    timeSeriesDatabaseSinkTask.version() shouldBe "0.0.1"

    //to improve code coverage
    timeSeriesDatabaseSinkTask.stop()
  }

  "TimeSeriesDatabaseSinkTask.put" should "update value on the timeseries database" in {
    val serverFuture = mockServer.init()
    val timeSeriesDatabaseSinkTask = new TimeSeriesDatabaseSinkTask()

    val properties = Map(Config.TimeSeriesDatabase -> "GraphiteReporter",
      Config.TimeSeriesDatabaseHost -> "localhost",
      Config.TimeSeriesDatabasePort -> mockServerPort.toString)
    timeSeriesDatabaseSinkTask.start(properties.asJava)

    val metric = Metric("sample-topic", Map[String, String](), "123456789", Some((System.currentTimeMillis / 1000).toString))
    val sinkRecord = new SinkRecord("test-topic", 0, null, null, null, metric, 0L)

    timeSeriesDatabaseSinkTask.put(List(sinkRecord).asJava)
    val actualResponse = Await.result[String](serverFuture, Duration.Inf)

    actualResponse shouldBe s"${metric.metricName} ${metric.value} ${metric.timestamp.get} "
  }
}
