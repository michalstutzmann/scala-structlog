package com.github.mwegrz.scalastructlog

import com.github.mwegrz.scalastructlog.JsonLogger.JsonObject
import com.github.mwegrz.scalastructlog.Logger.Tag

trait JsonAdapter extends Adapter {
  // Info
  def info(message: String, context: JsonObject): Unit

  def info(message: String, cause: Throwable, context: JsonObject): Unit

  def info(tags: List[Tag], message: String, context: JsonObject): Unit

  def info(tags: List[Tag], message: String, cause: Throwable, context: JsonObject): Unit

  // Debug
  def debug(message: String, context: JsonObject): Unit

  def debug(message: String, cause: Throwable, context: JsonObject): Unit

  def debug(tags: List[Tag], message: String, context: JsonObject): Unit

  def debug(tags: List[Tag], message: String, cause: Throwable, context: JsonObject): Unit

  // Warning
  def warning(message: String, context: JsonObject): Unit

  def warning(message: String, cause: Throwable, context: JsonObject): Unit

  def warning(tags: List[Tag], message: String, context: JsonObject): Unit

  def warning(tags: List[Tag], message: String, cause: Throwable, context: JsonObject): Unit

  // Error
  def error(message: String, context: JsonObject): Unit

  def error(message: String, cause: Throwable, context: JsonObject): Unit

  def error(tags: List[Tag], message: String, context: JsonObject): Unit

  def error(tags: List[Tag], message: String, cause: Throwable, context: JsonObject): Unit
}
