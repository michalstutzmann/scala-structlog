package com.github.mwegrz.scalastructlog

import com.github.mwegrz.scalastructlog.util.scalatest.UnitSpec

class KeyValueLoggerSpec extends UnitSpec {
  describe("A log entry") {
    it("should be properly printed") {
      Given("a logger, a message and context")
      val logger = KeyValueLogger()
      val message = "Test"
      val context = ("a" -> "a", "b" -> "b")
      When("logging")
      logger.warning(message, context)
      Then("a valid message should be printed")
    }
  }
}
