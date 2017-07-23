package com.github.mwegrz.scalastructlog.slf4j

import com.github.mwegrz.scalastructlog.KeyValueLogger.{ Key, Value }
import com.github.mwegrz.scalastructlog.KeyValueAdapter
import com.github.mwegrz.scalastructlog.Logger.Tag
import org.slf4j.Marker

class Slf4jKeyValueAdapter[A](c: Class[A]) extends Slf4jAdapter(c) with KeyValueAdapter {
  import Slf4jKeyValueAdapter._

  // Info
  override def info(message: String, context: Map[Key, Value]): Unit = l.info(context, message)

  override def info(message: String, cause: Throwable, context: Map[Key, Value]): Unit =
    l.info(context, message, cause)

  override def info(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = l.info(context, message)

  override def info(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit =
    l.info(context, message, cause)

  // Debug
  override def debug(message: String, context: Map[Key, Value]): Unit = l.debug(context, message)

  override def debug(message: String, cause: Throwable, context: Map[Key, Value]): Unit =
    l.debug(context, message, cause)

  override def debug(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = l.debug(context, message)

  override def debug(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit =
    l.debug(context, message, cause)

  // Warning
  override def warning(message: String, context: Map[Key, Value]): Unit = l.warn(context, message)

  override def warning(message: String, cause: Throwable, context: Map[Key, Value]): Unit =
    l.warn(context, message, cause)

  override def warning(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = l.warn(context, message)

  override def warning(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit =
    l.warn(context, message, cause)

  // Error
  override def error(message: String, context: Map[Key, Value]): Unit = l.error(context, message)

  override def error(message: String, cause: Throwable, context: Map[Key, Value]): Unit =
    l.error(context, message, cause)

  override def error(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = l.error(context, message)

  override def error(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit =
    l.error(context, message, cause)
}

object Slf4jKeyValueAdapter {
  import language.implicitConversions

  implicit def keyValueMapToMarker(keyValueMap: Map[Key, Value]): Marker = KeyValueMapMarker(keyValueMap)
}
