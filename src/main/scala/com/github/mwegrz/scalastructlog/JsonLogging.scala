package com.github.mwegrz.scalastructlog

trait JsonLogging {
  @transient
  protected lazy val log: JsonLogger = JsonLogger(getClass)
}
