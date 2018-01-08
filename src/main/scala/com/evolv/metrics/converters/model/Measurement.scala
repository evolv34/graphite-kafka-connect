package com.evolv.metrics.converters.model

case class Measurement(metricName: String, tags: Map[String, String], value: String, timestamp: Option[String])
