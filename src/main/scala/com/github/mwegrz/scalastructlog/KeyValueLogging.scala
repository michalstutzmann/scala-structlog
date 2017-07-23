package com.github.mwegrz.scalastructlog

trait KeyValueLogging {
  @transient
  protected lazy val log: KeyValueLogger = KeyValueLogger(getClass)
}
