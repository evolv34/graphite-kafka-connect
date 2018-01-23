package com.evolv.metrics.converters

import java.util

import com.evolv.metrics.converters.MeasurementAvroSerde._
import com.evolv.metrics.converters.model.Measurement
import com.sksamuel.avro4s.RecordFormat
import io.confluent.connect.avro.{AvroData, AvroDataConfig}
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.connect.data.{Schema, SchemaAndValue}
import org.apache.kafka.connect.storage.Converter
import org.json4s.DefaultFormats

class MeasurementAvroConverter extends Converter {
  implicit val formats = DefaultFormats.preservingEmptyValues

  private var deserializer: MeasurementAvroDeserializer = _
  private var isKey: Boolean = _
  private var avroData: AvroData = _

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
    this.isKey = isKey

    avroData = new AvroData(new AvroDataConfig(configs))

    deserializer = new MeasurementAvroDeserializer()
    deserializer.configure(deserializer.deserializerConfig(configs))
  }

  override def fromConnectData(topic: String, schema: Schema, value: scala.Any): Array[Byte] = {
    Array[Byte]()
  }

  override def toConnectData(topic: String, value: Array[Byte]): SchemaAndValue = {
    val deserialized = deserializer.deserialize(topic, isKey, value)

    val format = RecordFormat[Measurement]
    new SchemaAndValue(avroData.toConnectSchema(deserialized.getSchema),
      format.from(deserialized.asInstanceOf[GenericRecord]))
  }
}