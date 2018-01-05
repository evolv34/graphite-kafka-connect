package com.evolv.metrics.converters

import java.util

import com.evolv.metrics.reporter.Metric
import org.apache.kafka.common.serialization.{Deserializer, Serializer}
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.{read, write}

object MetricSerde {
  implicit val formats = DefaultFormats.preservingEmptyValues

  class MetricSerializer extends Serializer[Metric] {
    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}
    override def serialize(topic: String, data: Metric): Array[Byte] = write(data).toString.getBytes()
    override def close(): Unit = {}
  }

  class MetricDeserializer extends Deserializer[Metric] {
    val encoding = "UTF8"

    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}
    override def close(): Unit = {}
    override def deserialize(topic: String, data: Array[Byte]): Metric = read[Metric](new String(data, encoding))
  }
}
