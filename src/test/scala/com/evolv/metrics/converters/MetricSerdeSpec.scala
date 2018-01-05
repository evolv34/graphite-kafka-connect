package com.evolv.metrics.converters

import com.evolv.metrics.UnitSpec
import com.evolv.metrics.reporter.Metric
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.JavaConverters._

class MetricSerdeSpec extends UnitSpec {
  implicit val formats = DefaultFormats.preservingEmptyValues

  "MetricSerde.MetricSerializer.serialize" should "return serialized metric object to ByteArray" in {
    val serializer = new MetricSerde.MetricSerializer()
    val metric = Metric("kafka.graphite.test", Map("key" -> "value"), "123456789", None)

    val byteArrayOutput = serializer.serialize("test-topic", metric)
    byteArrayOutput shouldBe getBytes(metric)

    //to improve test cases
    serializer.configure(Map[String, String]().asJava, false)
    serializer.close()
  }

  "MetricSerde.MetricDeserializer.deserialize" should "return serialized metric object to ByteArray" in {
    val deserializer = new MetricSerde.MetricDeserializer()
    val expectedMetric = Metric("kafka.graphite.test", Map("key" -> "value"), "123456789", None)

    val actualMetric = deserializer.deserialize("test-topic", getBytes(expectedMetric))
    actualMetric shouldBe expectedMetric

    //to improve test cases
    deserializer.configure(Map[String, String]().asJava, false)
    deserializer.close()
  }

  private def getBytes(metric: Metric): Array[Byte] = {
    write(metric).toString.getBytes
  }
}
