package com.evolv.metrics.converters

import java.util

import com.evolv.metrics.converters.model.Measurement
import org.apache.kafka.common.serialization.{Deserializer, Serializer}
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.{read, write}

private[converters] object MetricSerde {
  implicit val formats = DefaultFormats.preservingEmptyValues

  class MetricSerializer extends Serializer[Measurement] {
    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}
    override def serialize(topic: String, data: Measurement): Array[Byte] = write(data).toString.getBytes()
    override def close(): Unit = {}
  }

  class MetricDeserializer extends Deserializer[Measurement] {
    val encoding = "UTF8"

    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}
    override def close(): Unit = {}
    override def deserialize(topic: String, data: Array[Byte]): Measurement = read[Measurement](new String(data, encoding))
  }
}
