package com.evolv.metrics.kafka

import com.evolv.metrics.UnitSpec

class VersionSpec extends UnitSpec {

  "Version.version" should "return version as 0.0.1" in {
    Version.version shouldBe "0.0.1"
  }
}
