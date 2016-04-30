package com.github.mwegrz.scalastructlog.akka

import akka.event.LoggingAdapter
import com.github.mwegrz.scalastructlog.KeyValueAdapter
import com.github.mwegrz.scalastructlog.KeyValueLogger.{ Key, Value }
import com.github.mwegrz.scalastructlog.Logger.Tag

class AkkaKeyValueAdapter(override val l: LoggingAdapter) extends AkkaAdapter(l) with KeyValueAdapter {
  // Info
  override def info(message: String, context: Map[Key, Value]): Unit = ???

  override def info(message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  override def info(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = ???

  override def info(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  // Warning
  override def warning(message: String, context: Map[Key, Value]): Unit = ???

  override def warning(message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  override def warning(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = ???

  override def warning(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  // Error
  override def error(message: String, context: Map[Key, Value]): Unit = ???

  override def error(message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  override def error(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = ???

  override def error(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  // Debug
  override def debug(message: String, context: Map[Key, Value]): Unit = ???

  override def debug(message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  override def debug(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = ???

  override def debug(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???
}
