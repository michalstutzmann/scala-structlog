package com.github.mwegrz.scalastructlog

import com.github.mwegrz.scalastructlog.KeyValueLogger.{ Key, Value }
import com.github.mwegrz.scalastructlog.Logger.Tag

trait KeyValueAdapter extends Adapter {
  // Info
  def info(message: String, context: Map[String, Any]): Unit

  def info(message: String, cause: Throwable, context: Map[Key, Value]): Unit

  def info(tags: List[Tag], message: String, context: Map[Key, Value]): Unit

  def info(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit

  // Debug
  def debug(message: String, context: Map[Key, Value]): Unit

  def debug(message: String, cause: Throwable, context: Map[Key, Value]): Unit

  def debug(tags: List[Tag], message: String, context: Map[Key, Value]): Unit

  def debug(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit

  // Warning
  def warning(message: String, context: Map[Key, Value]): Unit

  def warning(message: String, cause: Throwable, context: Map[Key, Value]): Unit

  def warning(tags: List[Tag], message: String, context: Map[Key, Value]): Unit

  def warning(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit

  // Error
  def error(message: String, context: Map[Key, Value]): Unit

  def error(message: String, cause: Throwable, context: Map[Key, Value]): Unit

  def error(tags: List[Tag], message: String, context: Map[Key, Value]): Unit

  def error(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit
}
