package com.evolv.metrics.reporter

import com.evolv.metrics.UnitSpec
import com.evolv.metrics.converters.MeasurementJsonConverter
import com.evolv.metrics.converters.model.Measurement
import org.apache.kafka.connect.data.{Schema, SchemaAndValue}
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write

import scala.collection.JavaConverters._

class MeasurementJsonConverterSpec extends UnitSpec {
  implicit val formats = DefaultFormats.preservingEmptyValues

  "MetricConverter.fromConnectData" should "serialize Metric case class to Array[Byte]" in {
    val metricConverter = new MeasurementJsonConverter()
    val metric = Measurement("test.measurement", Map[String, String](), "123456", None)
    val expectedResponse = write(metric).toString.getBytes()

    val actualResponse = metricConverter.fromConnectData("test-topic", null, metric)
    actualResponse shouldBe expectedResponse

    // to improve test coverage
    metricConverter.configure(Map[String, String]().asJava, false)
  }

  "MetricConverter.toConnectData" should "deserialize Array[Byte] to Metric case class" in {
    val metricConverter = new MeasurementJsonConverter()
    val metric = Measurement("test.measurement", Map[String, String](), "123456", None)
    val serializedMetric = write(metric).toString.getBytes()

    val actualResponse = metricConverter.toConnectData("test-topic", serializedMetric)
    actualResponse shouldBe new SchemaAndValue(Schema.OPTIONAL_STRING_SCHEMA, metric)
  }
}
