package com.github.mwegrz.scalastructlog.akka

import akka.event.DiagnosticMarkerBusLoggingAdapter
import com.github.mwegrz.scalastructlog.KeyValueAdapter
import com.github.mwegrz.scalastructlog.KeyValueLogger.{ Key, Value }
import com.github.mwegrz.scalastructlog.Logger.Tag

import scala.collection.JavaConverters._

object AkkaKeyValueAdapter {
  def withMdc(entries: Map[Key, Value])(f: => Unit)(implicit l: DiagnosticMarkerBusLoggingAdapter): Unit = {
    l.setMDC(entries.asJava)
    () =>
      f
      l.clearMDC()
  }
}

class AkkaKeyValueAdapter(override val l: DiagnosticMarkerBusLoggingAdapter)
    extends AkkaAdapter(l)
    with KeyValueAdapter {
  import AkkaKeyValueAdapter._

  // Info
  override def info(message: String, context: Map[Key, Value]): Unit =
    withMdc(context)(l.info(message))(l)

  override def info(message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  override def info(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = ???

  override def info(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  // Warning
  override def warning(message: String, context: Map[Key, Value]): Unit =
    withMdc(context)(l.warning(message))(l)

  override def warning(message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  override def warning(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = ???

  override def warning(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  // Error
  override def error(message: String, context: Map[Key, Value]): Unit =
    withMdc(context)(l.error(message))(l)

  override def error(message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  override def error(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = ???

  override def error(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  // Debug
  override def debug(message: String, context: Map[Key, Value]): Unit =
    withMdc(context)(l.debug(message))(l)

  override def debug(message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???

  override def debug(tags: List[Tag], message: String, context: Map[Key, Value]): Unit = ???

  override def debug(tags: List[Tag], message: String, cause: Throwable, context: Map[Key, Value]): Unit = ???
}
