package com.evolv.metrics.reporter

case class Metric(metricName: String, tags: Map[String, String], value: String, timestamp: Option[String])
