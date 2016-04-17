package com.github.mwegrz.scalastructlog.logback

import java.time.Instant
import ch.qos.logback.classic.pattern.RootCauseFirstThrowableProxyConverter
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.LayoutBase
import com.github.mwegrz.scalastructlog.KeyValueLogger.{Key, Value}
import com.github.mwegrz.scalastructlog.slf4j.KeyValueMapMarker
import scala.collection.JavaConverters._
import scala.collection.immutable.ListMap

class KeyValueLayout extends LayoutBase[ILoggingEvent] {
  private val throwableConverter = new RootCauseFirstThrowableProxyConverter

  override def doLayout(event: ILoggingEvent): String = {
    val time = Instant.ofEpochMilli(event.getTimeStamp).toString
    val message = event.getMessage
    val level = event.getLevel.levelStr.toLowerCase
    val logger = event.getLoggerName
    val stacktrace = throwableConverter.convert(event)
    val baseMap = ListMap("time" -> time, "level" -> level, "logger" -> logger, "message" -> message)
    val mdcMap = event.getMDCPropertyMap.asScala
    val markerMap = event.getMarker match {
      case m: KeyValueMapMarker => m.value
      case _ => ListMap.empty[Key, Value]
    }
    val stackTraceMap: Map[Key, Value] = if (stacktrace.nonEmpty) ListMap("stack-trace" -> stacktrace) else ListMap.empty[Key, Value]
    (baseMap ++ mdcMap ++ markerMap ++ stackTraceMap).mapValues(_.toString.replace("\t", "\\t")).mkString("\t") + "\n"
  }
}
