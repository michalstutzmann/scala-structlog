package com.github.mwegrz.scalastructlog

import com.github.mwegrz.scalastructlog.util.scalatest.UnitSpec

class LoggerSpec extends UnitSpec {
  describe("A log entry") {
    it("should be properly printed") {
      Given("a logger and a message")
      val logger = Logger()
      val message = "Test"
      When("logging")
      logger.warning(message)
      Then("a valid message should be printed")
    }
  }
}
