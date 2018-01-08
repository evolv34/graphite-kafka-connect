package com.evolv.metrics.converters

import java.util

import io.confluent.kafka.serializers.{AbstractKafkaAvroDeserializer, AbstractKafkaAvroSerializer, KafkaAvroDeserializerConfig}
import org.apache.avro.generic.GenericContainer

private[converters] object MeasurementAvroSerde {

  object TopicNameStrategy {
    def getSubjectName(topic: String, isKey: Boolean): String =
      if (isKey) {
        topic + "-key"
      } else {
        topic + "-value"
      }
  }

  class MeasurementAvroDeserializer extends AbstractKafkaAvroDeserializer {

    override def configure(config: KafkaAvroDeserializerConfig): Unit = {
      super.configure(config)
    }

    override def deserializerConfig(props: util.Map[String, _]): KafkaAvroDeserializerConfig = {
      super.deserializerConfig(props)
    }

    def deserialize(topic: String, isKey: Boolean, payload: Array[Byte]): GenericContainer = {
      deserializeWithSchemaAndVersion(topic, isKey, payload)
    }
  }

}
