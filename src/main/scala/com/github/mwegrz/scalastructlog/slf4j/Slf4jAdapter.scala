package com.github.mwegrz.scalastructlog.slf4j

import com.github.mwegrz.scalastructlog.Adapter
import com.github.mwegrz.scalastructlog.Logger.Tag
import org.slf4j._

class Slf4jAdapter[A](c: Class[A]) extends Adapter {
  import Slf4jAdapter._

  protected val l: Logger = LoggerFactory.getLogger(c)

  override def isInfoEnabled: Boolean = l.isInfoEnabled

  override def isDebugEnabled: Boolean = l.isDebugEnabled

  override def isWarningEnabled: Boolean = l.isWarnEnabled

  override def isErrorEnabled: Boolean = l.isErrorEnabled

  // Info
  override def info(message: String): Unit = l.info(message)

  override def info(message: String, cause: Throwable): Unit = l.info(message, cause)

  override def info(tags: List[Tag], message: String): Unit = withTags(tags)(l.info(message))

  override def info(tags: List[Tag], message: String, cause: Throwable): Unit = withTags(tags)(l.info(message, cause))

  // Debug
  override def debug(message: String): Unit = l.debug(message)

  override def debug(message: String, cause: Throwable): Unit = l.debug(message, cause)

  override def debug(tags: List[Tag], message: String): Unit = withTags(tags)(l.debug(message))

  override def debug(tags: List[Tag], message: String, cause: Throwable): Unit = withTags(tags)(l.debug(message, cause))

  // Warning
  override def warning(message: String): Unit = l.warn(message)

  override def warning(message: String, cause: Throwable): Unit = l.warn(message, cause)

  override def warning(tags: List[Tag], message: String): Unit = withTags(tags)(l.warn(message))

  override def warning(tags: List[Tag], message: String, cause: Throwable): Unit = withTags(tags)(l.warn(message, cause))

  // Error
  override def error(message: String): Unit = l.error(message)

  override def error(message: String, cause: Throwable): Unit = l.error(message, cause)

  override def error(tags: List[Tag], message: String): Unit = withTags(tags)(l.error(message))

  override def error(tags: List[Tag], message: String, cause: Throwable): Unit = withTags(tags)(l.error(message, cause))
}

object Slf4jAdapter {
  import language.implicitConversions

  implicit def tagsToMarker(tags: List[Tag]): Marker = {
    val markers = tags.map(a => MarkerFactory.getMarker(a.name))
    val head = markers.head
    for (m <- markers.drop(1)) {
      head.add(m)
    }
    head
  }

  def withTags[A](tags: List[Tag])(f: => A): A = withFields(Map("tags" -> tags.map(_.name).mkString(",")))(f)

  def withFields[A](fields: Map[String, String])(f: => A): A = {
    var cs: List[MDC.MDCCloseable] = null
    try {
      cs = fields.map(a => MDC.putCloseable(a._1, a._2)).toList
      f
    } finally {
      cs foreach { _.close() }
    }
  }
}
