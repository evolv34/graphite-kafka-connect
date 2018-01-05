package com.evolv

import java.util

import org.apache.kafka.connect.sink.SinkRecord

import scala.collection.JavaConverters._

package object metrics {

  implicit def toJavaList(props: List[Map[String, String]]): util.List[util.Map[String, String]] =
    props.map(_.asJava)
    .asJava

  implicit def toScalaList(records: util.Collection[SinkRecord]): List[SinkRecord] = records.asScala.toList
  implicit def toScalaMap(props: util.Map[String, String]): Map[String, String] = props.asScala.toMap
}
