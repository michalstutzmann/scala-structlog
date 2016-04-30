package com.github.mwegrz.scalastructlog.slf4j

import com.github.mwegrz.scalastructlog.JsonAdapter
import com.github.mwegrz.scalastructlog.JsonLogger.JsonObject
import com.github.mwegrz.scalastructlog.Logger.Tag
import org.slf4j.Marker

class Slf4jJsonAdapter[A](c: Class[A]) extends Slf4jAdapter(c) with JsonAdapter {
  import Slf4jJsonAdapter._

  // Info
  override def info(message: String, context: JsonObject): Unit = l.info(context, message)

  override def info(message: String, cause: Throwable, context: JsonObject): Unit = l.info(context, message, cause)

  override def info(tags: List[Tag], message: String, context: JsonObject): Unit = l.info(context, message)

  override def info(tags: List[Tag], message: String, cause: Throwable, context: JsonObject): Unit = l.info(context, message, cause)

  // Debug
  override def debug(message: String, context: JsonObject): Unit = l.debug(context, message)

  override def debug(message: String, cause: Throwable, context: JsonObject): Unit = l.debug(context, message, cause)

  override def debug(tags: List[Tag], message: String, context: JsonObject): Unit = l.debug(context, message)

  override def debug(tags: List[Tag], message: String, cause: Throwable, context: JsonObject): Unit = l.debug(context, message, cause)

  // Warning
  override def warning(message: String, context: JsonObject): Unit = l.warn(context, message)

  override def warning(message: String, cause: Throwable, context: JsonObject): Unit = l.warn(context, message, cause)

  override def warning(tags: List[Tag], message: String, context: JsonObject): Unit = l.warn(context, message)

  override def warning(tags: List[Tag], message: String, cause: Throwable, context: JsonObject): Unit = l.warn(context, message, cause)

  // Error
  override def error(message: String, context: JsonObject): Unit = l.error(context, message)

  override def error(message: String, cause: Throwable, context: JsonObject): Unit = l.error(context, message, cause)

  override def error(tags: List[Tag], message: String, context: JsonObject): Unit = l.error(context, message)

  override def error(tags: List[Tag], message: String, cause: Throwable, context: JsonObject): Unit = l.error(context, message, cause)
}

object Slf4jJsonAdapter {
  import language.implicitConversions

  implicit def jsonObjectToMarker(jsonObject: JsonObject): Marker = JsonObjectMarker(jsonObject)
}
