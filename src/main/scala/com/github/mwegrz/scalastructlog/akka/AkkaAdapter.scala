package com.github.mwegrz.scalastructlog.akka

import akka.event.DiagnosticMarkerBusLoggingAdapter
import com.github.mwegrz.scalastructlog.Adapter
import com.github.mwegrz.scalastructlog.Logger.Tag

class AkkaAdapter(val l: DiagnosticMarkerBusLoggingAdapter) extends Adapter {
  override def isInfoEnabled: Boolean = l.isInfoEnabled

  override def isDebugEnabled: Boolean = l.isDebugEnabled

  override def isWarningEnabled: Boolean = l.isWarningEnabled

  override def isErrorEnabled: Boolean = l.isErrorEnabled

  // Info
  override def info(message: String): Unit = l.info(message)

  override def info(message: String, cause: Throwable): Unit =
    l.info(message, cause)

  override def info(tags: List[Tag], message: String): Unit = l.info(message)

  override def info(tags: List[Tag], message: String, cause: Throwable): Unit =
    l.info(message, cause)

  // Debug
  override def debug(message: String): Unit = l.debug(message)

  override def debug(message: String, cause: Throwable): Unit =
    l.debug(message, cause)

  override def debug(tags: List[Tag], message: String): Unit = l.debug(message)

  override def debug(tags: List[Tag], message: String, cause: Throwable): Unit =
    l.debug(message, cause)

  // Warning
  override def warning(message: String): Unit = l.warning(message)

  override def warning(message: String, cause: Throwable): Unit =
    l.warning(message, cause)

  override def warning(tags: List[Tag], message: String): Unit =
    l.warning(message)

  override def warning(tags: List[Tag], message: String, cause: Throwable): Unit = l.warning(message, cause)

  // Error
  override def error(message: String): Unit = l.error(message)

  override def error(message: String, cause: Throwable): Unit =
    l.error(message, cause)

  override def error(tags: List[Tag], message: String): Unit = l.error(message)

  override def error(tags: List[Tag], message: String, cause: Throwable): Unit =
    l.error(message, cause)
}
