package com.github.mwegrz.scalastructlog

import com.github.mwegrz.scalastructlog.Logger.Tag

trait Adapter {
  def isInfoEnabled: Boolean

  def isDebugEnabled: Boolean

  def isWarningEnabled: Boolean

  def isErrorEnabled: Boolean
  
  // Info
  def info(message: String): Unit

  def info(message: String, cause: Throwable): Unit

  def info(tags: List[Tag], message: String): Unit

  def info(tags: List[Tag], message: String, cause: Throwable): Unit
  
  // Debug
  def debug(message: String): Unit

  def debug(message: String, cause: Throwable): Unit

  def debug(tags: List[Tag], message: String): Unit

  def debug(tags: List[Tag], message: String, cause: Throwable): Unit
  
  // Warning
  def warning(message: String): Unit

  def warning(message: String, cause: Throwable): Unit

  def warning(tags: List[Tag], message: String): Unit

  def warning(tags: List[Tag], message: String, cause: Throwable): Unit
  
  // Error
  def error(message: String): Unit

  def error(message: String, cause: Throwable): Unit

  def error(tags: List[Tag], message: String): Unit

  def error(tags: List[Tag], message: String, cause: Throwable): Unit
}