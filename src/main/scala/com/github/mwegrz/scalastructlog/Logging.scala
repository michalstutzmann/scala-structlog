package com.github.mwegrz.scalastructlog

trait Logging {
  @transient
  protected lazy val log: Logger = Logger(getClass)
}
