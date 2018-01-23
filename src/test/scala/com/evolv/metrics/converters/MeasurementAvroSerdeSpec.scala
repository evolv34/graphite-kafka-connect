package com.evolv.metrics.converters

import com.evolv.metrics.UnitSpec
import com.evolv.metrics.converters.MeasurementAvroSerde.MeasurementAvroDeserializer
import com.evolv.metrics.converters.model.Measurement
import org.json4s.DefaultFormats
import org.json4s.native.Serialization._

class MeasurementAvroSerdeSpec extends UnitSpec {
  implicit val formats = DefaultFormats.preservingEmptyValues

  "TopicNameStrategy.getSubjectName" should "return topic-value when isKey is false" in {
    val isKey = false
    val subjectName = MeasurementAvroSerde.TopicNameStrategy.getSubjectName("metric.topic", isKey)
    subjectName shouldBe "metric.topic-value"
  }

  it should "return topic-key when isKey is true" in {
    val isKey = true
    val subjectName = MeasurementAvroSerde.TopicNameStrategy.getSubjectName("metric.topic", isKey)
    subjectName shouldBe "metric.topic-key"
  }
}
