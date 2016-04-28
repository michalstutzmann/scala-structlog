package com.github.mwegrz.scalastructlog

import org.json4s.JsonDSL._

object Test extends App {
  val jsonLog = JsonLogger(getClass)
  //val kvLog = KeyValueLogger(getClass)
  //jsonLog.info(Tag("aa"), "test", ("lotto" -> ("test" -> "a")) ~ ("dupa" -> "ble"))
  jsonLog.debug("test", new Exception("test ex 1", new Exception("test ex 2")), ("message" -> "Shutdown") ~ ("test" -> "Shutdown"))
  //log.info((Tag("A"), Tag("B")), "test", ("lotto" -> ("test" -> "a")) ~ ("dupa" -> "ble"))
  //log.info("Shutdown", "message" -> "Shutdown", "test" -> "Shutdown")
  //log.info("Shutdown", Map("message" -> "Shutdown", "test" -> "Shutdown"))
  //log.error("Bang!", new IllegalArgumentException("a"))
}
