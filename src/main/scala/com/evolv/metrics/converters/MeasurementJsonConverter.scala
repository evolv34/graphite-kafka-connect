package com.evolv.metrics.converters

import java.util

import com.evolv.metrics.converters.model.Measurement
import org.apache.kafka.connect.data.{Schema, SchemaAndValue}
import org.apache.kafka.connect.storage.Converter

class MeasurementJsonConverter extends Converter {
  val serializer = new MetricSerde.MetricSerializer()
  val deserializer = new MetricSerde.MetricDeserializer()

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
    serializer.configure(configs, isKey)
    deserializer.configure(configs, isKey)
  }

  override def fromConnectData(topic: String, schema: Schema, value: scala.Any): Array[Byte] =
    serializer.serialize(topic, value.asInstanceOf[Measurement])

  override def toConnectData(topic: String, value: Array[Byte]): SchemaAndValue =
    new SchemaAndValue(Schema.OPTIONAL_STRING_SCHEMA, deserializer.deserialize(topic, value))
}