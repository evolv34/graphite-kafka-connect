package com.evolv.metrics.converters

import com.evolv.metrics.UnitSpec
import com.evolv.metrics.converters.model.Measurement
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write

import scala.collection.JavaConverters._

class MeasurementJsonSerdeSpec extends UnitSpec {
  implicit val formats = DefaultFormats.preservingEmptyValues

  "MetricSerde.MetricSerializer.serialize" should "return serialized metric object to ByteArray" in {
    val serializer = new MeasurementJsonSerde.MeasurementSerializer()
    val metric = Measurement("kafka.graphite.test", Some(Map("key" -> "value")), "123456789", None)

    val byteArrayOutput = serializer.serialize("test-topic", metric)
    byteArrayOutput shouldBe getBytes(metric)

    //to improve test cases
    serializer.configure(Map[String, String]().asJava, false)
    serializer.close()
  }

  "MetricSerde.MetricDeserializer.deserialize" should "return serialized metric object to ByteArray" in {
    val deserializer = new MeasurementJsonSerde.MeasurementDeserializer()
    val expectedMetric = Measurement("kafka.graphite.test", Some(Map("key" -> "value")), "123456789", None)

    val actualMetric = deserializer.deserialize("test-topic", getBytes(expectedMetric))
    actualMetric shouldBe expectedMetric

    //to improve test cases
    deserializer.configure(Map[String, String]().asJava, false)
    deserializer.close()
  }

  private def getBytes(metric: Measurement): Array[Byte] = {
    write(metric).toString.getBytes
  }
}
