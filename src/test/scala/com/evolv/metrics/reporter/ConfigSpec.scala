package com.evolv.metrics.reporter

import com.evolv.metrics.UnitSpec

class ConfigSpec extends UnitSpec {

  "Config.TimeSeriesDatabaseHost" should "return timeseries.host" in {
    Config.TimeSeriesDatabaseHost shouldBe "timeseries.host"
  }

  "Config.TimeSeriesDatabasePort" should "return timeseries.port" in {
    Config.TimeSeriesDatabasePort shouldBe "timeseries.port"
  }

  "Config.TimeSeriesDatabase" should "return timeseries.reporter" in {
    Config.TimeSeriesDatabase shouldBe "timeseries.reporter"
  }
}
